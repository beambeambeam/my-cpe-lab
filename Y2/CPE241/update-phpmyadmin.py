#!/usr/bin/env python3

"""
Update phpMyAdmin to automatically show all databases
Creates/updates docker-compose.phpmyadmin-override.yml
"""

import re
from pathlib import Path


def get_all_databases():
    """Get all database service names from compose files"""
    databases = []

    # Get db_default from main compose file
    main_file = Path("docker-compose.yml")
    if main_file.exists():
        with open(main_file, "r") as f:
            content = f.read()
            db_pattern = r"  (\w+):\s*\n\s*image:\s*mysql:8\.0"
            dbs = re.findall(db_pattern, content)
            databases.extend(dbs)

    # Get databases from additional compose files
    for compose_file in Path(".").glob("docker-compose.*.yml"):
        if compose_file.name not in ["docker-compose.yml", "docker-compose.phpmyadmin-override.yml"]:
            with open(compose_file, "r") as f:
                content = f.read()
                db_pattern = r"  (\w+):\s*\n\s*image:\s*mysql:8\.0"
                dbs = re.findall(db_pattern, content)
                databases.extend(dbs)

    return sorted(set(databases))


def update_phpmyadmin():
    """Create/update phpMyAdmin override file with all databases"""
    databases = get_all_databases()

    if not databases:
        print("No databases found")
        return False

    # Get ports for each database
    hosts = []
    ports = []

    for db in databases:
        hosts.append(db)
        # Inside Docker network, all MySQL containers use port 3306 (internal port)
        # The host-mapped ports (like 3307) are only for external access
        ports.append("3306")

    hosts_str = ",".join(hosts)
    ports_str = ",".join(ports)

    override_content = f"""version: "3.8"

services:
  phpmyadmin:
    environment:
      PMA_HOST: null
      PMA_PORT: null
      PMA_HOSTS: {hosts_str}
      PMA_PORTS: {ports_str}
"""

    override_file = Path("docker-compose.phpmyadmin-override.yml")
    with open(override_file, "w") as f:
        f.write(override_content)

    print(f"✓ Updated phpMyAdmin configuration")
    print(f"✓ Databases: {hosts_str}")
    print(f"✓ Ports: {ports_str}")
    print(f"\nNext steps:")
    print(f"1. Restart phpMyAdmin: docker-compose -f docker-compose.yml -f docker-compose.phpmyadmin-override.yml restart phpmyadmin")
    print(f"2. Or restart all: docker-compose -f docker-compose.yml -f docker-compose.phpmyadmin-override.yml -f docker-compose.*.yml restart phpmyadmin")
    print(f"3. Refresh phpMyAdmin in browser - all databases will appear in dropdown")

    return True


if __name__ == "__main__":
    update_phpmyadmin()

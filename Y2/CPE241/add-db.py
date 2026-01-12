#!/usr/bin/env python3

"""
Script to add a new MySQL database service without modifying docker-compose.yml
Creates separate compose files for each additional database
Usage: python3 add-db.py <db_name> [port]
Example: python3 add-db.py db_test_1 3307
"""

import sys
import re
from pathlib import Path


def sanitize_env_name(name):
    """Convert database name to valid environment variable name"""
    env_name = re.sub(r"[^a-zA-Z0-9]", "_", name).upper()
    env_name = re.sub(r"_+", "_", env_name).strip("_")
    return env_name


def get_next_port():
    """Get the next available port by checking existing compose files"""
    base_port = 3306
    used_ports = set()

    # Check main compose file
    main_file = Path("docker-compose.yml")
    if main_file.exists():
        with open(main_file, "r") as f:
            content = f.read()
            ports = re.findall(r':\s*"(\d+):3306"', content)
            used_ports.update(int(p) for p in ports)

    # Check additional compose files
    for compose_file in Path(".").glob("docker-compose.*.yml"):
        if compose_file.name != "docker-compose.yml":
            with open(compose_file, "r") as f:
                content = f.read()
                ports = re.findall(r':\s*"(\d+):3306"', content)
                used_ports.update(int(p) for p in ports)

    if used_ports:
        return max(used_ports) + 1
    return base_port + 1


def add_database(db_name, port=None):
    """Add a new database service by creating a separate compose file"""
    if db_name == "db_default":
        print("Error: 'db_default' is reserved for the main docker-compose.yml")
        return False

    # Validate database name
    if not re.match(r"^[a-zA-Z0-9_]+$", db_name):
        print("Error: Database name can only contain letters, numbers, and underscores")
        return False

    if port is None:
        port = get_next_port()

    env_prefix = sanitize_env_name(db_name)
    compose_file = Path(f"docker-compose.{db_name}.yml")

    # Check if database already exists
    if compose_file.exists():
        print(f"Error: Database '{db_name}' already exists")
        return False

    # Check if port is already in use
    for existing_file in Path(".").glob("docker-compose*.yml"):
        with open(existing_file, "r") as f:
            if f'"{port}:3306"' in f.read():
                print(f"Error: Port {port} is already in use")
                return False

    print(f"Adding database service: {db_name} on port {port}")

    # Create mysql-init directory
    init_dir = Path(f"mysql-init/{db_name}")
    init_dir.mkdir(parents=True, exist_ok=True)

    # Create separate compose file for this database
    compose_content = f"""version: "3.8"

services:
  {db_name}:
    image: mysql:8.0
    container_name: cpe241_mysql_{db_name}
    platform: linux/arm64
    restart: unless-stopped
    ports:
      - "${{{env_prefix}_PORT:-{port}}}:3306"
    environment:
      MYSQL_ROOT_PASSWORD: ${{MYSQL_ROOT_PASSWORD}}
      MYSQL_DATABASE: ${{{env_prefix}_DATABASE:-{db_name}}}
      MYSQL_USER: ${{MYSQL_USER}}
      MYSQL_PASSWORD: ${{MYSQL_PASSWORD}}
      MYSQL_CHARACTER_SET_SERVER: utf8mb4
      MYSQL_COLLATION_SERVER: utf8mb4_unicode_ci
    volumes:
      - mysql_{db_name}_data:/var/lib/mysql
      - ./mysql-init/{db_name}:/docker-entrypoint-initdb.d
    command: --default-authentication-plugin=mysql_native_password
    networks:
      - cpe241_network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s

volumes:
  mysql_{db_name}_data:
    driver: local

networks:
  cpe241_network:
    driver: bridge
"""

    with open(compose_file, "w") as f:
        f.write(compose_content)

    # Note: phpMyAdmin has PMA_ARBITRARY: 1, so it can connect to any server
    # No need to modify docker-compose.yml

    # Update .env file if it exists
    env_file = Path(".env")
    if env_file.exists():
        with open(env_file, "a") as f:
            f.write(f"\n# {db_name} Configuration\n")
            f.write(f"{env_prefix}_DATABASE={db_name}\n")
            f.write(f"{env_prefix}_PORT={port}\n")

    print(f"✓ Database service '{db_name}' added successfully!")
    print(f"✓ Port: {port}")
    print(f"✓ Volume: mysql_{db_name}_data")
    print(f"✓ Created: {compose_file}")

    # Update phpMyAdmin configuration
    import subprocess

    update_script = Path("update-phpmyadmin.py")
    if update_script.exists():
        subprocess.run(["python3", str(update_script)], capture_output=True)
        print("✓ phpMyAdmin configuration updated")

    print("\nNext steps:")
    print(
        f"1. Start the database: docker-compose -f docker-compose.yml -f docker-compose.phpmyadmin-override.yml -f {compose_file} up -d {db_name}"
    )
    print("2. Or start all: ./start-all.sh")
    print("3. Refresh phpMyAdmin in browser - new database will appear in dropdown")

    return True


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python3 add-db.py <db_name> [port]")
        print("Example: python3 add-db.py db_test_1 3307")
        sys.exit(1)

    db_name = sys.argv[1]

    port = None
    if len(sys.argv) > 2:
        try:
            port = int(sys.argv[2])
        except ValueError:
            print(f"Error: Invalid port number '{sys.argv[2]}'")
            sys.exit(1)

    success = add_database(db_name, port)
    sys.exit(0 if success else 1)

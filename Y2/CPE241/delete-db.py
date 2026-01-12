#!/usr/bin/env python3

"""
Script to list and delete MySQL database services
Deletes separate compose files without touching main docker-compose.yml
Usage: python3 delete-db.py [db_name]
"""

import sys
import re
import os
from pathlib import Path


def list_databases():
    """List all database services from compose files"""
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
        if compose_file.name != "docker-compose.yml":
            with open(compose_file, "r") as f:
                content = f.read()
                db_pattern = r"  (\w+):\s*\n\s*image:\s*mysql:8\.0"
                dbs = re.findall(db_pattern, content)
                databases.extend(dbs)

    return sorted(set(databases))


def remove_database(db_name):
    """Remove a database service by deleting its compose file"""
    if db_name == "db_default":
        print("Error: Cannot delete db_default (primary database in docker-compose.yml)")
        return False

    compose_file = Path(f"docker-compose.{db_name}.yml")

    if not compose_file.exists():
        print(f"Error: Database '{db_name}' not found")
        return False

    print(f"Removing database service: {db_name}")

    # Stop and remove container first (if it exists)
    import subprocess
    container_name = f"cpe241_mysql_{db_name}"

    # Check if container exists and is running
    result = subprocess.run(
        ["docker", "ps", "-a", "--filter", f"name={container_name}", "--format", "{{.Names}}"],
        capture_output=True,
        text=True
    )

    if result.stdout.strip():
        print(f"Stopping and removing container: {container_name}")
        subprocess.run(["docker", "stop", container_name], capture_output=True)
        subprocess.run(["docker", "rm", container_name], capture_output=True)
        print(f"✓ Container stopped and removed")

    # Remove compose file
    compose_file.unlink()
    print(f"✓ Removed {compose_file}")

    # Note: No need to update docker-compose.yml - phpMyAdmin uses PMA_ARBITRARY: 1

    # Remove mysql-init directory
    init_dir = Path(f"mysql-init/{db_name}")
    if init_dir.exists():
        import shutil
        shutil.rmtree(init_dir)
        print(f"✓ Removed mysql-init/{db_name}/ directory")

    # Remove from .env file if it exists
    env_file = Path(".env")
    if env_file.exists():
        with open(env_file, "r") as f:
            env_content = f.read()

        env_prefix = re.sub(r"[^a-zA-Z0-9]", "_", db_name).upper()
        env_pattern = rf"# {db_name} Configuration\n{env_prefix}_DATABASE={db_name}\n{env_prefix}_PORT=\d+\n"
        env_content = re.sub(env_pattern, "", env_content)

        with open(env_file, "w") as f:
            f.write(env_content)

    print(f"✓ Database service '{db_name}' removed")
    print(f"\nOptional cleanup:")
    print(f"  Remove volume: docker volume rm cpe241_mysql_{db_name}_data")

    return True


def main():
    databases = list_databases()

    if not databases:
        print("No databases found")
        return

    print("\nAvailable databases:")
    print("-" * 40)
    for i, db in enumerate(databases, 1):
        print(f"{i}. {db}")
    print("-" * 40)

    if len(sys.argv) > 1:
        db_name = sys.argv[1]
        if db_name not in databases:
            print(f"Error: Database '{db_name}' not found")
            sys.exit(1)
    else:
        try:
            choice = input(
                f"\nSelect database to delete (1-{len(databases)}) or 'q' to quit: "
            ).strip()

            if choice.lower() == "q":
                print("Cancelled.")
                return

            choice_num = int(choice)
            if choice_num < 1 or choice_num > len(databases):
                print("Invalid selection")
                sys.exit(1)

            db_name = databases[choice_num - 1]
        except (ValueError, KeyboardInterrupt):
            print("\nCancelled.")
            return

    confirm = (
        input(f"\nAre you sure you want to delete '{db_name}'? (yes/no): ")
        .strip()
        .lower()
    )
    if confirm not in ["yes", "y"]:
        print("Cancelled.")
        return

    success = remove_database(db_name)
    sys.exit(0 if success else 1)


if __name__ == "__main__":
    main()

# CPE241 Docker Setup

Docker environment with PHP 8.3, MySQL 8.0, and phpMyAdmin. Start with db1, then add more databases as needed.

## Prerequisites

- Docker
- Docker Compose

## Quick Start

1. **Copy environment file:**

   ```bash
   cp env.example .env
   ```

2. **Edit `.env` file** (optional - defaults work for development):

   - Change passwords for production use
   - Adjust ports if needed

3. **Start services:**

   ```bash
   docker-compose up -d
   ```

4. **Access services:**
   - PHP Application: http://localhost:8080
   - phpMyAdmin: http://localhost:8081
   - MySQL db_default: localhost:3306

## Default Credentials

- **MySQL Root:** `root` / `rootpassword`
- **MySQL User:** `cpe241_user` / `cpe241_password`
- **Database:** `db_default`

## Adding More Databases

To add additional databases with any name you want, use the `add-db.py` script:

**Note:** Run the script from the `Y2/CPE241/` directory:

```bash
cd Y2/CPE241

# Add db2 (auto-detects next available port)
python3 add-db.py db2

# Add db_test_1 with specific port
python3 add-db.py db_test_1 3307

# Add test_db (any name works!)
python3 add-db.py test_db
```

The script will:

- Create a separate `docker-compose.{db_name}.yml` file (doesn't touch main docker-compose.yml)
- Create the mysql-init directory for initialization scripts
- Update phpMyAdmin configuration to include the new database
- Update `.env` file with new database configuration

After running the script:

```bash
# Start a specific database
docker-compose -f docker-compose.yml -f docker-compose.db2.yml up -d db2

# Or start all databases at once
docker-compose -f docker-compose.yml -f docker-compose.*.yml up -d

# Access via phpMyAdmin - new server will appear in dropdown
```

**Important:** The main `docker-compose.yml` file is never modified. Each additional database gets its own compose file.

All databases are accessible through a single phpMyAdmin interface. When you access phpMyAdmin, you'll see a server selection dropdown to choose which database to manage.

## Useful Commands

```bash
# View logs
docker-compose logs -f

# Stop services
docker-compose down

# Stop and remove volumes (WARNING: deletes database)
docker-compose down -v

# Rebuild containers
docker-compose up -d --build

# Access MySQL CLI
docker exec -it cpe241_mysql_db_default mysql -u root -p

# Access PHP container shell
docker exec -it cpe241_web bash

# View logs for specific service
docker-compose logs -f db_default
```

## Managing Databases

### List and Delete Databases

Use the `delete-db.py` script to list and delete databases:

```bash
cd Y2/CPE241

# Interactive mode - lists databases and lets you select
python3 delete-db.py

# Direct deletion (non-interactive)
python3 delete-db.py db_test_1
```

The script will:
- List all available databases (from main compose file and additional files)
- Let you select which one to delete
- Delete the `docker-compose.{db_name}.yml` file (doesn't touch main docker-compose.yml)
- Update phpMyAdmin configuration
- Remove mysql-init directory
- Update `.env` file
- Provide instructions for removing containers and volumes

**Note:** `db_default` cannot be deleted (primary database in docker-compose.yml).

## Project Structure

- `Dockerfile` - PHP application container
- `docker-compose.yml` - Service orchestration
- `.env` - Environment variables (create from `env.example`)
- `add-db.py` - Script to add new database services
- `delete-db.py` - Script to list and delete database services
- `mysql-init/db_default/` - SQL scripts for db_default initialization (optional)
- `docker-compose.{db_name}.yml` - Separate compose files for each additional database
- `mysql-init/{db_name}/` - Created automatically when adding databases

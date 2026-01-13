# CPE241 Docker Setup

Simple Docker environment with MySQL 8.0 and phpMyAdmin.

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
   - phpMyAdmin: http://localhost:8081
   - MySQL: localhost:3306

## Default Credentials

- **MySQL Root:** `root` / `rootpassword`
- **MySQL User:** `cpe241_user` / `cpe241_password`
- **Database:** `db_default`

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

# Restart phpMyAdmin
docker-compose restart phpmyadmin
```

## Project Structure

- `docker-compose.yml` - Service orchestration
- `.env` - Environment variables (create from `env.example`)
- `mysql-init/db_default/` - SQL scripts for initialization (optional)

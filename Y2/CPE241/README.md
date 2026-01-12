# CPE241 Docker Setup

Docker environment with PHP 8.3, MySQL 8.0, and phpMyAdmin.

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
   - MySQL: localhost:3306

## Default Credentials

- **MySQL Root:** `root` / `rootpassword`
- **MySQL User:** `cpe241_user` / `cpe241_password`
- **Database:** `cpe241_db`

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
docker exec -it cpe241_mysql mysql -u root -p

# Access PHP container shell
docker exec -it cpe241_web bash
```

## Project Structure

- `Dockerfile` - PHP application container
- `docker-compose.yml` - Service orchestration
- `.env` - Environment variables (create from `env.example`)
- `mysql-init/` - SQL scripts to run on first startup (optional)

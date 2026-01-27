# CPE241 Docker Setup

Docker environment with PostgreSQL and pgAdmin.

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
   - pgAdmin: http://localhost:8082
   - PostgreSQL: localhost:5432

## Default Credentials

### PostgreSQL
- **User:** `postgres_user` / `postgres_password`
- **Database:** `postgres_db`

### pgAdmin
- **Email:** `admin@example.com`
- **Password:** `pgadmin_password`

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

# Access PostgreSQL CLI
docker exec -it cpe241_postgres psql -U postgres_user -d postgres_db

# Restart services
docker-compose restart pgadmin
```

## Project Structure

- `docker-compose.yml` - Service orchestration
- `.env` - Environment variables (create from `env.example`)
- `postgres-init/` - PostgreSQL SQL scripts for initialization (optional)

## Connecting pgAdmin to PostgreSQL

After starting the services:

1. Access pgAdmin at http://localhost:8082
2. Login with the credentials from `.env` (default: `admin@example.com` / `pgadmin_password`)
3. Right-click "Servers" → "Register" → "Server"
4. In the "General" tab:
   - Name: `PostgreSQL` (or any name)
5. In the "Connection" tab:
   - Host name/address: `postgres`
   - Port: `5432`
   - Username: `postgres_user` (from `.env`)
   - Password: `postgres_password` (from `.env`)
6. Click "Save"

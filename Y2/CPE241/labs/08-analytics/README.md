# Lab 08 Analytics (Docker)

This lab has:
- `prepare.sql` (schema + seed data)
- `processing/01-monthly-revenue-by-content-type.sql`
- `processing/02-quarterly-paid-orders-payment-success.sql`
- `processing/03-quarterly-best-seller-content.sql`

## New Docker compose file
Use `docker-compose.lab08.yml` to auto-run `prepare.sql` on first DB initialization.

### Start Lab 08 database
```bash
docker compose -f docker-compose.lab08.yml up -d
```

- PostgreSQL: `localhost:${LAB08_POSTGRES_PORT:-5433}`
- pgAdmin: `http://localhost:${LAB08_PGADMIN_PORT:-8083}`

### Run all 3 processing queries
```bash
./labs/08-analytics/scripts/run-processing.sh
```

### Run one query file manually
```bash
docker exec -i cpe241_postgres_lab08 psql -U postgres_user -d postgres_db -f - < labs/08-analytics/processing/01-monthly-revenue-by-content-type.sql
```

## Important note
PostgreSQL init scripts in `/docker-entrypoint-initdb.d` run **only when the data volume is empty**.
If you changed `prepare.sql` and want re-init:

```bash
docker compose -f docker-compose.lab08.yml down -v
docker compose -f docker-compose.lab08.yml up -d
```

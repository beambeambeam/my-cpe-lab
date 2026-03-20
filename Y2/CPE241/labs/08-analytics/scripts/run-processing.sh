#!/usr/bin/env bash
set -euo pipefail

COMPOSE_FILE="docker-compose.lab08.yml"
CONTAINER="cpe241_postgres_lab08"
DB_USER="${POSTGRES_USER:-postgres_user}"
DB_NAME="${POSTGRES_DB:-postgres_db}"
PROCESSING_DIR="labs/08-analytics/processing"

if ! docker ps --format '{{.Names}}' | grep -q "^${CONTAINER}$"; then
  echo "Container ${CONTAINER} is not running."
  echo "Start it first: docker compose -f ${COMPOSE_FILE} up -d"
  exit 1
fi

for sql_file in \
  "${PROCESSING_DIR}/01-monthly-revenue-by-content-type.sql" \
  "${PROCESSING_DIR}/02-quarterly-paid-orders-payment-success.sql" \
  "${PROCESSING_DIR}/03-quarterly-best-seller-content.sql"
do
  echo ""
  echo "=== Running ${sql_file} ==="
  docker exec -i "${CONTAINER}" psql -U "${DB_USER}" -d "${DB_NAME}" -f - < "${sql_file}"
done

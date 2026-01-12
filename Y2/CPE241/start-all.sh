#!/bin/bash

# Start all services including additional databases
# Usage: ./start-all.sh

# Update phpMyAdmin config if script exists
if [ -f "update-phpmyadmin.py" ]; then
    python3 update-phpmyadmin.py > /dev/null 2>&1
fi

# Start all services
docker-compose -f docker-compose.yml \
    $(if [ -f docker-compose.phpmyadmin-override.yml ]; then echo "-f docker-compose.phpmyadmin-override.yml"; fi) \
    $(ls docker-compose.*.yml 2>/dev/null | grep -v docker-compose.yml | grep -v docker-compose.phpmyadmin-override.yml | sed 's/^/-f /' | tr '\n' ' ') \
    up -d

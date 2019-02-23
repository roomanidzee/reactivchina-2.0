#!/bin/bash
set -e

echo "Собираем reactivchina-producer..."
echo ""

cd reactivchina-producer
gradle clean bootJar
cd ..
echo ""

echo "Собираем reactivchina-consumer..."
echo ""

cd reactivchina-consumer
gradle clean bootJar
cd ..

echo ""

echo "Начинаем сборку docker-compose..."

docker-compose -f scripts/docker-compose.yml build --no-cache
docker-compose -f scripts/docker-compose.yml up -d 
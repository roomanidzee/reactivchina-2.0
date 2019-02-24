#!/bin/bash
set -e

echo ""

echo "Начинаем сборку docker-compose..."

docker-compose -f scripts/docker-compose.yml build --no-cache
docker-compose -f scripts/docker-compose.yml up -d

echo "Адрес Cassandra: " "$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' cassandra-service)"
echo "Адрес Kafka: " "$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kafka-service)"
echo "Эти адреса добавляете в application.yml для настройки Kafka и Cassandra"
echo ""

echo "Перед запуском: нужно подключиться к Cassandra, и выполнить вот такую команду: "
echo "CREATE KEYSPACE IF NOT EXISTS demo WITH replication = {'class':'SimpleStrategy','replication_factor':'1'};"
echo ""

echo "Как собрать producer: cd reactivchina-producer && ./gradlew clean bootJar"
echo "Как собрать consumer: cd reactivchina-consumer && ./gradlew clean bootJar"
#!/bin/bash
set -e

echo ""

echo "Начинаем сборку docker-compose..."

docker-compose -f scripts/docker-compose.yml build --no-cache
docker-compose -f scripts/docker-compose.yml up -d

echo ""
echo "Адрес Cassandra: " "$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' cassandra-service)"
echo "Адрес Kafka: " "$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' kafka-service)"
echo "Эти адреса добавляете в application.yml для настройки Kafka и Cassandra"
echo ""

echo "Важно: для подключения к Cassandra нужно создать keyspace".
echo "Команды для этого: "
echo "docker exec -it cassandra-service bash"
echo "cqlsh *ip контейнера с Cassandra*"
echo "CREATE KEYSPACE IF NOT EXISTS reactivekeyspace WITH replication = {'class':'SimpleStrategy','replication_factor':'1'};"

echo ""

echo "Как собрать producer: cd reactivchina-producer && ./gradlew clean bootJar"
echo "Как собрать consumer: cd reactivchina-consumer && ./gradlew clean bootJar"
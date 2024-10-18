#!/bin/bash

echo -e "Spring Boot & Redis & Docker kurulumları başlıyor..."

java --version
javac --version

mvn clean package -DskipTests

# 1.YOL
docker-compose up

# 2.YOL
# docker run --name redis -p 6379:6379 -d redis
# docker exec -it redis redis-cli
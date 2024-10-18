#!/bin/bash

echo -e "Spring Boot & Redis & Docker kurulumları başlıyor..."

# Sürümler
java --version
javac --version

# Deployment Package JAR
mvn clean package -DskipTests

# Redis
docker container run --name spring_redis -p 6379:6379 -d redis

# 1.YOL
docker-compose up
# docker-compose up -d

# 2.YOL
# docker run --name redis -p 6379:6379 -d redis
# docker exec -it redis redis-cli
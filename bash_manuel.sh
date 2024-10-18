#!/bin/bash

echo -e "Spring Boot & Redis & Docker kurulumları başlıyor..."

java --version
javac --version

mvn clean package -DskipTests
docker-compose up
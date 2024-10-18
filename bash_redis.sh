#!/bin/bash


# Projemizin sağlıklı çalışması için öncelikler redis servera kurulu olması gerekiyor
docker container run --name spring_redis -p 6379:6379 -d redis
# docker exec -it spring_redis
# docker exec -it spring_redis redis-cli

# Spring Boot projesini çalıştır.
mvn spring-boot:run
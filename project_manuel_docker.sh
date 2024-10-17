#!/bin/bash

# 2.YOL
docker run --name redis -p 6379:6379 -d redis
docker exec -it redis redis-cli
#!/bin/bash

echo Start MySql docker
#docker container stop $(docker container ls -a -q); docker system prune --all --force --volumes; docker container rm $(docker container ls -a -q); docker image rm -f $(docker image ls -a -q)
#docker build -f ./docker/Dockerfile -t mysql-book .
#docker run -d -p 3317:3306 --name mysql-book  -e MYSQL_ROOT_PASSWORD=ZmhyJDMhZmVHc0V0SEc1clR1Mgo= mysql-book

docker run -e MYSQL_ROOT_PASSWORD=fdske4rwHFDGtrzr71e53 --name mysql-1 -d -p=3306:3306 mysql
docker logs mysql-book
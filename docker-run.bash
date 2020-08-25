#!/bin/bash

docker container stop $(docker container ls -a -q)
docker container rm $(docker container ls -a -q)
#docker rmi $(docker images -a -q)

echo Start creating custom image
docker build -t mysql01 .

echo Start build container
docker run --name db -p 3306:3306 -d mysql01

docker ps
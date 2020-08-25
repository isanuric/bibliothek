FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD fdske4rwHFDGtrzr71e53
ENV MYSQL_DATABASE bibdb
#ENV MYSQL_CONTAINER_NAME=mysql-7

ADD /docker/scripts-all.sql /docker-entrypoint-initdb.d

EXPOSE 3306
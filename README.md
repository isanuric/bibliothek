# Scope
* Spring Boot
* Spring Security
* Thymleaf
* Minikube
* MySql
* Docker


# Quick Start
* clone repository
* $ mvn clean install


# MySQL Database in Docker
MySQL intro: https://dev.mysql.com/doc/refman/8.0/en/tutorial.html

Execute the following commands to build and run mysql-book container:
* $ docker build -t mysql-book .  
* $ docker run -d -p 3317:3306 --name mysql-book  -e MYSQL_ROOT_PASSWORD=ZmhyJDMhZmVHc0V0SEc1clR1Mgo= mysql-book

# Branches

# Deployment
## Spring 
* $ mvn spring-boot:run
* http://localhost:8080/


## Minikube (Kubernetes v1.14.3)
* Download latest [minikube](https://kubernetes.io/docs/setup/minikube/) version 
* $ mvn clean install
* $ minikube start 
* $ eval $(minikube docker-env) 
* $ kubectl apply -f  kubernetes/mysql/deployment.yaml
* $ kubectl apply -f  kubernetes/mysql/mysql-persistent-volume.yaml 
* $ kubectl describe deployment mysql
* $ minikube service bar --url  
* $ click on result of last step and add _/index_ to path: <MINIKUBE-SERVICE-URL:PORT>/index
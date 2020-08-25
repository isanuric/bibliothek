
# Scope
* Spring Boot
* Spring Security
* Thymleaf
* MySql
* Docker
* Minikube


# Quick Start

### Docker
* `$ chmod +x docker-run.bash`
* `$ ./docker-run.bash`
* `$ mvn clean install`
* `$ mvn spring-boot:run`
* http://localhost:8090/


# MySql

#### Run MySql as Docker:


* MySql root password: fdske4rwHFDGtrzr71e53
* Port: 3306
* Host: 0.0.0.0    
* Database: bibdb

`docker run -e MYSQL_ROOT_PASSWORD=fdske4rwHFDGtrzr71e53 --name mysql-1 -d -p=3306:3306 mysql`

#### Tables:
You can update and customise tables that are located in `/docker/scripts-all.sql`.

# Deployment

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
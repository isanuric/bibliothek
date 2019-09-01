# Scope
* Spring Boot
* Spring Security
* Thymleaf
* Minikube
* MySql


# Quick Start
* clone repository
* $ mvn clean install

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
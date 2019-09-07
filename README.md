# Scope
* Spring Boot
* Spring Security
* Thymleaf
* Minikube
* MySql
* Docker


# Quick Start
* Clone repository
* $ chmod +x run-docker.bash  
* $ ./run-docker.bash -> (It runs MySQL Database in Docker)
* $ mvn clean install
* $ mvn spring-boot:run
* http://localhost:8080/


# Branches
* **master**: used in-memory authentication
* **jdbc-authentication**: used jdbc authentication 

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
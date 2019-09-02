# Scope
* Spring Boot
* Spring Security
* Thymleaf
* Minikube
* MySql


# Quick Start
* clone repository
* $ mvn clean install

# MySql
MySQL intro: https://dev.mysql.com/doc/refman/8.0/en/tutorial.html

##### Creation
* mysql -u root -p
* use the password who is defind in application.properties: **(Mmmrs()=890** 

##### Create a data base
* mysql> CREATE DATABASE book_database;
* mysql> USE book_database;

##### Create a table in your data base
* mysql>  CREATE TABLE book002 ( id int(11) NOT NULL AUTO_INCREMENT, name varchar(80) NOT NULL, autor varchar(80) NOT NULL, iban int(30) NOT NULL, status int (10) NOT NULL DEFAULT 1, timestamp TIMESTAMP, PRIMARY KEY (id) );

##### add some data to your table
* mysql> INSERT INTO BOOK002 (id,name,autor,iban)
    VALUES
        (12, "Die Geburt der Tragödie aus dem Geiste der Musik","Friedrich Wilhelm Nietzsche", 97842),
        (13, "Über Wahrheit und Lüge im außermoralischen Sinne","Friedrich Wilhelm Nietzsche", 97843),
        (14, "Die Philosophie im tragischen Zeitalter der Griechen","Friedrich Wilhelm Nietzsche", 97844),
        (15, "Unzeitgemäße Betrachtungen","Friedrich Wilhelm Nietzsche", 97845),
        (16, "Die Geburt der Tragödie aus dem Geiste der Musik","Friedrich Wilhelm Nietzsche", 97846),
        (17, "Menschliches, Allzumenschliches – Ein Buch für freie Geister.","Friedrich Wilhelm Nietzsche", 97847),
        (18, "Der Wanderer und sein Schatten","Friedrich Wilhelm Nietzsche", 97848),
        (19, "Morgenröte. Gedanken über die moralischen Vorurteile","Friedrich Wilhelm Nietzsche", 97849),
        (20, "Also sprach Zarathustra – Ein Buch für Alle und Keinen","Friedrich Wilhelm Nietzsche", 97850),
        (21, "Jenseits von Gut und Böse – Vorspiel einer Philosophie der Zukunft","Friedrich Wilhelm Nietzsche", 97851),
        (22, "Zur Genealogie der Moral – Eine Streitschrift","Friedrich Wilhelm Nietzsche", 97852),
        (23, "Der Fall Wagner – Ein Musikanten-Problem","Friedrich Wilhelm Nietzsche", 97853),
        (24, "Dionysos-Dithyramben","Friedrich Wilhelm Nietzsche", 97854),
        (25, "Götzen-Dämmerung oder Wie man mit dem Hammer philosophiert","Friedrich Wilhelm Nietzsche", 97855),
        (26, "Der Antichrist – Fluch auf das Christentum","Friedrich Wilhelm Nietzsche", 97856),
        (27, "Nietzsche contra Wagner","Friedrich Wilhelm Nietzsche", 97857),
        (28, "Ecce homo – Wie man wird, was man ist","Friedrich Wilhelm Nietzsche", 97858);

##### Check your table
* mysql> SELECT * FROM book002

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
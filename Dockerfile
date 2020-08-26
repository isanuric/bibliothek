FROM java:8

EXPOSE 8090

ADD target/bibliothek-0.0.1-SNAPSHOT.jar bibliothek.jar

# Run
ENTRYPOINT ["java", "-jar", "bibliothek.jar"]
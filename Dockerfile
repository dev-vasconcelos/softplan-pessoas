FROM openjdk
COPY target/pessoas-0.0.1-SNAPSHOT.jar pessoas-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pessoas-0.0.1-SNAPSHOT.jar"]
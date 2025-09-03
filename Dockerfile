FROM openjdk:21-jdk

COPY build/libs/*SNAPSHOT.jar /jenson-server.jar

ENTRYPOINT ["java", "-jar", "jenson-server.jar"]

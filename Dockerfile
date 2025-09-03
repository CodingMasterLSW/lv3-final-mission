FROM openjdk:21-jdk

ARG SPRING_ACTIVE_PROFILE
ENV PROFILE=${SPRING_ACTIVE_PROFILE}

COPY build/libs/*SNAPSHOT.jar /jenson-server.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "jenson-server.jar"]

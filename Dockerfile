FROM openjdk:13
EXPOSE 8080
ADD target/bookstore-docker.jar bookstore-docker.jar
ENTRYPOINT ["java", "-jar", "/bookstore-docker.jar"]
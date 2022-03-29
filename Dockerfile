FROM openjdk:11
COPY target/spring-service-converter.jar converter.jar
ENTRYPOINT ["java","-jar","converter.jar"]
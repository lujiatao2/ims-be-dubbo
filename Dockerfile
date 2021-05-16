FROM openjdk:8-jdk-alpine
ARG MODULE
COPY ${MODULE}/target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
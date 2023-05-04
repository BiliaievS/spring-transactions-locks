FROM openjdk:17
VOLUME /tmp
COPY target/*.jar demo-app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
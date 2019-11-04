FROM openjdk:8
EXPOSE 8080
ADD target/contactos.jar contactos.jar
ENTRYPOINT ["java","-jar","/contactos.jar"]
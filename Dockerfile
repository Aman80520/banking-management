FROM openjdk:8
EXPOSE 8080
ADD target/banking-mangement.jar banking-mangement.jar
ENTRYPOINT ["java", "-jar", "/banking-mangement.jar"]

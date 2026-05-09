FROM dockerhub.timeweb.cloud/maven:3.8.3-openjdk-17

COPY . .

RUN mvn clean package -B

RUN cp target/*.jar /api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/api.jar"]
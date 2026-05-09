FROM dockerhub.timeweb.cloud/maven:3.8.3-openjdk-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -B -DskipTests

FROM dockerhub.timeweb.cloud/eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN addgroup -S user  && adduser -S user -G user

COPY --from=build /build/target/*.jar /app/api.jar

USER user

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/api.jar"]


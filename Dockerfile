FROM maven:3.8.6-openjdk-18 as maven_build
WORKDIR /app
COPY ./ .
RUN --mount=type=cache,target=/root/.m2  mvn clean package -f ./pom.xml

FROM openjdk:18
WORKDIR /app
COPY --from=maven_build /app/product-service/target/product-service-0.0.1-SNAPSHOT.jar ./target/product-service-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app/target/product-service-0.0.1-SNAPSHOT.jar"]
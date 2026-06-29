FROM eclipse-temurin:21-jre

WORKDIR /app

COPY build/libs/productservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 7500

ENTRYPOINT ["java", "-jar", "app.jar"]
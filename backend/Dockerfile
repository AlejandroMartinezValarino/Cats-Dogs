# Build
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package \
    && JAR=$(find target -name "pet-gallery-backend-*.jar" ! -name "*-sources.jar" ! -name "*-original.jar" | head -1) \
    && cp "$JAR" /app/app.jar

# Run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/app.jar /app/app.jar
EXPOSE 8082
ENV SERVER_PORT=8082
ENTRYPOINT ["sh", "-c", "exec java -jar /app/app.jar"]

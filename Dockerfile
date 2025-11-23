FROM gradle:8-jdk17 AS builder
WORKDIR /app
COPY . .
# Build the Spring Boot fat jar
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
EXPOSE 8080
# Copy the packaged jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]

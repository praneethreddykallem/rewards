# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copy only pom.xml first for dependency caching
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source and build the jar
COPY src ./src
RUN mvn -B -q clean package -DskipTests


# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy only the built artifact from the builder image
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

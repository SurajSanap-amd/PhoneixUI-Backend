# -------- 1. Build stage --------
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (better cache)
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Now copy the source code
COPY src ./src

# Build the application (skip tests if you want faster build)
RUN mvn -q clean package -DskipTests

# -------- 2. Runtime stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (for local clarity; Render chooses its own internal port but it's fine)
EXPOSE 8080

# H2 DB + backup dirs (so they exist inside the container)
RUN mkdir -p /app/data /app/backup

# Set environment for H2 file path to be inside container
ENV SPRING_PROFILES_ACTIVE=h2
ENV JAVA_OPTS=""

# If you want, you can also override datasource via env in Render later.
# ENTRYPOINT: JVM args + jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

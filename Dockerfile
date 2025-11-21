FROM amazoncorretto:21

WORKDIR /app

# Copy ANY jar from target and name it app.jar
COPY target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]

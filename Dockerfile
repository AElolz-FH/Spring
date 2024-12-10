FROM openjdk:17-jdk-slim
ARG JAR_FILE=TP/target/TP-0.0.1-SNAPSHOT.jar

COPY out/artifacts/TP_jar/TP.jar app.jar
RUN mkdir -p /app/data && chmod -R 777 /app/data
EXPOSE 9999
VOLUME ["/app/data"]

ENTRYPOINT ["java", "-jar", "/app.jar"]

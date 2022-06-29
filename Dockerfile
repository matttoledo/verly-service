FROM amd64/openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=70","-jar","/app.jar"]
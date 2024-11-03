FROM openjdk:23-oracle
COPY target/blog-*.*.*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
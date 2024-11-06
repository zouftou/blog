mvn clean
mvn -Dmaven.test.skip -Dspring.profile.active=prod package
docker build -t blog:latest .
docker compose up -d
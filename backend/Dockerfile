FROM maven:3.8.3-openjdk-17

WORKDIR /app
COPY . .

# Activate the spring profile
#ENV MAVEN_OPTS="-Dspring-boot.run.fork=false"

CMD mvn spring-boot:run

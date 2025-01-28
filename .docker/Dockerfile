# Stage 1: Build the application
FROM maven:3.9.0-eclipse-temurin-17 AS build
# Set build arguments
ARG POM_VERSION

# Set environment variables
ENV POM_VERSION=${POM_VERSION}

WORKDIR /app

# Passe le répertoire du service comme argument
#ARG SERVICE_DIR
#COPY ${SERVICE_DIR}/pom.xml .
#COPY ${SERVICE_DIR}/src ./src

#RUN echo "Files copied to /usr/src/app:" && ls -la /app
#
## Alternatively, use tree for a hierarchical view
#RUN apt-get update && apt-get install -y tree && tree /app
COPY pom.xml .
COPY src ./src
RUN mvn versions:set -DnewVersion=${POM_VERSION} && mvn clean package -DskipTests


# Stage 2: Create the runtime container
FROM openjdk:17-jdk-slim
# Create a non-root user
RUN useradd -m -s /bin/bash webadm
WORKDIR /app
COPY --from=build /app/target/gestion-message-0.0.1-SNAPSHOT.jar app.jar
# Set permissions for the non-root user
RUN chown -R webadm:webadm /app && chmod 755 /app
# Switch to the non-root user
USER webadm
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
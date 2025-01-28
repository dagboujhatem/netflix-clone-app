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
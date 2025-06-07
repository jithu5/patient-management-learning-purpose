# -------- Stage 1: Build --------
FROM eclipse-temurin:21-jdk AS builder

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

COPY pom.xml .

COPY src ./src

# Trigger plugin download (and partial build)
RUN mvn generate-sources || true
# Make the plugin executable
RUN chmod +x /app/target/protoc-plugins/protoc-gen-grpc-java-*.exe

# Now build properly
RUN mvn clean package -DskipTests

# -------- Stage 2: Run --------
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder ./app/target/ ./app

# Run the JAR
ENTRYPOINT ["java", "-jar", "app/billing-service-0.0.1-SNAPSHOT.jar"]

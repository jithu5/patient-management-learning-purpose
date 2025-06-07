# -------- Stage 1: Build --------
FROM eclipse-temurin:21-jdk-alpine AS builder

# Install Maven
RUN apk add --no-cache maven

# Set working directory
WORKDIR /app

COPY . .

# Build the JAR
RUN mvn clean package -DskipTests

# -------- Stage 2: Run --------
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder ./app/target/ ./app

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.PatientManager-0.0.1-SNAPSHOT.jar"]

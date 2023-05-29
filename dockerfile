# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine as build

# Set the current working directory in the image
WORKDIR /app

# Copy the Gradle build file into the image
COPY build.gradle.kts gradlew ./

# Copy the gradle wrapper files
COPY gradle gradle

# Copy the source code
COPY src src

# Build the application
RUN ./gradlew bootJar

# Start a new stage to keep the final image clean and small
FROM openjdk:17-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the jar file from the build stage into this stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

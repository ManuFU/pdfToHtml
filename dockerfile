# Start with a base image containing Java runtime
FROM openjdk:17-jdk

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/pdfToHtmlKotlin-0.0.1-SNAPSHOT-plain.jar

# Add the application's jar to the container
ADD ${JAR_FILE} pdfToHtmlKotlin.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pdfToHtmlKotlin.jar"]

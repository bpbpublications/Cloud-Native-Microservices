# Base Image
FROM amazoncorretto:17-alpine-jdk

# Create application directory
RUN mkdir config-server

# Set application directory as work directory
WORKDIR /config-server

# Create log directory inside config-server
RUN mkdir log

# Copy last built config server executable jar to application directory and rename it to config-server.jar
COPY target/config-server*.jar config-server.jar

# Copy entry point script to config-server
COPY ConfigServerEntryPoint.sh ConfigServerEntryPoint.sh

# Convert entrypoint script in unix format
RUN dos2unix ConfigServerEntryPoint.sh

# Give executable permissions on entry point script
RUN chmod +x ConfigServerEntryPoint.sh

# Set the entry point script as ENTRYPOINT
ENTRYPOINT ["/config-server/ConfigServerEntryPoint.sh"]
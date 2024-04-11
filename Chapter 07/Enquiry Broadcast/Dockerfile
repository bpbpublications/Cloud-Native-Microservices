# Base Image
FROM amazoncorretto:17-alpine-jdk

# Create application directory
RUN mkdir enquiry-broadcast

# Set application directory as work directory
WORKDIR /enquiry-broadcast

# Create log directory inside enquiry-broadcast
RUN mkdir log

# Copy last built enquiry broadcast executable jar to application directory and rename it to enquiry-broadcast.jar
COPY target/enquiry-broadcast*.jar enquiry-broadcast.jar

# Copy entry point script to enquiry-broadcast
COPY EnquiryBroadcastEntryPoint.sh EnquiryBroadcastEntryPoint.sh

# Convert entrypoint script in unix format
RUN dos2unix EnquiryBroadcastEntryPoint.sh

# Give executable permissions on entry point script
RUN chmod +x EnquiryBroadcastEntryPoint.sh

# Set the entry point script as ENTRYPOINT
ENTRYPOINT ["/enquiry-broadcast/EnquiryBroadcastEntryPoint.sh"]
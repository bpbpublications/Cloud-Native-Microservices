# Base Image
FROM amazoncorretto:17-alpine-jdk

# Create application directory
RUN mkdir customer-enquiry

# Set application directory as work directory
WORKDIR /customer-enquiry

# Create log directory inside customer-enquiry
RUN mkdir log

# Copy last built customer enquiry executable jar to application directory and rename it to customer-enquiry.jar
COPY target/customer-enquiry*.jar customer-enquiry.jar

# Copy entry point script to customer-enquiry
COPY CustomerEnquiryEntryPoint.sh CustomerEnquiryEntryPoint.sh

# Convert entrypoint script in unix format
RUN dos2unix CustomerEnquiryEntryPoint.sh

# Give executable permissions on entry point script
RUN chmod +x CustomerEnquiryEntryPoint.sh

# Set the entry point script as ENTRYPOINT
ENTRYPOINT ["/customer-enquiry/CustomerEnquiryEntryPoint.sh"]
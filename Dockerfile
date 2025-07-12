# Use official Tomcat 10.1 base image with JDK 17
FROM tomcat:10.1-jdk17

# Remove default webapps (optional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file as ROOT.war (autodeploys to /)
COPY ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]

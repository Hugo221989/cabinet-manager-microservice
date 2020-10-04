FROM tomcat:8.5.20-jre8-alpine
USER root
ADD target/cabinet-manager /usr/local/tomcat/webapps/
EXPOSE 8083
VOLUME /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
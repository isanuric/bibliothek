FROM tomcat:9.0-jre8-alpine

EXPOSE 8095
#CMD ["catalina.sh", "run"]
#COPY target/wizard*.war $CATALINA_HOME/webapps/wizard.war
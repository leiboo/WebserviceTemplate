FROM 172.21.9.22/istuary/tomcat:7.0.73-jre8

MAINTAINER zhenhua.li

# RUN set -x \
#    && apt-get update \
#    && apt-get install -y --no-install-recommends openssh-server \
#    && rm -rf /var/lib/apt/lists/*

COPY ./shell/catalina.sh /usr/local/tomcat/bin/
COPY ./shell/kubectl /usr/bin/
COPY ./conf/server.xml /usr/local/tomcat/conf/
COPY ./conf/context.xml /usr/local/tomcat/conf/

#ADD resource
RUN mkdir -p /opt/webservice_template/log \
    mkdir -p /opt/webservice_template/tmp \
    && chmod -R 777 /opt/webservice_template/
#COPY ./output/ /opt/webservice_template/

RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./output/webserviceTemplate-api.war /usr/local/tomcat/webapps/ROOT.war

COPY ./shell/start.sh /opt/webservice_template/
COPY ./shell/readiness.sh /opt/webservice_template/

EXPOSE  8080 22

#CMD /usr/sbin/sshd -D
CMD bash /opt/webservice_template/start.sh

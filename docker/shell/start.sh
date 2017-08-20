#!/bin/bash

chmod -R 777 /opt
chmod 755 /usr/bin/kubectl

chmod +x /usr/local/tomcat/bin/catalina.sh

/usr/local/tomcat/bin/catalina.sh run

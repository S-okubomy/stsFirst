#!/bin/sh

cd /var/lib/tomcat7/webapps/rcgaBatch1/WEB-INF

java -cp ./lib/*:./classes: rcgaBatch1.batch.MainRcga

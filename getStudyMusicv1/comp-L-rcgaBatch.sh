#!/bin/sh


cd /var/lib/tomcat7/webapps/rcgaBatch1/WEB-INF/

javac -cp .:lib/*: classes/rcgaBatch1/batch/MainRcga.java classes/rcgaBatch1/batch/CalFit.java classes/rcgaBatch1/batch/Convert1.java classes/rcgaBatch1/batch/CrossBLX.java classes/rcgaBatch1/batch/InitGa.java classes/rcgaBatch1/batch/Mutation.java classes/rcgaBatch1/batch/ProbaSelecct.java classes/rcgaBatch1/batch/SelectR.java classes/rcgaBatch1/CaluculateDateUtil.java classes/rcgaBatch1/entity/*.java classes/rcgaBatch1/service/*.java classes/rcgaBatch1/entity/*.java classes/rcgaBatch1/dto/*.java


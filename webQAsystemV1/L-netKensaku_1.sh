#!/bin/sh

current_dir=$(cd $(dirname $0) && pwd)
#makeClass_dir = $current_dir"/src/main/webapp/WEB-INF/classes"
#lib_dir = $current_dir/src/main/webapp/WEB-INF/lib"

#cd $makeClass_dir

echo $current_dir

#set classPh = $current_dir/lib/:$current_dir/classes/

#cd /var/lib/tomcat7/webapps/rcgaBatch1/WEB-INF/classes


BATCH_LIBRARY_PATH=../lib

echo $BATCH_CONFIG_PATH

cd src/main/webapp/WEB-INF/classes

pwd
ls -l




echo "java -cp "${CLASS_PASS}:" rcgaBatch1.batch.TestNGramOut"
#java -cp .:../lib/*: rcgaBatch1.batch.TestNGramOut

java -cp .:../lib/* rcgaBatch1.batch.TestNGramOut

#java -cp $classPh rcgaBatch1.batch.TestNGramOut

#!/bin/sh

#change dir
cd `dirname $0`
if [[ `pwd` == *\/docker ]]; then
    # if is docker folder, go to father folder
    cd ..
fi

#build with gradle
gradle clean packageDevWar || echo 'you need install Gradle at first, or check the error output.'

rm -rf docker/output/resource/
mkdir -p docker/output/resource/
cp sub-webapp/build/libs/webserviceTemplate-api.war docker/output/webserviceTemplate-api.war
#cp -r resource/ docker/output/

#!/bin/bash

#change dir
cd `dirname $0`
if [[ `pwd` == *\/docker ]]; then
    # if is docker folder, go to father folder
    cd ..
fi

cd docker

source build_image_name.sh

#run
tomcat_port=8081
docker run -d -p "$tomcat_port":8080 \
 -l webservice_template_run \
  -v /opt/webservice_template/volume:/opt/webservice_template/volume \
 "$WEBSERVICE_IMAGE_NAME" \
  /bin/bash /opt/webservice_template/start.sh

echo "webservice_template is running. tomcat port is $tomcat_port."


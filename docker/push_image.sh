#!/bin/bash

source build_image_name.sh

docker push "$WEBSERVICE_IMAGE_NAME"

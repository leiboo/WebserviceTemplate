#!/bin/bash

if [ ! -n "$WEBSERVICE_IMAGE_NAME" ]; then
  export WEBSERVICE_IMAGE_NAME=172.21.9.22/library/webservice_template:v1.0.001
fi


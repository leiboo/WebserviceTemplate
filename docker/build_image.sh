#!/bin/bash

source build_image_name.sh

sudo docker build -t="$WEBSERVICE_IMAGE_NAME" .

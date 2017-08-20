#!/bin/bash

docker stop $(docker ps -q -f "label=webservice_template_run")

echo "webservice_template is stopped."

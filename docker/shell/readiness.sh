#!/bin/bash


OUTPUT_FILE=/tmp/noResult
rm -f $OUTPUT_FILE

STATUS=`curl -o $OUTPUT_FILE -s -w %{http_code} 127.0.0.1:8080/heartbeat`
if [ $STATUS = "200" ]; then
    echo "ok"
    exit 0
else
    echo "not ok"
    exit 1
fi
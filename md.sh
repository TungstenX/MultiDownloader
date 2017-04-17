#!/bin/bash
nohup java -server -XX:CompileThreshold=2 -XX:+AggressiveOpts -XX:+UseFastAccessorMethods -jar MultiDownloader.jar --url=$1 >download.log 2>&1 &
tail -f download.log


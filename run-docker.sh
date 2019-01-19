#!/bin/bash

docker rm korturl
docker run -p 8083:8083 -t korturl
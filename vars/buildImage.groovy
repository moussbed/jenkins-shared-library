#!/usr/bin/env groovy

def call(){
    echo 'Building docker image ....'
    sh  "docker build -t $IMAGE_NAME ."
}
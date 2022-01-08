#!/usr/bin/env groovy

def call(String imageName){
    echo 'Building docker image ....'
    sh  "docker build -t $imageName ."
}
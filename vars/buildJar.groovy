#!/usr/bin/env groovy

def call(){
    echo 'Building jar artefact ....'
    sh 'mvn package'
}
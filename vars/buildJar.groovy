#!/usr/bin/env groovy

def call(){
    echo "Building jar artefact for $BRANCH_NAME branch ...."
    sh 'mvn package'
}
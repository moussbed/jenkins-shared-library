#!/usr/bin/env groovy

def call(){
    echo "Building jar artefact for ${env.BRANCH_NAME} branch ...."
    sh 'mvn package'
}

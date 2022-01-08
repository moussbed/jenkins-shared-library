package com.mb

class Docker implements Serializable {
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo 'Building docker image ....'
        script.sh  "docker build -t $imageName ."
    }

    def pushDockerImage(String imageName){
        script.echo 'Pushing docker image to docker hub repository ....'
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){
            script.sh "echo script.$PASS | docker login -u script.$USER --password-stdin " // Use '' to prevent Interpolation of sensitive environment variables
            script.sh "docker push $imageName"
        }
    }
}

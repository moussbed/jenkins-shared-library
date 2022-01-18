package com.mb

class Docker implements Serializable {
    def script
    def latest

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo 'Building docker image ....'
        def latest= imageName.split(":")[0]+":latest"
        script.sh  "docker build -t $imageName ."
        script.sh  "docker tag $imageName  $latest "
    }

    def dockerLogin(){
        script.echo 'Logging to docker hub repository ....'
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){
            script.sh ('echo $PASS | docker login -u $USER --password-stdin') // Use '' to prevent Interpolation of sensitive environment variables
        }
    }

    def pushDockerImage(String imageName){
        script.echo 'Pushing docker image to docker hub repository ....'
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){
            script.sh "docker push $imageName"
            script.sh "docker push $latest"
        }
    }


}

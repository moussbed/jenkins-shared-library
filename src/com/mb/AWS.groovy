package com.mb

class AWS implements  Serializable{

    def script

    AWS(script) {
        this.script = script
    }

    def dockerLoginECR(){
        script.echo 'Logging to ECR registry ....'
        script.withCredentials([script.usernamePassword(credentialsId: 'ecr-credentials',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){
            script.sh ('echo $PASS | docker login --username $USER --password-stdin  ${script.env.$REPO_SERVER}') // Use '' to prevent Interpolation of sensitive environment variables
        }
    }

    def pushDockerImageECR(String imageName){
        script.echo 'Pushing docker image to ECR registry ....'
        script.withCredentials([script.usernamePassword(credentialsId: 'ecr-credentials',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){

            def latestImage= imageName.split(":")[0]+":latest"
            script.sh "docker tag $imageName  $latestImage "
            script.sh "docker push $imageName"
            script.sh "docker push $latestImage"
        }
    }
}
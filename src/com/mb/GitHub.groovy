package com.mb

class GitHub implements Serializable{

    def script

    GitHub(script) {
        this.script = script
    }

    def commitVersionUpdate(){
        script.echo 'Commit version update ...'
        script.withCredentials([script.usernamePassword(credentialsId: 'Github-Credential',
                passwordVariable: 'PASS',
                usernameVariable: 'USER'
        )]){
            script.sh 'git config user.email "jenkins@example.com"'
            script.sh 'git config user.name "jenkins"'
            script.sh 'git config --global --unset http.proxy'
            script.sh 'git config --global --unset https.proxy'

            script.sh 'git status'
            script.sh 'git branch'
            script.sh 'git config --list'

            script.sh ('git remote set-url origin https://$USER:$PASS@github.com/moussbed/java-mvn-app.git')
            script.sh 'git add .'
            script.sh 'git commit -m "ci: version bump"'
            script.sh 'git push origin HEAD:jenkins-jobs'
        }
    }
}

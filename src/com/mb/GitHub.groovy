package com.mb

class GitHub implements Serializable{

    def script

    GitHub(script) {
        this.script = script
    }

    def commitVersionUpdate(){
        script.echo 'Commit version update ...'
        script.withCredentials([script.sshUserPrivateKey(credentialsId: 'Github-push-bump',
                keyFileVariable: 'keyfile'
        )]){
            script.sh 'mkdir -p ~/.ssh && cp ${keyfile} ~/.ssh/id_rsa'
            script.sh 'git remote -v'
            script.sh 'git show-ref'

            // Change https:// to SSH URL so we can push with a deploy key
            script.sh 'git remote set-url origin `git remote get-url origin | sed -re "s%.+/([^/]+)/([^/]+)$%git@github.com:\\1/\\2%"`'

            script.sh 'git log -p -2'

            script.sh 'git add .'
            script.sh 'git commit -m "ci: version bump"'
            script.sh 'git push origin HEAD:jenkins-jobs'
        }
    }
}

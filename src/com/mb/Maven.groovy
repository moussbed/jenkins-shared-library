package com.mb

class Maven implements  Serializable{
    def script

    Maven(script) {
        this.script = script
    }

    def incrementVersion(){
        script.echo 'Increment app version ....'
        script.sh 'mvn build-helper:parse-version versions:set \
              -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
        def matcher = script.readFile('pom.xml') =~ '<version>(.+)</version>'
        def version = matcher[0][1]
        script.env.IMAGE_VERSION= "${version}-${script.env.BUILD_NUMBER}"
    }
    def buildJar(){
        script.echo "Building jar artefact for ${script.env.BRANCH_NAME} branch ...."
        script.sh 'mvn clean package'
    }

}

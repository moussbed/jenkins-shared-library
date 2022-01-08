#!/usr/bin/env groovy
import  com.mb.Docker

def call(String imageName){
    new Docker(this).buildDockerImage(imageName)
}
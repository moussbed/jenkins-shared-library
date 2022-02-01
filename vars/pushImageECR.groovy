#!/usr/bin/env groovy

import com.mb.AWS

def call(String imageName){
    new AWS(this).pushImageECR(imageName)
}
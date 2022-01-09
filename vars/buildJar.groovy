#!/usr/bin/env groovy

import  com.mb.Maven

def call(){
  new Maven(this).buildJar()
}

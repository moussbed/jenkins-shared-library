#!/usr/bin/env groovy

import com.mb.Docker

def call(){
    new Docker(this).dockerLogin()
}
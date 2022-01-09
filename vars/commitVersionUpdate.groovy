#!/usr/bin/env groovy

import  com.mb.GitHub

def call(){
    new GitHub(this).commitVersionUpdate()
}
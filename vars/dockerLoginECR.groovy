#!/usr/bin/env groovy

import com.mb.AWS

def call(){
    new AWS(this).dockerLoginECR()
}
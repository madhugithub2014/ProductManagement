#!groovy
pipeline {
  agent none
  options {
    timestamps()
    skipStagesAfterUnstable()
  }
  stages {
    stage('Build') {
      agent any
      steps {
        sh '''#!/usr/bin/env bash
        echo I followed this tutorial!
        '''
      }
    }
  }
  post {
    success {
      echo 'Build SUCCESS'
    }
  }
}

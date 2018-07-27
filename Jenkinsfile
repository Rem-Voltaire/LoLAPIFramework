pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool 'M3'
        tool 'JDK18'
        sh 'mvn --version'
      }
    }
  }
}
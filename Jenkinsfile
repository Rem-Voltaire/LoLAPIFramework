pipeline {
  agent {
    docker {
      image 'maven:3.5.4'
    }

  }
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
pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool 'M3'
        tool(name: 'JDK 1.8', type: 'Compiler')
        sh 'mvn --version'
      }
    }
  }
}
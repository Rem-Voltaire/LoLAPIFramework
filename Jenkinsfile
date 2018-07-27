pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        tool(name: 'Maven 3.5.4', type: 'Build tool')
        tool(name: 'JDK 1.8', type: 'Compiler')
        sh 'mvn --version'
      }
    }
  }
}
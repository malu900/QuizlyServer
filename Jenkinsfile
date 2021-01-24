pipeline {
  agent any  
   stages {
      stage('Build and Test') {
      steps {
        bat 'mvn clean install'
      }
      }
      stage('Sonar') {
      environment {
        scannerHome = tool 'SonarQubeScanner'
    }
    steps {
        withSonarQubeEnv('sonarqube') {
            sh "C:/sonarqube/sonarqube-8.5.1.38104/bin/windows-x86-64/bin/sonar-scanner"
        }
        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
    }
   }
   
  }


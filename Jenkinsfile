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
            bat "mvn clean install sonar:sonar"
        }
        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
    }
   }
   
  }


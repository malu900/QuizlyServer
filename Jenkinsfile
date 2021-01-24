pipeline {
  agent any  
   stages {
      stage('Build and Test') {
      steps {
        bat 'mvn clean install'
      }
      }
    //   stage('Sonar') {
    //   environment {
    //     scannerHome = tool 'Sonar_Runner'
    // }
    // steps {
    //     withSonarQubeEnv('SonarQube') {
    //         sh "${scannerHome}/bin/sonar-scanner"
    //     }
    //     timeout(time: 10, unit: 'MINUTES') {
    //         waitForQualityGate abortPipeline: true
    //     }
    // }
    // }
   }
   
  }


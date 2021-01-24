pipeline {
  agent any  
   stages {
      stage('Build and Test') {
      steps {
        bat 'mvn clean install'
      }
      }
      stage('Sonar') {
      steps {
        bat 'mvn sonar:sonar -Dsonar.host.url=http://sonar:9000'
      }
    }
   }
   
  }


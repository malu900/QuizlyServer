pipeline {
  agent any

   stages {
      stage('Build and Test') {
      steps {
        sh 'mvn clean install'
      }
   }
   stage('Sonar') {
      steps {
        sh 'mvn sonar:sonar -Dsonar.host.url=http://sonar:9000'
      }
    }
   stage('Test') {
     steps {
        echo 'Testing...'
     }
   }
   stage('Deploy') {
     steps {
       echo 'Deploying...'
     }
   }
  }
}

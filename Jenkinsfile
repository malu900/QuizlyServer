pipeline {
  agent any
   stages {
      stage('Build and Test') {
      steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
   }
   
  }
}

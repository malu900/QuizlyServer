pipeline {
  agent any
    tools {     
        maven 'Maven 3.6.1' 
    }
   stages {
      stage('Build and Test') {
      steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
   }
   
  }
}

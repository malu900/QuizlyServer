pipeline {
  agent any
    tools {     
        maven 'Maven 3.6.1' 
        jdk 'jdk11' 
    }
   stages {
      stage('Build and Test') {
      steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
   }
   
  }
}

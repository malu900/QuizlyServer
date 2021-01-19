pipeline {
  agent {
    docker {
      image 'maven:3.6.2-jdk-11'
      args '--network=docker-jenkins-sonarqube'
    }

  }
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

  }
}
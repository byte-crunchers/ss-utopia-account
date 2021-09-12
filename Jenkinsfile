pipeline {
    agent any

    stages {
      stage('checkout') {
        steps {
          git branch: 'feature_jenkins', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-account.git'
        }
      }
        
        stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube') {
                sh 'mvn clean package sonar:sonar'
              }
            }
          }
          stage("Quality Gate") {
            /*steps {
              timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }*/
          }
          stage('Build') {
            steps {
                  
                    sh 'docker build . -t jbnilles/ss-utopia-account:latest'

                 
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker push jbnilles/ss-utopia-account:latest'
            }
        }
    }

}
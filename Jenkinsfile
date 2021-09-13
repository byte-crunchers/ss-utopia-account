pipeline {
    agent any

    stages {
      stage('checkout') {
        steps {
          git branch: 'feature_jenkins', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-account.git'
        }
      }
        
        stage("SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube') {
                sh 'mvn sonar:sonar'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              echo message: "can not do on local machine "
             /* timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }*/
            }
          }
          stage ('Package') {
            steps {
              sh 'mvn clean package'   
            }         
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


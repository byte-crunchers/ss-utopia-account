pipeline {
   options
    {
      buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    agent any

    environment {
      AWS_DEFAULT_REGION="us-east-1" 
      IMAGE_REPO_NAME="ss-utopia-account"
      IMAGE_TAG="latest"
      REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }

    stages {       
      stage('Initialize'){
        steps{
          script{
        def dockerHome = tool 'myDocker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
          }
        }
    }
      stage('checkout') {
        steps {
          git branch: 'feature_kubernetes', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-account.git'
        }
      }

      stage("Clean install") {  //test
        steps {
          withMaven(maven: 'maven') {
            sh 'mvn clean install'
          }
        }
      }
        
      stage("SonarQube analysis") {
        steps {
          withSonarQubeEnv('SonarQube') {
            withMaven(maven: 'maven') {
              sh 'mvn sonar:sonar'
            }   
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
      //package

      stage('Build') { // git commit id for tag
        steps {
          sh 'docker build . -t ss-utopia-account:latest'
        }
      }

      stage('Deploy') {
        steps {
          script{
            docker.withRegistry("https://${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com", toolName: 'myDocker', 'ecr:us-east-1:ss-AWS') 
            {
              sh 'docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/ss-utopia-account:latest'
            }
          }
        }
      }
      //post docker prune
    }
}



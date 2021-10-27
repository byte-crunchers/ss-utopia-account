pipeline {
  options
  {
    buildDiscarder(logRotator(numToKeepStr: '3'))
  }
  agent any

  environment {
    IMAGE_REPO_NAME="ss-utopia-account"
    IMAGE_TAG="latest"
    REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
  }

  stages {       
    
    stage('checkout') {
      steps {
        git branch: 'feature_jenkins', credentialsId: 'git_login', url: 'https://github.com/byte-crunchers/ss-utopia-account.git'
      }
    }
    stage('get_commit_msg') {
      steps {
          script {
            env.GIT_COMMIT_MSG = sh (script: 'git log -1 --pretty=format:"%H"', returnStdout: true).trim()
          }
      }
    }
    stage("MVN Test") {  
      steps {
        sh 'mvn clean test'
      }
    }
      
    stage("SonarQube analysis") {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'mvn sonar:sonar'
        }  
      }
    }
  
    stage("Quality Gate") {
      steps {
          timeout(time: 5, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: false
        }
      }
    }
  
    stage("MVN Package") {  
      steps {
          sh 'mvn clean package -Dmaven.test.skip'
      }
    }

    stage('Build') { 
      steps {
        sh 'docker build . -t ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/ss-utopia-account:${GIT_COMMIT_MSG} -t ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/ss-utopia-account:latest '
      }
    }

    stage('Log into ECR') {
      steps {
          script{
              sh 'aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com'
          }
      }
    }
    stage('Deploy') {
      steps {
        script{
          sh 'docker push -a ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/ss-utopia-account'
        }
      }
    }

    stage('Update Service') {
      steps{
        withAWS(credentials: 'jenkins-ec2-user', region: "${AWS_DEFAULT_REGION}") {
          script {
            string status = sh(script: """
                  aws ecs describe-services --cluster ss-ecs-cluster --services ss-account-service --query 'failures[0].reason' --output text
              """, returnStdout: true).trim()
              echo "service status: $status" 
              if (status == 'MISSING') { 
                echo "ecs service: ss-account-service does not exists in ss-ecs-cluster"
                // put create service logic
              } else {
                  sh 'aws ecs update-service --cluster ss-ecs-cluster --service ss-account-service --force-new-deployment'
              }
          }
        }
      }
    }
    
    stage('Cleaning up') {
      steps{
          sh "docker image prune -f"
      }
    }
  }
}
pipeline {
   environment {
     REGISTRY_ENDPOINT = "https://k8s-web:v1/v2/"
     IMAGE_WITH_TAG = "k8s-web:v1"
     REGISTRY_CERTS = "registry"
   }
  agent {
    node {
      label 'java'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test '
      }
    }
    stage('Code Quality') {
      steps {
        script {
          try{
            checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
          }catch(e){
            echo e
          }
        }

      }
    }
    stage('Image Build&Publish') {
      steps {
        echo 'Build Images'
        script {
          docker.withRegistry("${REGISTRY_ENDPOINT}", "${REGISTRY_CERTS}") {
            sh 'docker build -t ${IMAGE_WITH_TAG} .'
            sh 'docker push ${IMAGE_WITH_TAG}'
          }
        }

      }
    }
  }
  triggers {
    pollSCM('* * * * *')
  }
}
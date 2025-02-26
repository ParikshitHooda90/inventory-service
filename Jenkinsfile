pipeline{
    agent any
    tools{
        maven ("maven_3_9_9")
    }
    stages{

        stage("Build maven"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ParikshitHooda90/inventory-service']])
                sh 'mvn clean install'
            }
        }
        stage("Build docker image"){
            steps{
                script{
                    sh 'docker build -t parikshithooda4033/inventory-service .'
                }
            }
        }
        stage("Push docker image"){
            steps{
                script{
                   withCredentials([string(credentialsId: '', variable: 'dockerpwd')]) {
                       sh 'docker login -parikshithooda4033 -p ${dockerpwd}'
                       sh 'docker push parikshithooda4033/inventory-service'
                   }
                }
            }
        }
    }
}
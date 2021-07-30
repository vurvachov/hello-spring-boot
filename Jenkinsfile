pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Build') {
            steps {
                /*sh '''docker-compose build
                docker image tag hello-spring:latest hello-spring:MAIN-1.0.${BUILD_NUMBER}'''*/


                /*Aplicamos este cambio para generar el jar con Gradle*/

                echo '\033[34mConstruyendo\033[0m \033[35mimagen\033[0m'
                sh '''./gradlew assemble'''
                /*docker-compose build'''*/
            }
        }

        stage('gitlab') {
          steps {
             echo 'Notify GitLab'
             updateGitlabCommitStatus name: 'build', state: 'pending'
             updateGitlabCommitStatus name: 'build', state: 'success'
          }
       }

        stage('Deploy') {
            steps {
                echo '\033[34mEjecutando\033[0m \033[33mla\033[0m \033[35maplicación\033[0m'
                sh '''./gradlew bootRun --args='--server.port=5555' '''
                /*sh '''docker-compose build
                docker-compose up -d'''*/
            }
        }
    }
}

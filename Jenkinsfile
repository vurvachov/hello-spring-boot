pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    stages {
        stage('Build') {
            steps {

                sh '''docker-compose build
                docker image tag alpine-jre hello-gradle:MAIN-1.0.${BUILD_NUMBER}'''

                /*Aplicamos este cambio para generar el jar con Gradle*/

                echo '\033[34mConstruyendo\033[0m \033[33mla\033[0m \033[35mimagen\033[0m'
                /*sh '''./gradlew assemble'''*/
                /*sh '''docker-compose build'''*/
            }
        }

        stage('Deploy') {
            steps {
                echo '\033[34mEjecutando\033[0m \033[33mla\033[0m \033[35maplicación\033[0m'
                /*sh '''./gradlew bootRun --args='--server.port=5555' '''*/
                /*sh '''docker-compose build*/
                sh '''docker-compose up -d'''
            }
        }
    }
}

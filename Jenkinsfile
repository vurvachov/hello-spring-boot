pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                sh './gradlew test'   
                junit '**/test/*.xml' 
            }
        }

        stage('Build') {
            steps{
                echo 'Building...'
            }    
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}

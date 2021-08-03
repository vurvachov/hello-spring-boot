#!/usr/bin/env groovy
pipeline {
    agent any
    options {
        ansiColor('xterm')
    }

    stages {

        stage('Pruebas') {
            steps {
                
                echo 'Realizando Pruebas...'
                
                withGradle{
                     sh './gradlew clean test'        
                }             
            }
            post{
                always{
                    junit '**/test/TEST-com.example.demo.DemoApplicationTests.xml'
                    jacoco execPattern:'**/jacoco/*.exec'
                }                
            }
        }

        stage('QA'){
            steps{

                echo 'Realizando Validacion...'

                withGradle{
                    sh './gradlew check'
                }
            }

            post{
                always{
                    recordIssues(
                            tools: [
                                    pmdParser(pattern: '**/pmd/*.xml'),
                                    spotBugs(useRankAsPriority: true, pattern: '**/spotbugs/*.xml')
                            ]
                    )
                }
            }
        }

        stage('Construcción') {
            steps{
                echo 'Construyendo...'
                sh 'docker-compose build'
            }    
        }

        stage('Seguridad') {
            steps{
                echo 'Comprobando seguridad...'
                sh 'trivy image --format=json --output=trivy-image.json hello-spring-testing:latest'
            }

            post{
                always{
                    recordIssues(
                            tool: trivy(pattern: 'trivy-image.json'),
                            enabledForFailure: true,
                            aggregatingResults: true
                    )
                }
            }
        }

        stage('Despliege') {
            steps {
                echo 'Desplegando...'
                sh 'docker-compose up -d'
            }
        }

        stage('SCM'){
            steps {
                git 'https://github.com/foo/bar.git'
            }
        }

        stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv('My SonarQube Server', envOnly: true) { // Will pick the global server connection you have configured
                    println ${env.SONAR_AUTH_TOKEN} 
                    sh './gradlew sonarqube'           
                } 
            }
        }
    }
}

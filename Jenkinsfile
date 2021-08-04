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

        /*stage('SonarQube analysis') {
            steps{
                withSonarQubeEnv("sonarqube") {
                    sh "./gradlew sonarqube"
                }
            }       
        }*/

        stage('Lanzando Pitest'){
            steps{
                withGradle{
                    sh './gradlew pitest'
                }
                
            }

            post{
                always{
                    recordIssues(
                        enabledForFailure: true, 
                        tool: pit(pattern: '**/pitest/**/*.xml')
                    )
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
                            ],
                            enabledForFailure: true
                    )
                }
            }
        }

        /*stage('Construcción') {
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
        }*/
    }
}

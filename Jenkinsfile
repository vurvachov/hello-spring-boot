#!/usr/bin/env groovy
pipeline {
    agent any

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
                //sh 'trivy hello-spring-testing:latest'
                sh 'trivy image --format=json --output=trivy-image.json hello-spring-testing:latest'
            }

            post{
                always{
                    recordIssues(
                            enabledForFailure: true,
                            aggregatingResults: true,
                            tool: [trivy(pattern: 'trivy-image.json')]
                    )
                }
            }
        }

        stage('Despliege') {
            steps {
                echo 'Desplegando...'
            }
        }
    }
}

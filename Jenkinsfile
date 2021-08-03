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

        stage('Despliege') {
            steps {
                echo 'Desplegando...'
            }
        }
    }
}

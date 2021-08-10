#!/usr/bin/env groovy
pipeline {
    agent any
    options {
        ansiColor('xterm')
    }

    stages {

        /*stage('Pruebas') {
            steps {
                
                echo 'Realizando Pruebas...'
                
                withGradle{
                     sh './gradlew clean test'        
                }             
            }
            post{
                always{
                    junit '/test/TEST-com.example.demo.DemoApplicationTests.xml' //Acordarse volver a poner ** antes de /test
                    jacoco execPattern:'/jacoco/*.exec'  // Acordarse de poner ** antes de /jacoco
                }                
            }
        }*/

        /*stage('Analysis'){
            parallel{
                stage('SonarQube analysis') {
                    when { expression {true}}
                    steps{
                        withSonarQubeEnv("sonarqube") {
                            sh "./gradlew sonarqube"
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
                                    pmdParser(pattern: '/pmd/*.xml'),  //acordarse de poner ** antes de /pmd
                                    spotBugs(useRankAsPriority: true, pattern: '/spotbugs/*.xml') //acordarse de poner ** antes de /spotbugs
                                ],
                                enabledForFailure: true
                            )
                        }
                    }
                }
            }
        }*/

        /*stage('Lanzando Pitest'){
            steps{
                withGradle{
                    sh './gradlew pitest'
                }
                
            }

            post{
                always{
                    recordIssues(
                        enabledForFailure: true, 
                        tool: pit(pattern: '/pitest//*.xml') // Acoerdarse volver a poner ** antes de /pitest y despues de /pitest/
                    )
                }
            }
        }*/

        /*stage('Construcción') {
            steps{
                echo 'Construyendo...'
                sh 'docker-compose build'               
            }    
        }

        stage('Login Docker'){
            steps{
                script{
                    withDockerRegistry([url:'http://10.250.11.3:5050',credentialsId:'tokenDespliege']) {
                        sh "docker tag hello-spring-testing:latest 10.250.11.3:5050/vurvachov/hello-spring-boot/hello-spring-testing:${env.BUILD_TAG}"
                        sh "docker push 10.250.11.3:5050/vurvachov/hello-spring-boot/hello-spring-testing:${env.BUILD_TAG}"
                        
                        sh "docker image rm -f 10.250.11.3:5050/vurvachov/hello-spring-boot/hello-spring-testing:${env.BUILD_TAG}"
                        sh "docker image rm -f hello-spring-testing:latest"
                    } 
                }

            }
                                                                    
        }*/

        /*stage('Seguridad') {
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
        }*/

        stage('Despliege') {
            steps{
                sshagent(credentials: ['appKey']) {
                    sh 'ssh -o StrictHostKeyChecking=no app@10.250.11.3 uptime'
                    sh 'ssh app@10.250.11.3'
                    sh '/bin/sh -c cd hello-spring-boot && docker-compose pull && docker-compose up -d'
                }
            }  
        }
    }
}

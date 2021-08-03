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

                post{
                    always{
                        recordIssues(
                                tools: [
                                        pmdParser(pattern: '**/pmd/*.xml')
                                ]
                        )
                    }
                }
            }
        }

        stage('Construcción') {
            steps{
                echo 'Construyendo...'
            }    
        }

        stage('Despliege') {
            steps {
                echo 'Desplegando...'
            }
        }
    }
}

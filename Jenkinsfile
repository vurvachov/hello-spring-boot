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

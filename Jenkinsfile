pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64/bin/java'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/valdesngoumlack/springbootraining.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'java -jar target/training_0.0.1-SNAPSHOT.jar &'
            }
        }
    }

    post {
        success {
            echo 'Build et déploiement réussis !'
        }
        failure {
            echo 'Échec du build ou du déploiement.'
        }
    }
}
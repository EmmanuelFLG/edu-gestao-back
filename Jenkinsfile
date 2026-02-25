pipeline {
    agent any

    environment {
        COMPOSE_FILE = "docker-compose.yml"
        PROJECT_NAME = "gestao-escolar"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend Image') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE build backend'
            }
        }

        stage('Build Frontend Image') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE build frontend'
            }
        }

        stage('Deploy Containers') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE down'
                sh 'docker compose -f $COMPOSE_FILE up -d'
            }
        }

        stage('Show Running Containers') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo 'Deploy realizado com sucesso 🚀'
        }
        failure {
            echo 'Pipeline falhou ❌'
        }
    }
}
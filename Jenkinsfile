pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "vijay3247/youtube-clone:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/vijay254452/youtube.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker push ${DOCKER_IMAGE}'
                }
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker rm -f youtube-clone || true
                docker run -d --name youtube-clone -p 8090:8080 ${DOCKER_IMAGE}
                '''
            }
        }
    }
}


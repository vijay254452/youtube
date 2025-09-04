pipeline {
    agent any

    tools {
        maven "Maven3"
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=/var/jenkins_home/.m2/repository"
        DOCKER_IMAGE = "vijay254452/youtube-clone"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build WAR') {
            when {
                changeset "**/src/**" // Run only if Java source files changed
            }
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker Image') {
            when {
                anyOf {
                    changeset "Dockerfile"
                    changeset "target/*.war"
                }
            }
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:latest ."
            }
        }

        stage('Push Docker Image') {
            when {
                branch 'main'
            }
            steps {
                sh "docker push ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Deploy Container') {
            when {
                branch 'main'
            }
            steps {
                sh '''
                docker rm -f youtube-clone || true
                docker run -d --name youtube-clone -p 8080:8080 ${DOCKER_IMAGE}:latest
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline finished with status: ${currentBuild.currentResult}"
        }
    }
}


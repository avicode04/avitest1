pipeline {
    agent any
    environment {
        repo_path = '$(basename $PWD)'
    }
    stages {
        stage('sync source code') {
            when{ branch 'master' }
            steps {
                sh "rsync -rva ../${repo_path} ubuntu@10.20.1.136:/home/ubuntu/"
            }
        }
        stage('build') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.136 'cd ~/'${repo_path}' ; mvn clean package -DskipTests'"
                sh "ssh ubuntu@10.20.1.252 'cd ~/'${repo_path}'/frontend ; npm install'"
                sh "ssh ubuntu@10.20.1.252 'cd ~/'${repo_path}'/frontend ; ng build'"

            }
        }
        stage('Deploy') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.136 'cd ~/'shopperzoid_v1.0.1' ; docker-compose up --build -d config-server eureka-server'"
                sh "ssh ubuntu@10.20.1.136 'cd ~/'shopperzoid_v1.0.1' ; docker-compose up --build -d --no-recreate'"
            }
        }
        stage('Deployment status') {
            when { branch 'master' }
            steps {
                sh "ssh ubuntu@10.20.1.136 'cd ~/'shopperzoid_v1.0.1' ; sleep 30 ; docker ps'"
            }
        }
    }
}

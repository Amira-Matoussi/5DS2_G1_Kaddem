pipeline {
    agent any
    
    stages {
        
        // 1. Git Clone Stage
        stage('Code Retrieval') {
            steps {
                echo 'Cloning repository...';
                git branch: 'Khaled_5DS2_G1', url: 'https://github.com/Khaled-Chaabouni/5DS2_G1_Kaddem.git';
            }
        }

        // 2. Maven Clean and Compile
        stage('Cleaning and Compiling with Maven') {
            steps {
                echo 'Running Maven clean and compile...';
                sh 'mvn clean compile';
            }
        }

        // 3. JUnit Test Execution
        stage('Unit Testing with JUnit') {
            steps {
                echo 'Executing Unit Tests...';
                sh 'mvn test';
            }
        }

        // 4. Code Quality Check with SonarQube
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...';
                sh 'mvn sonar:sonar -Dsonar.projectKey=5DS2_G1_Kaddem -Dsonar.login=admin -Dsonar.password=Chaabouni111KH928&';
            }
        }

        // 5. Package Artifact with Maven
        stage('Maven Package') {
            steps {
                echo 'Packaging application...'
                sh 'mvn package -DskipTests'
            }
        }

        // 6. Deploy to Nexus Repository
        stage('Nexus Deployment') {
            steps {
                echo 'Deploying to Nexus...'
                sh 'mvn deploy -DskipTests'
            }
        }

        // 7. Docker Image Creation
        stage('Docker Image Creation') {
            steps {
                echo 'Building Docker Image...';
                sh 'docker build -t khaledchaabouni/5ds2_g1_kaddem:1.0.0 .';
            }
        }

        // 8. Push Docker Image to Docker Hub
        stage('Push Docker Image to Docker Hub') {
            steps {
                echo 'Pushing Docker Image to Docker Hub...';
                sh 'docker login -u khaledchaabouni -p dckr_pat_7kWM12VTv9eWmsh0_bGuqOnIzis';
                sh 'docker push khaledchaabouni/5ds2_g1_kaddem:1.0.0';
            }
        }

        // 9. Docker Compose to Launch Services
        stage('Docker Compose Setup') {
            steps {
                echo 'Starting services with Docker Compose...';
                sh 'docker compose up -d';
            }
        }

        // 10. Launch Prometheus
        stage('Launch Prometheus') {
            steps {
                echo 'Starting Prometheus for monitoring...';
                sh 'docker run -d --name prometheus -p 9090:9090 prom/prometheus';
            }
        }

        // 11. Launch Grafana
        stage('Launch Grafana') {
            steps {
                echo 'Starting Grafana for visualization...';
                sh 'docker run -d --name grafana -p 3000:3000 grafana/grafana';
            }
        }
    }
}

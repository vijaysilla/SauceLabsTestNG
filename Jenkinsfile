pipeline {
    agent any

    // tools {
    //     maven "MAVEN_HOME" // Ensure this matches your Maven tool installation in Jenkins
    // }

    stages {
        stage('Checkout') {
            steps {
                    echo "Checking out the repository..."
                    git branch: 'main', url: 'https://github.com/vijaysilla/SauceLabsTestNG.git'
                    bat 'ls -la'
            }
        }

        stage('Install Dependencies') {
            steps {
                echo "Installing dependencies..."
                bat "mvn clean install -X" // Enable Maven debugging and continue on error                
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running Cucumber BDD tests using Maven..."
                // sh "mvn -X test || true" // Enable Maven debugging output and continue on error
                bat "mvn test"
            }
        }
    }

    post {
        always {
            echo "Archiving test reports..."
            junit '**/target/surefire-reports/*.xml'
            cucumber fileIncludePattern: '**/target/cucumber.json'
            archiveArtifacts artifacts: '**/test-output/reports/*/sauce_sparkReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: '**/test-output/reports/*/sauce_HTMLReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: '**/test-output/reports/*/sauce_PDFReport.pdf', allowEmptyArchive: true
            cleanWs() // Clean workspace after everything else
        }
        success {
            echo 'Tests completed successfully.'
        }
        failure {
            echo 'Tests failed! Check the reports for details.'
        }
    }
}

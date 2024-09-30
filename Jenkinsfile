pipeline {
    agent any

    tools {
        maven "M3" // Ensure this matches your Maven tool installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                    echo "Checking out the repository..."
                    git branch: 'main', url: 'https://github.com/vijaysilla/SauceLabsTestNG.git'
                    // bat 'dir'
            }
        }

        stage('Install Dependencies') {
            steps {
                echo "Installing dependencies..."
                bat "mvn clean install -e -X" // Enable Maven debugging and continue on error                
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running Cucumber BDD tests using Maven..."
                bat "mvn clean test -e -X"
            }
        }
    }

    post {
        always {
            echo "Archiving test reports..."
            junit '**/test-output/junitreports/surefire-reports/*.xml'
            cucumber fileIncludePattern: '**/test-output/junitreports/surefire-reports/cucumber.json'
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

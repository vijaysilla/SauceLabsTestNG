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

        // stage('Install Dependencies') {
        //     steps {
        //         echo "Installing dependencies..."
        //         // bat "mvn clean install -e -X" // Enable Maven debugging and continue on error 
        //         bat "mvn clean install" // Enable Maven debugging and continue on error 
        //     }
        // }

        stage('Run Tests') {
            steps {
                echo "Running Cucumber BDD tests using Maven..."
                // bat "mvn clean test -e -X"
                bat "mvn clean test"
            }
        }
        stage('Publish Extent Reports') 
        {
            steps 
            {
                publishHTML([allowMissing: false, 
                    alwaysLinkToLastBuild: true, 
                    keepAll: true, 
                    reportDir: 'test-output/reports', // Directory where Extent Reports are generated
                    reportFiles: 'sauce_sparkReport.html', // The main Extent report file
                    reportName: 'Extent Test Report'])
            }
        }
    }

    post {
        always {
            echo "Archiving test reports..."
            // junit 'test-output/junitreports/surefire-reports/*.xml'
            cucumber fileIncludePattern: 'target/cucumber.json'
            archiveArtifacts artifacts: 'test-output/reports/sauce_sparkReport1.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/sauce_HTMLReport1.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/sauce_PDFReport1.pdf', allowEmptyArchive: true
            // cleanWs() // Clean workspace after everything else

                script 
                {
                def webexApiUrl = "https://webexapis.com/v1/messages"
                def accessToken = "YOUR_WEBEX_BOT_ACCESS_TOKEN"
                def roomId = "YOUR_WEBEX_ROOM_ID"
                def testReportUrl = "${env.BUILD_URL}/testReport"
                def buildStatus = currentBuild.result ?: 'SUCCESS'
                def testResult = buildStatus == 'FAILURE' ? 'Some tests failed.' : 'All tests passed.'

                // Execute the cURL command using bat (Windows shell command)
                bat """
                curl -X POST "${webexApiUrl}" ^
                  -H "Authorization: Bearer ${accessToken}" ^
                  -H "Content-Type: application/json" ^
                  -d "{
                        \\"roomId\\": \\"${roomId}\\",
                        \\"markdown\\": \\"**Test Execution Summary**\\\\nBuild: #${env.BUILD_NUMBER}\\\\nStatus: ${buildStatus}\\\\n${testResult}\\\\n[Test Report](${testReportUrl})\\"
                      }"
                """
                }              
        }
        success {
            echo 'Tests completed successfully.'
        }
        failure {
            echo 'Tests failed! Check the reports for details.'
        }
    }
}

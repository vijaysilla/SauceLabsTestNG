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
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running Cucumber BDD tests using Maven..."
                bat "mvn clean test -Dcucumber.filter.tags='@exceltestDisplay'"
            }
        }

        stage('Publish Extent Reports') {
            steps {
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
            cucumber fileIncludePattern: 'target/cucumber.json'
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_sparkReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_HTMLReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_PDFReport.pdf', allowEmptyArchive: true
            
            script {
                def webexApiUrl = "https://webexapis.com/v1/messages"
                def accessToken = "ZDVkZDRmN2UtYWQyYS00YmYzLTgwNTYtMmNlNWRlMjNkNjEyMTIzMmE4ZjItNDJm_P0A1_149711dc-58c8-4cd6-9e66-384c51eeff08"
                def roomId = "d2200aa0-8140-11ef-8e63-7150b8751249"
                // def testReportUrl = "${env.BUILD_URL}/testReport"
                def testReportUrl = "${env.BUILD_URL}artifacts/test-output/reports/*/sauce_sparkReport.html"
                def buildStatus = currentBuild.result ?: 'SUCCESS'
                def testResult = buildStatus == 'FAILURE' ? 'Some tests failed.' : 'All tests passed.'

                def jsonPayload = """
                {
                    "roomId": "${roomId}",
                    "markdown": "**Test Execution Summary**\\nBuild: #${env.BUILD_NUMBER}\\nStatus: ${buildStatus}\\n${testResult}\\n[Test Report](${testReportUrl})"
                }
                """

                powershell """
                \$headers = @{
                    Authorization = 'Bearer ${accessToken}'
                    'Content-Type' = 'application/json'
                }
                
                \$body = '${jsonPayload}'
                
                Invoke-RestMethod -Uri '${webexApiUrl}' -Method POST -Headers \$headers -Body \$body
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

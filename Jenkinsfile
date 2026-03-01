pipeline 
{
    agent any

    tools 
    {
        maven "M3" // Ensure this matches your Maven tool installation in Jenkins
    }
	parameters 
	{
	    string(name:'TAGS', defaultValue: '@exceltestDisplay', description:'my executions through jenkins file')	    
	}

    stages 
    {
        stage('Checkout') 
        {
            steps 
            {
                echo "Checking out the repository..."
                git branch: 'main', url: 'https://github.com/vijaysilla/SauceLabsTestNG.git'
				bat "echo mvn clean test -Dcucumber.filter.tags=${params.TAGS}"
                bat "echo \'${params.TAGS}'"
                bat "echo \"${params.TAGS}\""				
            }
        }

        stage('Run Tests') 
        {
            steps 
            {
                echo "Running Cucumber BDD tests using Maven..."
                //bat "mvn clean test -Dcucumber.filter.tags='@exceltestDisplay'"
                bat "mvn clean test -Dcucumber.filter.tags=\'${params.TAGS}'"
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

    post 
    {
        always 
        {
            echo "Archiving test reports..."
            cucumber fileIncludePattern: 'target/cucumber.json'
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_sparkReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_HTMLReport.html', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/reports/*/sauce_PDFReport.pdf', allowEmptyArchive: true  
        }
	 
        success 
        {
            echo 'Tests completed successfully.'
        }

        failure 
        {
            echo 'Tests failed! Check the reports for details.'
        }
     }
}

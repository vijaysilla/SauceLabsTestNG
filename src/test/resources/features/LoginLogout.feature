@sanity @smoke
Feature: This feature consists of scenarios related to login & logout of application

  #Background:
  #Given Log into sauce labs application with credentials "standard_user" and password "secret_sauce"
  #@sanity @smoke @regression
  #Scenario: All items on home able to shop or not
  #Given User at home page or not
  #Then validate each item in home page haveing Add to cart button
  #@exceltest
  #Scenario: excel data read
  #Given Read the test data from data file
  #Then Display read values from test data file at row num "<SNO>"
  #| SNO |
  #|   2 |
  #|   3 |
  #Then display again excel values
  
  @exceltest
  Scenario Outline: Login page validation with valid credentials list
    Then Validate application launch
    When Log into sauce labs application with credentials "<uname>" "<pwd>" from "credentials.xlsx" located in "emp_data"
    Then Validate user login for user "<uname>" from "credentials.xlsx" located in "emp_data"
    Then Reset application login page 

    Examples: 
      | uname           | pwd |
      #| locked_out_user | **  |
      | standard_user   | **  |
 
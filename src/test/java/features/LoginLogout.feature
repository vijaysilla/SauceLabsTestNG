@sanity @smoke
Feature: This feature consists of scenarios related to login & logout of application

  Background: 
    Given Log into sauce labs application with credentials "standard_user" and password "secret_sauce"
@sanity @smoke @regression
  Scenario: All items on home able to shop or not
    Given User at home page or not
    Then validate each item in home page haveing Add to cart button
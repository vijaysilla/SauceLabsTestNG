@sanity @smoke
Feature: This is to display excel values which were read in other feature

  @exceltestDisplay
  Scenario: excel data values display
    Given extract test data which was read from excel earlier
    Then Display read values from test data file

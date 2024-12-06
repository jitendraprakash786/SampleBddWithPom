Feature: Verify login feature

  @Test
  Scenario: Verify the successful login to application
    Given I launch the Chrome browser
    When I open the application in qa environment
    
    And I am on the Landing page
    And I click the TextBox_Search element
    And I enter Mobile value in the TextBox_Search field
    And I close the browser
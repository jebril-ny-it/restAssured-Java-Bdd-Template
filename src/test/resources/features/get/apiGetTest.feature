@smoke
Feature: Get API Test Example

  Scenario: example of how to send a post request
    When I send a rest get request to "/api/users?page=2" for "URL"
    Then I verify response status code is "200"
    Then I extract the full response to the console


@smoke
Feature: Post API Test Example

  Scenario: example of how to send a post request
    When I send a rest post request to "/api/users" leveraging "users" json file for "URL"
    Then I verify response status code is "201"

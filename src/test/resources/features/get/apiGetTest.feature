@smoke
Feature: Get API Test Example

  Scenario: example of how to send a post request
    When I send a rest get request to "/some/endpoint" for "URL"
    Then I verify response status code is "403"

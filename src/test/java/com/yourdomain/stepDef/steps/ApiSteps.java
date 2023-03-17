package com.yourdomain.stepDef.steps;

import com.yourdomain.constants.FieldData;
import com.yourdomain.stepDef.support.PageRetriever;
import com.yourdomain.util.TestDataRetriever;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    /**
     * dependency Injection:
     * */
    PageRetriever pageRetriever;

    /**
     * constructor:
     * @param pageRetriever:
     * */
    public ApiSteps(PageRetriever pageRetriever) {
        this.pageRetriever = pageRetriever;
    }

    /**
     * @param soapApiFIlePath: json file path
     * @param restApiFIlePath: xml file path
     * */
    private final String soapApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain", "api", "soap").toString() + File.separator;
    private final String restApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain", "api", "rest").toString() + File.separator;

    /**
     * This executable specification will send a post request
     * @param endPoint: The end-point provided via feature file
     * @param jsonFile: The json file were the request payload will be fetched from
     * @param url:      The URL for the api, this value will be fetched from the properties file, either DEV,QA,UAT
     * */
    @Given("I send a rest post request to {string} leveraging {string} json file for {string}")
    public void iSendARestPostRequestToLeveragingJsonFileFor(String endPoint, String jsonFile, String url) {
        String fileName = jsonFile + ".json";
        String URL = TestDataRetriever.myData.get(url);

        String jsonRequestBody = pageRetriever.getApiUtils().getFileContent(restApiFIlePath, fileName);
        System.out.println("Json File Read is-> " + jsonRequestBody);

        RestAssured.baseURI = URL;

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "yourAuth")
                        .accept(ContentType.JSON)
                        .body(jsonRequestBody)
                        .when()
                        .post(endPoint)
                        .then()
                        //.statusCode(200)
                        .log()
                        .ifError()
                        .extract()
                        .response();

        // This step will store the status code into the data store for future use
        pageRetriever.getDataStore().put(FieldData.RESPONSE_STATUS_CODE, String.valueOf(response.statusCode()));
    }

    /**
     * This executable specification will send a get request
     * @param expectedStatusCode: The expected response status code provided via feature file
     * */
    @Then("I verify response status code is {string}")
    public void iVerifyResponseStatusCodeIs(String expectedStatusCode) {
        String actualStatusCodeToString = (String) pageRetriever.getDataStore().get(FieldData.RESPONSE_STATUS_CODE);
        System.out.println("The status code that I fetched from the Data Store during my assertion step is-> " + actualStatusCodeToString);
        Assert.assertEquals(actualStatusCodeToString, expectedStatusCode);
    }

    /**
     * This executable specification will send a get request
     * @param endPoint: The end-point provided via feature file
     * @param url:      The URL for the api, this value will be fetched from the properties file, either DEV,QA,UAT
     * */
    @When("I send a rest get request to {string} for {string}")
    public void iSendARestGetRequestToFor(String endPoint, String url) {
        String URL = TestDataRetriever.myData.get(url);

        RestAssured.baseURI = URL;

        Response response =
                given()
                        .get(endPoint)
                        .then()
                        .log()
                        .ifError()
                        .extract()
                        .response();

        // This step will store the status code into the data store for future use
        pageRetriever.getDataStore().put(FieldData.RESPONSE_STATUS_CODE, String.valueOf(response.statusCode()));
    }
}

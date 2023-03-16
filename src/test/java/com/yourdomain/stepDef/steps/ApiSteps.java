package com.yourdomain.stepDef.steps;

import com.yourdomain.constants.FieldData;
import com.yourdomain.stepDef.support.PageRetriever;
import com.yourdomain.util.TestDataRetriever;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    PageRetriever pageRetriever;

    public ApiSteps(PageRetriever pageRetriever) {
        this.pageRetriever = pageRetriever;
    }

    private final String soapApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain", "api", "soap").toString() + File.separator;
    private final String restApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain", "api", "rest").toString() + File.separator;


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

        // This step should not go here, but I am just adding it here for visibility
        String statusCode = (String) pageRetriever.getDataStore().get(FieldData.RESPONSE_STATUS_CODE);
        System.out.println("The status code that I fetched from the Data Store is->" + statusCode);
    }

    @Then("I verify response status code is {string}")
    public void iVerifyResponseStatusCodeIs(String expectedStatusCode) {
        String actualStatusCodeToString = (String) pageRetriever.getDataStore().get(FieldData.RESPONSE_STATUS_CODE);
        System.out.println("The status code that I fetched from the Data Store during my assertion step is-> " + actualStatusCodeToString);
        Assert.assertEquals(actualStatusCodeToString, expectedStatusCode);
    }
}

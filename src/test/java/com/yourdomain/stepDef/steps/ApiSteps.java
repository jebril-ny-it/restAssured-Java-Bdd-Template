package com.yourdomain.stepDef.steps;

import com.yourdomain.stepDef.support.PageRetriever;
import io.cucumber.java.en.Given;

import java.io.File;
import java.nio.file.Paths;

public class ApiSteps {

	PageRetriever pageRetriever;

	public ApiSteps(PageRetriever pageRetriever ){
		this.pageRetriever=pageRetriever;
	}

	private final String soapApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","soap").toString() + File.separator;
	private final String restApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","rest").toString() + File.separator;

	@Given("I send a rest get request")
	public void i_send_a_rest_get_request() {

		String jsonRequestBody = pageRetriever.getApiUtils().getFileContent(restApiFIlePath,"test.json");
		System.out.println("Json File Read is-> " + jsonRequestBody);

		String soapRequestBody = pageRetriever.getApiUtils().getFileContent(soapApiFIlePath,"test.xml");
		System.out.println("soap File Read is-> " + soapRequestBody);

		System.out.println("Sending a get request");
	}


}

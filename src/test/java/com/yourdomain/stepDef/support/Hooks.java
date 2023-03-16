package com.yourdomain.stepDef.support;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.yourdomain.util.ReadConfigFile;
import com.yourdomain.util.TestDataRetriever;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
    // Dependency injection
    PageRetriever pageRetriever;

    /**
     * Constructor
     */
    public Hooks(PageRetriever pageRetriever) {
        this.pageRetriever = pageRetriever;
    }

    /**
     * Hooks Before & After
     */
    @Before
    public void beforeScenarioStart() {
        System.out.println("Scenario Started... ");

        String tag = System.getProperty("cucumber.filter.tags");
        System.out.println("If a Tag was defined via CMD, it is-> " + tag);

        // Read & prepare the Config for the entire system
        try {
            TestDataRetriever.myData = ReadConfigFile.readProperties();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterScenarioEnd(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                // do nothing for now
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Scenario Ended... ");
    }

}

package com.equalexperts.stepdefs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:build/reports/cucumber-report.html"},
        features = { "classpath:features/shoppingBasket.feature" },
        glue = {"com.equalexperts.stepdefs"},
        publish = true)
public class RunCucumberTest {
}
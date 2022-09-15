package com.a101.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty:target/failed-pretty-report",
        features = "@target/rerun.txt",
        glue = "com/a101/stepDefinitions"
)
public class FailedTestRunner {
}

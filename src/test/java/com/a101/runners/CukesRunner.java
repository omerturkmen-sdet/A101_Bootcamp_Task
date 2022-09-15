package com.a101.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/html-report",
                "pretty",
                "rerun: target/rerun.txt"},
        features = "src/test/resources/features",
        glue = "com/a101/stepDefinitions",
        dryRun = false,
        tags = "@negative"
)
public class CukesRunner {
}

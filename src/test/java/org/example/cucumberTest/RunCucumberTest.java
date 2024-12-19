package org.example.cucumberTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.fcu.iecs.gym",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class RunCucumberTest {
}

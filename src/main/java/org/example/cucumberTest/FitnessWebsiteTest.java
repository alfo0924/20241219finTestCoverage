package org.example.cucumberTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FitnessWebsiteTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost/fitness.html");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

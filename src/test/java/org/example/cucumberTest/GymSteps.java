package org.example.cucumberTest.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class GymSteps {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "file:///path/to/your/html/file.html"; // 更新為實際路徑
        driver.manage().window().maximize();
    }

    @Given("I am on the gym pricing calculator page")
    public void i_am_on_the_gym_pricing_calculator_page() {
        driver.get(baseUrl);
    }

    @When("I select day {string}")
    public void i_select_day(String day) {
        driver.findElement(By.id("day")).sendKeys(day);
    }

    @When("I enter age {string}")
    public void i_enter_age(String age) {
        driver.findElement(By.id("age")).sendKeys(age);
    }

    @When("I select time {string}")
    public void i_select_time(String time) {
        driver.findElement(By.id("time")).sendKeys(time);
    }

    @When("I am not a member")
    public void i_am_not_a_member() {
        driver.findElement(By.id("member-no")).click();
    }

    @When("I am a member with id {string}")
    public void i_am_a_member_with_id(String memberId) {
        driver.findElement(By.id("member-yes")).click();
        driver.findElement(By.id("member-id")).sendKeys(memberId);
    }

    @Then("the price should be {string}")
    public void the_price_should_be(String expectedPrice) {
        driver.findElement(By.id("calculate")).click();
        WebElement output = driver.findElement(By.id("output"));
        assertTrue(output.getText().contains(expectedPrice));
    }

    @Then("I should see age error message")
    public void i_should_see_age_error_message() {
        driver.findElement(By.id("calculate")).click();
        WebElement error = driver.findElement(By.id("age-error"));
        assertTrue(error.isDisplayed());
        assertEquals("年齡應介於 3 與 75 之間", error.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

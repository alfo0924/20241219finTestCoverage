package org.example.cucumberTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class GymSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://nlhsueh.github.io/iecs-gym/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("I am on the gym pricing calculator page")
    public void i_am_on_the_gym_pricing_calculator_page() {
        driver.get(baseUrl);
    }

    @When("I select day {string}")
    public void i_select_day(String day) {
        Select daySelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("day"))
        ));
        daySelect.selectByValue(day);
    }

    @When("I enter age {string}")
    public void i_enter_age(String age) {
        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    @When("I select time {string}")
    public void i_select_time(String time) {
        Select timeSelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("time"))
        ));
        timeSelect.selectByValue(time);
    }

    @When("I am not a member")
    public void i_am_not_a_member() {
        WebElement memberNo = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("member-no"))
        );
        memberNo.click();
    }

    @When("I am a member with id {string}")
    public void i_am_a_member_with_id(String memberId) {
        WebElement memberYes = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("member-yes"))
        );
        memberYes.click();

        WebElement memberIdInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("member-id"))
        );
        memberIdInput.clear();
        memberIdInput.sendKeys(memberId);
    }

    @Then("the price should be {string}")
    public void the_price_should_be(String expectedPrice) {
        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $" + expectedPrice));
    }

    @Then("I should see age error message")
    public void i_should_see_age_error_message() {
        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement error = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age-error"))
        );
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

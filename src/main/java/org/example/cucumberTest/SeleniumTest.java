package org.example.cucumberTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.*;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = "https://nlhsueh.github.io/iecs-gym/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testRegularWeekdayPrice() {
        driver.get(baseUrl);

        // Use Select class for dropdown elements
        Select daySelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))));
        daySelect.selectByValue("Monday");

        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("30");

        Select timeSelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("time"))));
        timeSelect.selectByValue("after7");

        WebElement memberNo = wait.until(ExpectedConditions.elementToBeClickable(By.id("member-no")));
        memberNo.click();

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement output = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("output")));
        assertTrue("Price should be 200.00", output.getText().contains("費用為 $200.00"));
    }

    @Test
    public void testMemberDiscount() {
        driver.get(baseUrl);

        WebElement memberYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("member-yes")));
        memberYes.click();

        WebElement memberId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("member-id")));
        memberId.clear();
        memberId.sendKeys("IECS-123");

        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("30");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement output = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("output")));
        assertTrue("Price should be 100.00", output.getText().contains("費用為 $100.00"));
    }

    @Test
    public void testInvalidAge() {
        driver.get(baseUrl);

        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("2");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age-error")));
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

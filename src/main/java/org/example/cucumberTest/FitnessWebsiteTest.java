package org.example.cucumberTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FitnessWebsiteTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://nlhsueh.github.io/iecs-gym/");
        driver.manage().window().maximize();
    }

    @Test
    public void testWeekdayRegularPrice() {
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
        Assert.assertTrue(output.getText().contains("費用為 $200.00"));
    }

    @Test
    public void testWeekendPrice() {
        Select daySelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))));
        daySelect.selectByValue("Saturday");

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
        Assert.assertTrue(output.getText().contains("費用為 $250.00"));
    }

    @Test
    public void testMemberDiscount() {
        Select daySelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))));
        daySelect.selectByValue("Monday");

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
        Assert.assertTrue(output.getText().contains("費用為 $100.00"));
    }

    @Test
    public void testEarlyBirdDiscount() {
        Select daySelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("day"))));
        daySelect.selectByValue("Monday");

        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("30");

        Select timeSelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("time"))));
        timeSelect.selectByValue("before7");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement output = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("output")));
        Assert.assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testSeniorDiscount() {
        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("65");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement output = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("output")));
        Assert.assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testChildDiscount() {
        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("10");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement output = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("output")));
        Assert.assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testInvalidMemberId() {
        WebElement memberYes = wait.until(ExpectedConditions.elementToBeClickable(By.id("member-yes")));
        memberYes.click();

        WebElement memberId = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("member-id")));
        memberId.clear();
        memberId.sendKeys("INVALID-123");

        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("30");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("member-id-error")));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "會員編號必須以 IECS- 開頭。");
    }

    @Test
    public void testInvalidAge() {
        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.clear();
        ageInput.sendKeys("2");

        WebElement calculateButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("calculate")));
        calculateButton.click();

        WebElement error = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age-error")));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "年齡應介於 3 與 75 之間");
    }

    @Test
    public void testResetButton() {
        WebElement ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        ageInput.sendKeys("30");

        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("reset")));
        resetButton.click();

        ageInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("age")));
        Assert.assertEquals(ageInput.getAttribute("value"), "");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

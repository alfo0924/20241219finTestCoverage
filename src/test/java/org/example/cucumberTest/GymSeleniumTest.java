package org.example.cucumberTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class GymSeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://nlhsueh.github.io/iecs-gym/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testWeekendPrice() {
        driver.get(baseUrl);

        Select daySelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("day"))
        ));
        daySelect.selectByValue("Saturday");

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("30");

        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $250.00"));
    }

    @Test
    public void testEarlyBirdDiscount() {
        driver.get(baseUrl);

        Select timeSelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("time"))
        ));
        timeSelect.selectByValue("before7");

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("30");

        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testSeniorDiscount() {
        driver.get(baseUrl);

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("65");

        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testChildDiscount() {
        driver.get(baseUrl);

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("10");

        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $160.00"));
    }

    @Test
    public void testInvalidMemberId() {
        driver.get(baseUrl);

        WebElement memberYes = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("member-yes"))
        );
        memberYes.click();

        WebElement memberId = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("member-id"))
        );
        memberId.clear();
        memberId.sendKeys("INVALID-123");

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("30");

        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        WebElement error = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("member-id-error"))
        );
        assertTrue(error.isDisplayed());
        assertEquals("會員編號必須以 IECS- 開頭。", error.getText());
    }

    @Test
    public void testResetButton() {
        driver.get(baseUrl);

        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.sendKeys("30");

        WebElement resetButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("reset"))
        );
        resetButton.click();

        ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        assertEquals("", ageInput.getAttribute("value"));
    }

    // 保留原有的測試方法...

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

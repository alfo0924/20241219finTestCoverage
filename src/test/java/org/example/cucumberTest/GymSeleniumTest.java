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
    public void testRegularWeekdayPrice() {
        driver.get(baseUrl);

        // 使用 Select 類處理下拉選單
        Select daySelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("day"))
        ));
        daySelect.selectByValue("Monday");

        // 輸入年齡
        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("30");

        // 選擇時間
        Select timeSelect = new Select(wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("time"))
        ));
        timeSelect.selectByValue("after7");

        // 選擇非會員
        WebElement memberNo = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("member-no"))
        );
        memberNo.click();

        // 點擊計算按鈕
        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        // 驗證輸出
        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $200.00"),
                "Expected price 200.00 not found in output: " + output.getText());
    }

    @Test
    public void testMemberDiscount() {
        driver.get(baseUrl);

        // 選擇會員
        WebElement memberYes = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("member-yes"))
        );
        memberYes.click();

        // 輸入會員編號
        WebElement memberId = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("member-id"))
        );
        memberId.clear();
        memberId.sendKeys("IECS-123");

        // 輸入年齡
        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("30");

        // 點擊計算按鈕
        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        // 驗證輸出
        WebElement output = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("output"))
        );
        assertTrue(output.getText().contains("費用為 $100.00"),
                "Expected price 100.00 not found in output: " + output.getText());
    }

    @Test
    public void testInvalidAge() {
        driver.get(baseUrl);

        // 輸入無效年齡
        WebElement ageInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age"))
        );
        ageInput.clear();
        ageInput.sendKeys("2");

        // 點擊計算按鈕
        WebElement calculateButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("calculate"))
        );
        calculateButton.click();

        // 驗證錯誤訊息
        WebElement error = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("age-error"))
        );
        assertTrue(error.isDisplayed());
        assertEquals("年齡應介於 3 與 75 之間", error.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

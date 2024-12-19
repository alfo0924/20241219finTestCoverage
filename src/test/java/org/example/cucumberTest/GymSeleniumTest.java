package org.example.cucumberTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class GymSeleniumTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "file:///path/to/your/html/file.html"; // 更新為實際路徑
        driver.manage().window().maximize();
    }

    @Test
    public void testRegularWeekdayPrice() {
        driver.get(baseUrl);
        driver.findElement(By.id("day")).sendKeys("Monday");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("time")).sendKeys("after7");
        driver.findElement(By.id("calculate")).click();

        WebElement output = driver.findElement(By.id("output"));
        assertTrue(output.getText().contains("200.00"));
    }

    @Test
    public void testMemberDiscount() {
        driver.get(baseUrl);
        driver.findElement(By.id("member-yes")).click();
        driver.findElement(By.id("member-id")).sendKeys("IECS-123");
        driver.findElement(By.id("age")).sendKeys("30");
        driver.findElement(By.id("calculate")).click();

        WebElement output = driver.findElement(By.id("output"));
        assertTrue(output.getText().contains("100.00"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

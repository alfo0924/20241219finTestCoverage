package com.fcu.iecs.gym;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "file:///path/to/your/html/file.html"; // Update with actual path
        driver.manage().window().maximize();
    }

    @Test
    public void testRegularWeekdayPrice() {
        driver.get(baseUrl);

        // Select Monday
        driver.findElement(By.id("day")).sendKeys("Monday");

        // Enter age 30
        driver.findElement(By.id("age")).sendKeys("30");

        // Select regular time
        driver.findElement(By.id("time")).sendKeys("after7");

        // Click calculate
        driver.findElement(By.id("calculate")).click();

        // Verify price
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

    @Test
    public void testInvalidAge() {
        driver.get(baseUrl);

        driver.findElement(By.id("age")).sendKeys("2");
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

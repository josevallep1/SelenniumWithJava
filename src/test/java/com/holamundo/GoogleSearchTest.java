package com.holamundo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class GoogleSearchTest {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void miFirstTest() {
        WebElement searchTxt = driver.findElement(By.name("q"));
        searchTxt.clear();
        searchTxt.sendKeys("CaldoReas");
        searchTxt.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertEquals("CaldoReas - Buscar con Google", driver.getTitle());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

package com.holamundo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DemoQaTexBoxTest {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void addUserInfoTest() throws InterruptedException {
        WebElement fullNameTxt = driver.findElement(By.id("userName"));
        fullNameTxt.clear();
        fullNameTxt.sendKeys("Wilson Valle");

        WebElement emailTxt = driver.findElement(By.id("userEmail"));
        emailTxt.clear();
        emailTxt.sendKeys("test123@tes.com");

        WebElement currentAddressTxt = driver.findElement(By.id("currentAddress"));
        currentAddressTxt.clear();
        currentAddressTxt.sendKeys("calle 123 # 85-96");

        WebElement permanentAddressTxt = driver.findElement(By.id("permanentAddress"));
        permanentAddressTxt.clear();
        permanentAddressTxt.sendKeys("calle 123 # 85-96");

        WebElement submitBtn = driver.findElement(By.id("submit"));
        locatorElement (submitBtn);
        submitBtn.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> outputList = driver.findElements(By.className("mb-1"));
        String expectedOutput = "test";

        for(WebElement output:outputList){
            Assert.assertEquals(expectedOutput, output.getText());

        }
    }

    @Test
    public void addEmailFailTest() throws InterruptedException {
        WebElement emailTxt = driver.findElement(By.id("userEmail"));
        WebElement submitBtn = driver.findElement(By.id("submit"));

        String expectedTooltip = "test";

        emailTxt.clear();
        emailTxt.sendKeys("test123tes.com");
        locatorElement (submitBtn);
        submitBtn.click();
        Assert.assertEquals(expectedTooltip,emailTxt.getAttribute("title"));
    }

    public void locatorElement (WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

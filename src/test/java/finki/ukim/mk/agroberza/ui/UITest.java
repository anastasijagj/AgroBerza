package finki.ukim.mk.agroberza.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class UITest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() throws Exception {
        driver.get("http://localhost:9001/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("jana");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("user");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Where you can acquire directly from the source");

    }

    @Test
    public void registerTest() throws Exception {
        driver.get("http://localhost:9001/login");
        driver.findElement(By.cssSelector("a[href='/register']")).click();

        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("ATTestUser" + LocalDate.now());
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("AT");
        driver.findElement(By.cssSelector("input[name='surname']")).sendKeys("test");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertNotEquals(driver.findElement(By.tagName("h2")).getText(), "Products");
    }
}
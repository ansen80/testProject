package google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearch {

    WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/Downloads/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void strangerThingsSearchGoogle() {

        driver.get("https://www.google.ru/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("https://www.google.ru/"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.ru/");
        Assert.assertEquals(driver.getTitle(), "Google");

        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).click();
        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).sendKeys("Очень странные дела");
        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).sendKeys(Keys.ENTER);


        driver.findElement(By.cssSelector("svg[viewBox=\"0 0 92 30\"]")).isDisplayed();

        Assert.assertEquals(driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).getText(), "Очень странные дела");
        Assert.assertEquals(driver.getTitle(), "Очень странные дела - Поиск в Google");

    }
    @After
    public void tearDown( ){
        driver.quit();
   }
}

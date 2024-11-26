package google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pikabu {

    WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/Downloads/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void verifyPikabu() {

        driver.get("https://pikabu.ru/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://pikabu.ru/");

        Assert.assertEquals(driver.getTitle(),"Горячее – самые интересные и обсуждаемые посты | Пикабу");

        driver.findElement(By.cssSelector("[class=\"pkb-normal-btn header-right-menu__login-button\"]")).click();

        driver.findElement(By.cssSelector("[class=\"auth-modal\"]")).isDisplayed();
        driver.findElement(By.cssSelector("[name=\"username\"]")).isDisplayed();
        driver.findElement(By.cssSelector("[name=\"password\"]")).isDisplayed();
        driver.findElement(By.cssSelector("button.button_success.button_width_100[type=\"submit\"]")).isDisplayed();

        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("button.button_success.button_width_100[type=\"submit\"]")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.findElement(By.cssSelector("//span[class=\"auth__error auth__error_top\"]")).isDisplayed());
        Assert.assertEquals(driver.getTitle(), "Ошибка. Вы ввели неверные данные авторизации");

    }
    @After
    public void tearDown( ){
        driver.quit();
    }
}

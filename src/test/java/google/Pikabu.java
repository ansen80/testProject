package google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
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

        Assert.assertEquals(driver.getTitle(), "Горячее – самые интересные и обсуждаемые посты | Пикабу");

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(), 'Войти')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);


        driver.findElement(By.cssSelector("div[class=\"auth-modal\"]")).isDisplayed();
        driver.findElement(By.xpath("(//input[@class=\"input__input\"])[11]")).isDisplayed();
        driver.findElement(By.xpath("(//input[@class=\"input__input\"])[12]")).isDisplayed();
        driver.findElement(By.cssSelector("button.button_success.button_width_100[type=\"submit\"]")).isDisplayed();

        driver.findElement(By.xpath("(//input[@class=\"input__input\"])[11]")).sendKeys("Qwerty");
        driver.findElement(By.xpath("(//input[@class=\"input__input\"])[12]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("button.button_success.button_width_100[type=\"submit\"]")).sendKeys(Keys.ENTER);


        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"auth__notice\"]")).isDisplayed());
        WebElement element = driver.findElement(By.xpath("//div[@class='auth__notice']"));
        String actualText = element.getText();
        Assert.assertTrue("Текст не совпадает", actualText.contains("Необходимо войти или зарегистрироваться"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

package google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearch {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Инизиализация драйвера wait для Явного ожидания


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Downloads/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); //Ожидание пишется один раз после установки драйвера. Действует на весь код. (Неявное ожидание)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.google.ru/");
    }

    @Test
    public void strangerThingsSearchGoogle() {

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.ru/");
        Assert.assertEquals(driver.getTitle(), "Google");

        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")))); //Ждем пока прогрузится конкретный элемент (Явное ожидание)
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

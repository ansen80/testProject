package google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Pobeda {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Downloads/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.google.com/");
    }

    @Test
    public void searchPobeda() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-label=\"Найти\"]"))).sendKeys("Сайт компании Победа", Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h3"))).click();

        Assert.assertEquals("Полетели в Калининград!", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Полетели в Калининград!')]"))).getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class=\"dp-mj9o31-root-root\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.dp-1vzfy35-root-root:nth-of-type(2)"))).click();

        //Проверка наличия нужных элементов
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Ticket search')]"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Online check-in')]"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Manage my booking')]"))).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

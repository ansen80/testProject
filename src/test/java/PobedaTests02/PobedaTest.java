package PobedaTests02;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PobedaTest {

    String driverPath = "C:/Downloads/chromedriver/chromedriver.exe";
    WebDriver driver;
    Action action;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.pobeda.aero");
        action = new Action(driver);
    }

    @Test
    public void testWebsiteOpenedSuccessfully() {
        //Тест Заголовка
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assert.assertEquals("Неверный заголовок страницы.", expectedTitle, action.getPageTitle());

        //Тест Логотипа на видимость
        Assert.assertTrue("Логотип Победы не отображается.", action.isLogoDisplayed());
    }

    @Test
    public void testSearchBlockVisible() {
        //Переход к блоку поиска
        action.scrollToElement(action.fromField);

        //Блок с полями отображается
        Assert.assertTrue("Блок с поиском билетов не отображается.", action.isSearchBlockVisible());
    }

    @Test
    public void testSearchFunctionality() {
        //Вводим города
        action.enterSearchCriteria("Москва", "Санкт-Петербург");

        //Клик
        action.clickSearchButton();
    }

    @Test
    public void enterSearchCriteriaForCleanForRedBotton() {
        //Вводим города
        action.enterSearchCriteriaForClean("Москва", "Санкт-Петербург");

        //Клик
        action.clickSearchButtonForTestRedButton();
        //Проверяем красный бордер
        action.isDateFromFieldHighlighted();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


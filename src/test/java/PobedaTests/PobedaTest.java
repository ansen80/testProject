package PobedaTests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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
        //Проверяем заголовок страницы
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assert.assertEquals("Неверный заголовок страницы.", expectedTitle, driver.getTitle());

        //Проверяем, что логотип виден
        Assert.assertTrue("Логотип Победы не отображается.", action.isLogoDisplayed());
    }

    @Test
    public void testInformationMenuHover() {
        // Наводим мышь на меню Инфо для проверки отображения
        action.hoverOnInformationMenu();
        Assert.assertTrue("Меню 'Информация' не отображается.", action.isInformationMenuDisplayed());
    }

    @Test
    public void testInformationMenuHeaders() {
        //Проверка заголовков
        List<String> expectedHeaders = Arrays.asList("Подготовка к полету", "Полезная информация", "О компании");

        //Наведение мыши на Инфо
        action.hoverOnInformationMenu();

        //Клик на Инфо
        action.clickOnInformationMenu();

        //Проверка отображения заголовков
        Assert.assertTrue("Не все заголовки меню отображаются.", action.areExpectedHeadersPresent(expectedHeaders));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

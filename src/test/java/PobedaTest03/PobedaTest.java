package PobedaTest03;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.pobeda.aero");
        action = new Action(driver);
    }

    @Test
    public void testManageBooking() {
        //Открытия сайта
        Assert.assertTrue("Текст заголовка страницы некорректен",
                action.isPageTitleCorrect("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"));
        Assert.assertTrue("Логотип Победы не отображается", action.isLogoDisplayed());

        //Клик на управление бронью
        action.clickManageBooking();

        //Поля для ввода отображаются
        Assert.assertTrue("Не отображаются поля для ввода на странице управления бронированием",
                action.areBookingFieldsVisible());

        //Ввод данных и нажатие Поиск
        action.searchBooking("Qwerty", "XXXXXX");

        //Ожидаем открытие нового окна
        action.waitForWindow();

        //Переход на новое окно
        action.switchToNewWindow();

        //Проверяем что сообщение отображается
        Assert.assertTrue("Сообщение об ошибке не отображается", action.isErrorMessageDisplayed());
    }
}


package SelenidePobeda;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Epic;

@Epic("Автотесты для сайта Pobeda")
@Feature("Проверка управления бронированием")

public class PobedaTest {

    private Action action;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        Configuration.timeout = 10000;
        Selenide.open("https://www.pobeda.aero");
        action = new Action();
    }

    @Test
    @Description("Проверка функциональности управления бронированием с ожидаемым положительным результатом")
    @Feature("Проверка управления бронированием")
    public void testManageBooking() {
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assert.assertTrue("Текст заголовка страницы некорректен", action.isPageTitleCorrect(expectedTitle));
        Assert.assertTrue("Логотип Победы не отображается", action.isLogoDisplayed());
        action.clickManageBooking();
        Assert.assertTrue("Не отображаются поля для ввода на странице управления бронированием",
                action.areBookingFieldsVisible());
        action.searchBooking("Qwerty", "XXXXXX");
        action.waitForWindow();
        action.switchToNewWindow();
        Assert.assertTrue("Ошибка не отображается", action.isErrorMessageDisplayed());
    }

    @Test
    @Description("Проверка управления бронированием с неправильными данными для негативного теста")
    @Feature("Проверка управления бронированием")
    public void testManageBookingFail() {
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assert.assertTrue("Текст заголовка страницы некорректен", action.isPageTitleCorrect(expectedTitle));
        Assert.assertTrue("Логотип Победы не отображается", action.isLogoDisplayed());
        action.clickManageBooking();
        Assert.assertTrue("Не отображаются поля для ввода на странице управления бронированием",
                action.areBookingFieldsVisible());
        action.searchBooking("Invalid", "00000");  //Логин и пароль не тот.
        action.waitForWindow();
        action.switchToNewWindow();
        Assert.assertTrue("Ошибка не отображается", action.isErrorMessageDisplayed());
    }
}
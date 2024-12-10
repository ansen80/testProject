package SelenidePobeda;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
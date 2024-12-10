package PobedaSelenide;

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
        Configuration.timeout = 10000; // Тайм-ауты для поиска элементов
        Selenide.open("https://www.pobeda.aero");
        action = new Action();
    }

    @Test
    public void testManageBooking() {
        // Используем метод isPageTitleCorrect для проверки заголовка страницы
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        Assert.assertTrue("Текст заголовка страницы некорректен", action.isPageTitleCorrect(expectedTitle));

        // Использование метода из класса Action для проверки логотипа
        Assert.assertTrue("Логотип Победы не отображается", action.isLogoDisplayed());

        // Клик по кнопке "Управление бронированием"
        action.clickManageBooking();

        // Проверка видимости полей для ввода
        Assert.assertTrue("Не отображаются поля для ввода на странице управления бронированием",
                action.areBookingFieldsVisible());

        // Ввод данных и нажатие кнопки "Поиск"
        action.searchBooking("Qwerty", "XXXXXX");

        // Ожидание появления нового окна
        action.waitForWindow();

        // Переключение на новое окно
        action.switchToNewWindow();

        // Проверка наличия ошибки, используя метод isErrorMessageDisplayed
        Assert.assertTrue("Ошибка не отображается", action.isErrorMessageDisplayed());
    }
}
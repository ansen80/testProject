package SelenidePobeda;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("Автотесты для сайта Pobeda")
@Feature("Проверка управления бронированием")
public class PobedaTest {

    private Action action;

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeEach
    // Используем аннотацию @Before для подготовки перед каждым тестом
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
        assert action.isPageTitleCorrect(expectedTitle) : "Текст заголовка страницы некорректен";
        action.isLogoDisplayed();
        action.clickManageBooking();
        action.areBookingFieldsVisible();
        action.searchBooking("Qwerty", "XXXXXX");
        action.waitForWindow();
        action.switchToNewWindow();
        assert action.isErrorMessageDisplayed() : "Ошибка не отображается";
    }

    @Test
    @Description("Проверка управления бронированием с неправильными данными для негативного теста")
    @Feature("Проверка управления бронированием")
    public void testManageBookingFail() {
        String expectedTitle = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";
        assert action.isPageTitleCorrect(expectedTitle) : "Текст заголовка страницы некорректен";
        action.isLogoDisplayed();
        action.clickManageBooking();
        action.areBookingFieldsVisible();
        action.searchBooking("Invalid", "00000");  // Логин и пароль не тот.
        action.waitForWindow();
        action.switchToNewWindow();
        assert action.isErrorMessageDisplayed() : "Ошибка не отображается";
    }
}

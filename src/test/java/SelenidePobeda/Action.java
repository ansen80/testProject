package SelenidePobeda;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Action {

    private final SelenideElement logo = $x("//div[contains(@class,'lottie')]");
    private final SelenideElement manageBookingLink = $x("//button[.//div[text()='Управление бронированием']]");
    private final SelenideElement lastNameField = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement bookingNumberField = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");
    private final SelenideElement errorMessage = $x("//div[@ng-if=\"vm.errorMessage\"]");

    //Заголовок страницы
    @Step("Проверка заголовка страницы")
    public boolean isPageTitleCorrect(String expectedTitle) {
        return title().equals(expectedTitle);
    }

    //Наличие логотипа
    @Step("Проверка отображения логотипа")
    public void isLogoDisplayed() {
        logo.shouldBe(visible);
    }

    //Клик на Управление бронированием
    @Step("Клик на кнопку 'Управление бронированием'")
    public void clickManageBooking() {
        manageBookingLink.click();
    }

    //Кнопки видны на странице
    @Step("Проверка видимости полей для ввода бронирования")
    public void areBookingFieldsVisible() {
        lastNameField.shouldBe(visible);
        bookingNumberField.shouldBe(visible);
        searchButton.shouldBe(visible);
    }

    //Ввод данных и клик по кнопке поиска
    @Step("Ввод фамилии клиента и номера бронирования: {0}, {1}")
    public void searchBooking(String lastName, String bookingNumber) {
        lastNameField.setValue(lastName);
        bookingNumberField.setValue(bookingNumber);
        searchButton.click();
    }

    //Ждем новое окно
    @Step("Ожидание нового окна")
    public void waitForWindow() {
        switchTo().window(1);
    }

    //Новое окно
    @Step("Переключение на новое окно")
    public void switchToNewWindow() {
        switchTo().window(1); //Переключаемся на новое окно
    }

    @Step("Проверка отображения ошибки")
    public boolean isErrorMessageDisplayed() {
        try {
            errorMessage.shouldBe(visible);
            String actualErrorMessage = errorMessage.getText();
            if (!actualErrorMessage.toLowerCase().contains("заказ с указанными параметрами не найден")) {
                throw new AssertionError("Ошибка: " + actualErrorMessage);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package SelenidePobeda;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Step;

public class Action {

    private SelenideElement logo = $(By.cssSelector("img[src*='logo-rus-white']"));
    private SelenideElement manageBookingLink = $(By.xpath("//button[.//div[text()='Управление бронированием']]"));
    private SelenideElement lastNameField = $(By.xpath("//input[@placeholder='Фамилия клиента']"));
    private SelenideElement bookingNumberField = $(By.xpath("//input[@placeholder='Номер бронирования или билета']"));
    private SelenideElement searchButton = $(By.xpath("//button[@type='submit']"));
    private SelenideElement errorMessage = $(By.xpath("//div[@ng-if=\"vm.errorMessage\"]"));

    //Заголовок страницы
    @Step("Проверка заголовка страницы")
    public boolean isPageTitleCorrect(String expectedTitle) {
        return title().equals(expectedTitle);
    }
    //Наличие логотипа
    @Step("Проверка отображения логотипа")
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
    //Клик на Управление бронированием
    @Step("Клик на кнопку 'Управление бронированием'")
    public void clickManageBooking() {
        manageBookingLink.click();
    }
    //Кнопки видны на странице
    @Step("Проверка видимости полей для ввода бронирования")
    public boolean areBookingFieldsVisible() {
        return lastNameField.isDisplayed() && bookingNumberField.isDisplayed() && searchButton.isDisplayed();
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

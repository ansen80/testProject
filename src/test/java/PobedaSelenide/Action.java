package PobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Action {

    private SelenideElement logo = $(By.cssSelector("img[src*='logo-rus-white']"));
    private SelenideElement manageBookingLink = $(By.xpath("//button[.//div[text()='Управление бронированием']]"));
    private SelenideElement lastNameField = $(By.xpath("//input[@placeholder='Фамилия клиента']"));
    private SelenideElement bookingNumberField = $(By.xpath("//input[@placeholder='Номер бронирования или билета']"));
    private SelenideElement searchButton = $(By.xpath("//button[@type='submit']"));
    private SelenideElement errorMessage = $(By.xpath("//div[@ng-if=\"vm.errorMessage\"]"));

    // Заголовок страницы
    public boolean isPageTitleCorrect(String expectedTitle) {
        return title().equals(expectedTitle);
    }

    // Наличие логотипа
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    // Кликаем на Управление бронированием
    public void clickManageBooking() {
        manageBookingLink.click();
    }

    // Проверяем, что кнопки видны на странице
    public boolean areBookingFieldsVisible() {
        return lastNameField.isDisplayed() && bookingNumberField.isDisplayed() && searchButton.isDisplayed();
    }

    // Вводим данные и нажимаем кнопку поиск
    public void searchBooking(String lastName, String bookingNumber) {
        lastNameField.setValue(lastName);
        bookingNumberField.setValue(bookingNumber);
        searchButton.click();
    }

    // Ждем новое окно
    public void waitForWindow() {
        switchTo().window(1);
    }

    // Переход на новое окно
    public void switchToNewWindow() {
        // Переключаемся на новое окно (индекс 1, так как 0 — это основное окно)
        switchTo().window(1);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            errorMessage.shouldBe(visible); // Ожидание появления ошибки
            String actualErrorMessage = errorMessage.getText();
            if (!actualErrorMessage.toLowerCase().contains("заказ с указанными параметрами не найден")) {
                throw new AssertionError("Ошибка: " + actualErrorMessage); // Выбрасываем исключение, если ошибка не совпадает
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package PobedaTest03;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {

    WebDriver driver;

    @FindBy(css = "img[src*='logo-rus-white']")
    WebElement logo;

    @FindBy(xpath = "//button[.//div[text()='Управление бронированием']]")
    WebElement manageBookingLink;

    @FindBy(xpath = "//input[@placeholder='Фамилия клиента']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Номер бронирования или билета']")
    WebElement bookingNumberField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@ng-if=\"vm.errorMessage\"]")
    WebElement errorMessage;

    public Action(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Заголовок страницы
    public boolean isPageTitleCorrect(String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }

    //Наличие логотипа
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    //Кликаем на Управление бронированием
    public void clickManageBooking() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(manageBookingLink));
        manageBookingLink.click();
    }


    //Проверяем что кнопки видны на странице
    public boolean areBookingFieldsVisible() {
        return bookingNumberField.isDisplayed() && lastNameField.isDisplayed() && searchButton.isDisplayed();
    }

    //Вводим данные и нажимаем кнопку поиск
    public void searchBooking(String lastName, String bookingNumber) {
        lastNameField.sendKeys(lastName);
        bookingNumberField.sendKeys(bookingNumber);
        searchButton.click();
    }

    //Ждем новое окно
    public void waitForWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> webDriver.getWindowHandles().size() > 1);
    }

    //Переход на новое окно
    public void switchToNewWindow() {
        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }


    //Проверяем наличие ошибки в новом окне
    public boolean isErrorMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return error.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Метод для проверки текста ошибки
    public void checkErrorMessage() {
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.toLowerCase().contains("Заказ с указанными параметрами не найден"));
    }
}

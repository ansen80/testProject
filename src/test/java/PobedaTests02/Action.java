package PobedaTests02;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class=\"dp-1x07rlv-lottie\"]")
    WebElement logo;
    @FindBy(xpath = "(//input[@placeholder=\"Откуда\"])[1]")
    WebElement fromField;
    @FindBy(xpath = "(//input[@placeholder=\"Куда\"])[1]")
    WebElement toField;
    @FindBy(xpath = "(//input[@placeholder=\"Туда\"])[1]")
    WebElement dateFromField;
    @FindBy(xpath = "( //input[@placeholder=\"Обратно\"])[1]")
    WebElement dateBackField;
    @FindBy(xpath = "(//span[@class='dp-wjta0n-root'])[1]")
    WebElement searchButton;

    public Action(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Заголовок страницы
    public String getPageTitle() {
        return driver.getTitle();
    }

    //Наличие логотипа
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    //Наличие блока поиска
    public boolean isSearchBlockVisible() {
        return fromField.isDisplayed() && toField.isDisplayed() && dateFromField.isDisplayed() && dateBackField.isDisplayed();
    }

    //Заполнение полей с предварительной чисткой
    public void enterSearchCriteria(String from, String to) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        //Откуда
        fromField.clear();
        fromField.click();
        fromField.sendKeys(from);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();


        //Куда
        toField.clear();
        toField.click();
        toField.sendKeys(to);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    //Клик на поиск
    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    //Проверка, что поле Куда с красной обводкой
    public boolean isDateFromFieldHighlighted() {
        String borderColor = dateFromField.getCssValue("border-color");
        return borderColor.equals("#ff1681ff") || borderColor.equals("#f50071ff");
    }

    //Прокрутка к поиску
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}




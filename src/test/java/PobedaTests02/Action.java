package PobedaTests02;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Action {

    WebDriver driver;

    @FindBy(css = "img[src*=\"logo-rus-white\"]")
    WebElement logo;

    @FindBy(css = "input.dp-1mh0xcc-root-control[placeholder=\"Откуда\"]")
    WebElement fromField;

    @FindBy(css = "input.dp-1mh0xcc-root-control[placeholder=\"Куда\"]")
    WebElement toField;

    @FindBy(css = "input.dp-1mh0xcc-root-control[placeholder=\"Туда\"][data-empty=\"true\"]")
    WebElement dateFromField;

    @FindBy(css = "input.dp-1mh0xcc-root-control[placeholder=\"Обратно\"][data-empty=\"true\"]")
    WebElement dateBackField;

    @FindBy(css = "div[class='dp-1y916xi-root-root-root']")
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
        //Откуда
        fromField.clear();
        fromField.sendKeys(Keys.CONTROL + "a");
        fromField.sendKeys(Keys.DELETE);
        fromField.sendKeys(from);
        fromField.sendKeys(Keys.ENTER);

        //Куда
        toField.clear();
        toField.sendKeys(Keys.CONTROL + "a");
        toField.sendKeys(Keys.DELETE);
        toField.sendKeys(to);
        toField.sendKeys(Keys.ENTER);
    }

    //Клик на поиск
    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public void enterSearchCriteriaForClean(String from, String to) {
        //Тест для красной обводки. Нужно сперва все удалить и нажать Поиск
        fromField.clear();
        fromField.sendKeys(Keys.CONTROL + "a");
        fromField.sendKeys(Keys.DELETE);
        fromField.sendKeys(from);
        fromField.sendKeys(Keys.ENTER); }

    //Клик на Поиск
    public void clickSearchButtonForTestRedButton() {
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



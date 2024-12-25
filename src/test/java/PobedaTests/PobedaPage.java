package PobedaTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PobedaPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public PobedaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Ожидание видимости элемента
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Ожидание кликабельности элемента
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Наводим курсор на элемент
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //Наличие всех ожидаемых текстов в элементах
    public boolean areExpectedTextsPresent(List<WebElement> elements, List<String> expectedTexts) {
        if (elements.size() != expectedTexts.size()) {
            return false;
        }
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).getText().trim().equals(expectedTexts.get(i))) {
                return false;
            }
        }
        return true;
    }
}


package PobedaTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Action extends PobedaPage {

    @FindBy(css = "img[src*=\"logo-rus-white\"]")
    WebElement logo;

    @FindBy(css = "a[href=\"/information\"]")
    WebElement informationMenu;

    @FindBy(css = "div[class=\"dp-14ry67i-root-root\"]")
    List<WebElement> informationMenuHeaders;

    public Action(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Тест отображения логотипа
    public boolean isLogoDisplayed() {
        waitForElementToBeVisible(logo);
        return logo.isDisplayed();
    }

    //Наводим мышь на меню Информация
    public void hoverOnInformationMenu() {
        hoverOverElement(informationMenu);
    }

    //Проверка отображения меню
    public boolean isInformationMenuDisplayed() {
        waitForElementToBeVisible(informationMenu);
        return informationMenu.isDisplayed();
    }

    //Клик на меню Информация
    public void clickOnInformationMenu() {
        informationMenu.click();
    }

    //Проверка, что все ожидаемые заголовки отображаются
    public boolean areExpectedHeadersPresent(List<String> expectedHeaders) {
        return areExpectedTextsPresent(informationMenuHeaders, expectedHeaders);
    }

    //Наводим мышь
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    //Проверяем текст в элементах
    public boolean areExpectedTextsPresent(List<WebElement> elements, List<String> expectedTexts) {
        for (WebElement element : elements) {
            String elementText = element.getText();
            if (!expectedTexts.contains(elementText)) {
                return false;
            }
        }
        return true;
    }
}

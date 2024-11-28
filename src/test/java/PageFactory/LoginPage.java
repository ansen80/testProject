package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(name = "uid")
    WebElement userName;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(className = "barone")
    WebElement titleText;

    @FindBy(name = "btnLogin")
    WebElement login;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Метод initElements будет создавать все параметры типа WebElements
        PageFactory.initElements(driver, this);
    }

    //Заполняем поле ввода логина userName значением strUserName
    public void setUserName(String strUserName) {
        userName.sendKeys(strUserName);
    }

    //Заполняем поле ввода пароля password значениями strPassword
    public void setPassword(String strPassword) {
        password.sendKeys(strPassword);
    }

    //Клик по кнопке Логин
    public void clickLogin() {
        login.click();
    }

    //Получаем заголовок страницы логина
    public String getLoginTitle() {
        return titleText.getText(); }

    /**
     * Метод для ав оризации на сайте Guru99
     * @param strUserName
     * @param strPassword
     * @return
     */

    public void loginToGuru99(String strUserName, String strPassword) {
        //Заполняем логин
        this.setUserName(strUserName);
        //Заполняем пароль
        this.setPassword(strPassword);
        //Кликаем кнопку логина
        this.clickLogin();
    }
}






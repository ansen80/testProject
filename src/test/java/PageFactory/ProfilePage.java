package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    WebDriver driver;

    @FindBy(xpath = "//table//tr[@class='heading3']")
    WebElement userName;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Получаем имя юзера с домашней страницы
    public String getHomePageDashboardUserName() {
        return userName.getText();
    }
}

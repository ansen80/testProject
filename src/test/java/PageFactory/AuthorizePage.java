package PageFactory;

import Pom.LoginPage;
import Pom.ProfilePage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AuthorizePage {

    String driverPath = "/Downloads/chromedriver/chromedriver.exe";

    WebDriver driver;

    LoginPage objLogin;

    ProfilePage objHomePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo.guru99.com/V4/");
    }

    @Test
    public void testSuccessfullyLogin() {
        //Создание экземпляра обьекта страницы логина и страницы профиля
        objLogin = new LoginPage(driver);
        objHomePage = new ProfilePage(driver);

        //Проверяем заголовок страницы
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        //Входим под аккаунтом
        objLogin.loginToGuru99("mngr602723", "qudaraq");

        //Проверяем, что мы действительно попали на страницу профиля
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id: mgr123"));
    }
}

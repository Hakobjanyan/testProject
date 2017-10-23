package work.Isui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arsen on 23.10.2017.
 */
public class AddUser {

    private WebDriver driver;
    private WebDriverWait wait;
    MainPage mainPage;


    @BeforeClass
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        mainPage = new MainPage(driver);

        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testAddUser() throws IOException {
        mainPage.path = "c:\\tmp\\screenshot";

        mainPage.getUrl("http://isui-test.osslabs.ru");
        mainPage.login("admin", "123456");
        mainPage.usersRegisty();
        mainPage.dataNewUser("atest", "Иван", "Иванов", "iv-ivanov@list.ru", "123456");
        mainPage.saveButtonClick();
        mainPage.usersRegisty();
        mainPage.searchUser("atest");
        mainPage.deleteButtonClick();
        mainPage.logout();
    }




    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}

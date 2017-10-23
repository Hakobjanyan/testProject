package work.Isui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by ars on 23.10.2017.
 */
public class DeleteUser {
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
    public void testDeleteUser() {
        mainPage.getUrl("http://isui-test.osslabs.ru");
        mainPage.login("admin", "123456");
        mainPage.usersRegisty();
        mainPage.searchUser("atest");
        mainPage.deleteButtonClick();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}

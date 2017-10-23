package work.Isui_dev_NS;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by qas on 03.05.2017.
 */
public class Responsible {
    static WebDriver driver;


    @Before
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://isui-dev.osslabs.ru/platform");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginDialog"), "Вход"));
        driver.findElement(By.id("j_username")).sendKeys("rukOGE");
        driver.findElement(By.id("j_password")).sendKeys("123456", Keys.ENTER);
        //(new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginDialog"),"Реестр нештатных ситуаций"));
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.
                textToBePresentInElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/h2"), "Нештатные ситуации (мои активные"));

        //открыть навигатор
        WebElement navigate = driver.findElement(By.id("navigatorUnit"));
        if (!navigate.getText().contains("Отчеты по НС")) {
            driver.findElement(By.id("menu-button")).click();
        }
    }

    @Test
    public void otvetstvenniy() {

        // Не назначенные
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/form/div[2]/div[1]/ul/li[1]/ul/li[1]/span/span[3]/a")).click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("form:dataObjects")));
        // driver.findElement(By.cssSelector(".a.ui-link.ui-widget > div:nth(0)")).click();

        // на первую
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form/table/tbody/tr/td[1]/div/span/span/table/tbody/tr/td/div[2]/div[1]/table/tbody/tr[1]/td[1]/div/a/div")).click();


        (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[1]/div/table[1]/tbody/tr/td[4]/div/button")));

        // нажать кнопку "Принять"
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[3]/div[2]/div/div[1]/div/div/div[1]/div/table/tbody/tr[2]/td/button")).click();

        // Ждать пока выйдет сообщение о сохранений
        (new WebDriverWait(driver,10)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container"), "сохранены"));

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[3]/div[2]/div/div[1]/div/div/div[2]/h3")).click();
        //Assert.assertEquals();
    }

    @After
    public void testDown(){
        driver.quit();
    }
}
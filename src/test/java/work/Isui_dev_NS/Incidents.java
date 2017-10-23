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

import java.util.concurrent.TimeUnit;

/**
 * Created by qas on 02.05.2017.
 */
public class Incidents {

    static WebDriver driver;


    @Before
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://isui-dev.osslabs.ru/platform");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginDialog"), "Вход"));
        driver.findElement(By.id("j_username")).sendKeys("dispRDP");
        driver.findElement(By.id("j_password")).sendKeys("123456", Keys.ENTER);
        //(new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginDialog"),"Реестр нештатных ситуаций"));

    }

    public static WebElement numberIncident() {
        return driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[1]/div/table[1]/tbody/tr/td[2]/span[2]"));
    }

    @Test
    public  void otkaz(){
        WebDriverWait waitTenSeconds = new WebDriverWait(driver, 10);//wait 10 seconds
        WebDriverWait waitFiveSeconds = new WebDriverWait(driver, 5);//wait 5 seconds

        waitFiveSeconds.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/form/table/tbody/tr/td[1]/div/span/span/div[1]/button[3]")));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form/table/tbody/tr/td[1]/div/span/span/div[1]/button[3]")).click();   // click is button "Отказ оборудования"

        waitTenSeconds.until(ExpectedConditions.
                textToBePresentInElementLocated(By.id("incidentsForm"), "Отказ (повреждение) оборудования"));

        //Select type incidents
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[3]/div[1]/div/table/tbody/tr[1]/td/div/div[1]/div[2]/table/tbody/tr[3]/td[2]/div/div[1]/span/button")).click();
        waitFiveSeconds.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[11]/ul/li[4]/div")));
        driver.findElement(By.xpath("/html/body/div[11]/ul/li[4]/div")).click();

        //Признак изменения режима МТ
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[3]/div[1]/div/table/tbody/tr[1]/td/div/div[1]/div[2]/table/tbody/tr[4]/td[2]/div/div[1]/span/button")).click();
        waitFiveSeconds.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[13]/ul/li[1]/div")));
        driver.findElement(By.xpath("/html/body/div[13]/ul/li[1]/div")).click();


        // Наименование МН
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[3]/div[1]/div/table/tbody/tr[4]/td/div/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[1]/span/button")).click();
        waitFiveSeconds.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[19]/ul/li[1]/div")));
        driver.findElement(By.xpath("/html/body/div[19]/ul/li[1]/div")).click();




        // click in button "Зарегистировать"
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[1]/div/table[1]/tbody/tr/td[4]/div/button")).click();

        // Ждать пока не появиться сообщение о сохранений
        waitTenSeconds.until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container"), "сохранены"));

        // Проверка на появление таймера
        Assert.assertNotNull(ExpectedConditions.presenceOfElementLocated(By.id("incidentsForm:timerId")));


        String str = numberIncident().getText();


        // Кнопка назад
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/form[2]/div/div[1]/div/table[1]/tbody/tr/td[5]/div/button")).click();

        //Поиск НС
        waitTenSeconds.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]"), "Реестр нештатных ситуаций"));
        driver.findElement(By.id("form:dataObjectsList:dataObjectsTableFilter")).sendKeys(str,Keys.ENTER);
        Assert.assertNotNull(driver.findElement(By.id("form:dataObjectsList:dataObjectsTable_data")));
    }





    @After
    public void testDown() {
        driver.quit();
    }
}


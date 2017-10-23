package work.Isui_dev_admin_add_delete_seache_update;

import org.apache.http.util.Asserts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Arsen on 26.04.2017.
 */
public class OpenDriver {

    static WebDriver driver;

    @Before
    public  void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://isui-dev.osslabs.ru/platform/");
        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("loginDialog"),"Вход"));
        driver.findElement(By.id("j_username")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("123456", Keys.ENTER);
        // открыть навигатор если он закрыт

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement navigate = driver.findElement(By.id("navigatorUnit"));



    }


    public void addUser() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form[2]")).click();
        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("userForm"),"Корпоративный E-Mail"));

        // нажать наи галочку "Доступ к системе"
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[1]/td/table[1]/tbody/tr[1]/td[1]/label")).click();
        (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(By.id("userForm:confirm")));


        driver.findElement(By.id("userForm:loginname")).sendKeys("Test"); // в поле Логин ввести значение

        driver.findElement(By.id("userForm:firstname")).sendKeys("Иван");   // в поле имя ввусти

        driver.findElement(By.id("userForm:lastname")).sendKeys("Иванов");   // В поле отчество

        driver.findElement(By.id("userForm:email")).sendKeys("test@test.ru");   // поле емаил

        driver.findElement(By.id("userForm:password")).sendKeys("123456");  //поле пароль

        driver.findElement(By.id("userForm:confirm")).sendKeys("123456");   // подтвердение пароля


        driver.findElement(By.id("userForm:tenant_label")).click();     // организационная единица

        driver.findElement(By.xpath("//*[@id=\"userForm:tenant_1\"]")).click();  //выбыраем

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[1]")).click(); //кнопка сохранить

        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container"),"сохранены"));


        WebElement navigate = driver.findElement(By.id("navigatorUnit"));
        //проерка, открыт ли навигатор
        if(!navigate.getText().contains("Атмосферные явления")){
            driver.findElement(By.id("menu-button")).click();
        }
        // Реестр пользователи
        driver.findElement(By.xpath("//*[@id=\"navigatorForm:navigator:0_1:n\"]")).click();

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[3]")).click(); // кнопка закрыть
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[3]")).click(); // кнопка закрыть
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[3]")).click(); // кнопка закрыть
    }

    public void deleteUser(){

        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("j_idt112:usersTable_data"), "Test"));
        driver.findElement(By.linkText("Test")).click();        // открываем профиыль пользвателя

        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("userForm"),"Корпоративный E-Mail"));

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[2]")).click();     // кнопка удалить

        driver.switchTo().alert().accept();
        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container"),"удалены"));
    }

    public void updateUser(){
        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("j_idt112:usersTable_data"), "Test"));
        driver.findElement(By.linkText("Test")).click();
        driver.findElement(By.id("userForm:firstname")).clear();
        driver.findElement(By.id("userForm:firstname")).sendKeys("ИванИван");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[1]")).click(); //кнопка сохранить

        (new WebDriverWait(driver,5)).until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container"),"сохранены"));
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/form/table/tbody/tr[3]/td/button[3]")).click(); // кнопка закрыть



    }

    public void searchUser(String searchText){

        (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.className("parent-page-block")));
        // Поиск пользоателя
        WebElement select = driver.findElement(By.xpath(".//*[@id='j_idt121:usersTable:j_idt122:filter']"));
        select.sendKeys(searchText);



    }



    @After
    public void testDown(){
        driver.quit();
    }
}

package work.osslabs;


import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by user on 24.04.17.
 */
public class test1 extends test2{


    public void addUser(){

        driver.findElement(By.id("j_idt129")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // нажать наи галочку "Доступ к системе"
        driver.findElement(By.id("userForm:j_idt119")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        // в поле Логин ввести значение
        driver.findElement(By.id("userForm:loginname")).sendKeys("Test");
        // в поле имя ввусти
        driver.findElement(By.id("userForm:firstname")).sendKeys("Иван");
        // В поле отчество
        driver.findElement(By.id("userForm:lastname")).sendKeys("Иванов");
        // поле емаил
        driver.findElement(By.id("userForm:email")).sendKeys("test@test.ru");
        //поле пароль
        driver.findElement(By.id("userForm:password")).sendKeys("123456");
        // подтвердение пароля
        driver.findElement(By.id("userForm:confirm")).sendKeys("123456");
        // организационная единица
        driver.findElement(By.id("userForm:tenant_label")).click();
        driver.findElement(By.xpath("//*[@id=\"userForm:tenant_1\"]")).click();
        driver.findElement(By.id("userForm:j_idt153")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("userForm:j_idt156")).click();
        driver.findElement(By.id("userForm:j_idt156")).click();
        // driver.switchTo().alert().accept();
    }

    public void deleteUser(){
        driver.navigate().to("http://isui-dev.osslabs.ru/platform/views/security/usrmng/user.xhtml?id=" + "Test");


        driver.findElement(By.xpath("//*[@id=\"userForm:j_idt155\"]")).click();
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("userForm:j_idt156")).click();
    }

    public void searchUser(){


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Поиск пользоателя
        WebElement select = driver.findElement(By.xpath("//*[@id=\"j_idt112:usersTable:j_idt113:filter\"]"));
        select.sendKeys("Test");
    }



    @Test
    public void logPass() {

        // авторизация
        driver.get("http://isui-dev.osslabs.ru/platform/");
        driver.findElement(By.id("j_username")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("123456", Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // открыть навигатор если он закрыт
        WebElement airbags = driver.findElement(By.id("navigatorUnit"));
        if(!airbags.getText().contains("Атмосферные явления")){
            driver.findElement(By.id("menu-button")).click();
        }
// Реестр пользователи
        driver.findElement(By.xpath("//*[@id=\"navigatorForm:navigator:0_1:n\"]")).click();


        searchUser();

        // проверка, есть ли в реестре пользователь
        if (driver.findElement(By.id("j_idt112:usersTable_data")).getText().contains("Test")){
            deleteUser();
        }
        addUser();
    }
}


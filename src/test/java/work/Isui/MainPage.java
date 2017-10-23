package work.Isui;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arsen on 14.07.2017.
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    private boolean acceptNextAlert = true;

    public String path;


    public MainPage(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, 30);
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Меню
    @FindBy(xpath = ".//*[@id='mainmenuForm:mainmenu']/ul/li[1]/a/span[1]")
    WebElement menu;

    // Меню-Безопасность
    @FindBy(xpath = ".//*[@id='mainmenuForm:mainmenu']/ul/li[1]/ul/li[3]/a/span[1]")
    WebElement seciurity;

    // Меню-Безопасность-пользователи
    @FindBy(xpath = ".//*[@id='mainmenuForm:mainmenu']/ul/li[1]/ul/li[3]/ul/li[3]/a/span")
    WebElement users;

    // Кнопка создать пользователья
    @FindBy(xpath = ".//*[@id='j_idt138']/a")
    WebElement createButton;

    // кнопка сохранить
    @FindBy(xpath = ".//*[@id='userForm:j_idt164']")
    WebElement deleteButton;

    // кнопка Удалить
    @FindBy(xpath = ".//*[@id='userForm:j_idt162']")
    WebElement saveButton;

    // кнопка закрыть
    @FindBy(xpath = ".//*[@id='userForm:j_idt165']")
    WebElement closeButton;

    // Реестр пользователи
    public void usersRegisty() {
        menu.click();
        seciurity.click();
        users.click();
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='j_idt121:usersTable_head']")).getText().contains("Логин"));
        screen(path);
    }

    //  флорма создание Пользователи
    public void dataNewUser(String loginName, String firstName, String lastName, String eMail, String password) {

        createButton.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("userForm"), "Корпоративный E-Mail"));

        // нажать наи галочку "Доступ к системе"
        driver.findElement(By.xpath(".//*[@id='userForm:enabled']/div[2]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userForm:confirm")));


        driver.findElement(By.id("userForm:loginname")).clear();
        driver.findElement(By.id("userForm:loginname")).sendKeys(loginName); // в поле Логин ввести значение
        // Assert.assertTrue(driver.findElement(By.id("growl_container")).getText().contains(""));

        driver.findElement(By.id("userForm:firstname")).clear();
        driver.findElement(By.id("userForm:firstname")).sendKeys(firstName);   // в поле имя ввусти

        driver.findElement(By.id("userForm:lastname")).clear();
        driver.findElement(By.id("userForm:lastname")).sendKeys(lastName);   // В поле отчество

        driver.findElement(By.id("userForm:email")).clear();
        driver.findElement(By.id("userForm:email")).sendKeys(eMail); // поле емаил
        // Assert.assertTrue(driver.findElement(By.id("growl_container")).getText().contains(""));
        driver.findElement(By.id("userForm:password")).clear();
        driver.findElement(By.id("userForm:password")).sendKeys(password);  //поле пароль
        driver.findElement(By.id("userForm:confirm")).clear();
        driver.findElement(By.id("userForm:confirm")).sendKeys(password);   // подтвердение пароля


        driver.findElement(By.id("userForm:tenant_label")).click();     // организационная единица

        driver.findElement(By.xpath("//*[@id=\"userForm:tenant_1\"]")).click();  //выбыраем


    }

    public void searchUser(String searchText) {
        driver.findElement(By.xpath(".//*[@id='j_idt121:usersTable:j_idt122:filter']")).sendKeys(searchText);
        waitLoadAjaxFon();
        driver.findElement(By.linkText(searchText)).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("userForm"), "Корпоративный E-Mail"));
        screen(path);
        System.out.println(driver.findElement(By.xpath(".//*[@id='userForm:loginname']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='userForm:loginname']")).getText().contains(searchText));
    }



    public void screen(String path){
        int i = 0;
        while ((new File(path + i + ".png")).exists()) {
            i++;
        }
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(path + i + ".png"));
        }catch (IOException e){System.out.println("Что то пошло не так в методе screen");}

    }

    public boolean waitDataSaved(){
        waitLoadAjaxFon();
        try{
           // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container")));
            Assert.assertTrue(driver.findElement(By.id("growl_container")).getText().contains("сохранены"));
            screen(path);
            return true;
        }catch(AssertionError e){
            screen(path);
            return false;
        }
    }

    public boolean waitDataDelete(){
        waitLoadAjaxFon();
        try{
            // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("growl_container")));
            Assert.assertTrue(driver.findElement(By.id("growl_container")).getText().contains("удалены"));
            screen(path);
            return true;
        }catch(AssertionError e){
            screen(path);
            return false;
        }
    }

    public void saveButtonClick(){
        saveButton.click();
        Assert.assertTrue(waitDataSaved());
    }

    public void deleteButtonClick(){
        deleteButton.click();
        driver.switchTo().alert().accept();
        waitDataDelete();
    }



    /***************************************************  считает с файла  ********************************************/
    public static StringBuilder read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();

        File file = new File(fileName);
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Возвращаем полученный текст с файла
        return sb;
    }
    /******************************************************************************************************************/

    /***/
    public void getUrl(String url) {
        baseUrl = url;
        driver.navigate().to(baseUrl + "/platform/views/system/login.xhtml");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='loginDialog']/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='loginDialog']/div[1]")).getText().equals("Вход"),
                " Ошибка: адресс не открылся ");
    }
    /***/

    /**********************************************  Авторизация  *****************************************************/
    public boolean login(String username, String password) {


        driver.findElement(By.id("j_username")).clear();
        driver.findElement(By.id("j_username")).sendKeys(username);
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();

        // Assert.assertFalse(driver.findElement(By.id("loginForm")).getText().contains("Неверная пара логин/пароль"), "Ошибка авторизации");
        try {
            Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='j_idt111']/a[2]")).
                    getText().contains("Выйти"), username + " не смог авторизоваться");
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    /******************************************************************************************************************/




    /**********************************************  Закрепить файл  **************************************************/
    public void download(String id_elements, String path) {
        File file = new File(path);
        driver.findElement(By.id(id_elements)).sendKeys(file.getAbsolutePath());
    }
    /******************************************************************************************************************/


    /****************************************************  ВЫЙТИ  *****************************************************/
    public void logout() {
        driver.navigate().to(baseUrl + "/platform/logout");
        Assert.assertTrue(isElementPresent(By.id("loginBtn")));
    }
    /******************************************************************************************************************/


    /*********************************************  Возвращает id задания  *******8************************************/
    public String idGos() {
        return driver.findElement(By.id("form:j_idt204:dataObjectSectionsContainer:" +
                "dataObjectControllerInstanceIdApplication")).getAttribute("title");
    }
    /******************************************************************************************************************/


    /*********************************************   Запись в файл  ***************************************************/
    public void WriteToFile(String PatchFile, String idGos) throws Exception {
        File file = new File(PatchFile);
        FileWriter writer = new FileWriter(file);
        writer.write(idGos);
        writer.flush();
        writer.close();
    }
    /******************************************************************************************************************/





    /*************************************** Проверяет появился ли фрейм  ********************************************/
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }
    /******************************************************************************************************************/


    /********************************************  Закрыть фрейм  *****************************************************/
    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    /******************************************************************************************************************/


    /**************************************  ждать успешного сохранения данных  ***************************************/


    /******************************************************************************************************************/


    public void waitApplicationSubmitted() {

        waitLoadAjaxFon();
        Assert.assertTrue(driver.findElement(By.cssSelector("span.ui-growl-title")).getText().contains("Заявление подано"));
    }


    /*************************************    Возвращает сегоднящную дату   *******************************************/
    public String todaysData() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

        return formatForDateNow.format(dateNow);
    }
    /******************************************************************************************************************/


    /****************************************  Проверят есть ли элемент   *********************************************/
    boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {///java.util.NoSuchElementException
            return false;
        }
    }
    /******************************************************************************************************************/


    /**************************************  Ждать пока пройдет ajax запрос  ******************************************/
    public void waitLoadAjaxFon() {

        if (isElementPresent(By.id("load-ajax-fon"))) {
            wait.until(ExpectedConditions.not(ExpectedConditions.
                    visibilityOfElementLocated(By.id("load-ajax-fon"))));
        }
        /*try {
            Assert.assertTrue(isElementPresent(By.id("load-ajax-fon")));
            wait.until(ExpectedConditions.not(ExpectedConditions.
                    visibilityOfElementLocated(By.id("load-ajax-fon"))));
        }catch(NoSuchElementException e){System.out.println("Исключение сработало");}
    */
    }

    /******************************************************************************************************************/

    public int randomInt(int a, int b) {

        int random_number; // Генерация 1-го числа
        random_number = a + (int) (Math.random() * b);
        return random_number;
    }

    /*************************************/


}

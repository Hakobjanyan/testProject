package work.Isui_dev_admin_add_delete_seache_update;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by user on 24.04.17.
 */
public class AddUser extends OpenDriver {


    @Test
    public void testUserAdd() throws InterruptedException {

        searchUser("");
        WebElement searchText = driver.findElement(By.id("j_idt112:usersTable_data"));

        // проверка, есть ли в реестре пользователь
        if (searchText.getText().contains("test@test.ru")){
            deleteUser();
        }

        addUser();

        searchUser("");
        Assert.assertEquals(true, driver.findElement(By.id("j_idt112:usersTable_data")).getText().contains("test@test.ru"));

    }


}

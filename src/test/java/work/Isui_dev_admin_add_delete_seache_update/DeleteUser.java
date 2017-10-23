package work.Isui_dev_admin_add_delete_seache_update;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Arsen on 27.04.2017.
 */
public class DeleteUser extends OpenDriver {
    @Test

    public void testUserDelete() {

        searchUser("");
        WebElement searchText = driver.findElement(By.id("j_idt112:usersTable_data"));

        // проверка, есть ли в реестре пользователь
        if (searchText.getText().contains("test@test.ru")){
            deleteUser();
        }

        Assert.assertEquals(false, driver.findElement(By.id("j_idt112:usersTable_data")).getText().contains("test@test.ru"));

    }
}

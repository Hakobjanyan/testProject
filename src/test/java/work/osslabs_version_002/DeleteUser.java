package work.osslabs_version_002;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Arsen on 27.04.2017.
 */
public class DeleteUser extends OpenDriver {
    @Test

    public void testUserDelete() {

        searchUser();
        WebElement searchText = driver.findElement(By.id("j_idt112:usersTable_data"));

        // проверка, есть ли в реестре пользователь
        if (searchText.getText().contains("Test")){
            deleteUser();
        }
        else System.out.println("В реестре нет, такого пользователя!!!");



        //addUser();
    }
}

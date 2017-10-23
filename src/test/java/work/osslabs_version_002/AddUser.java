package work.osslabs_version_002;

import org.junit.Test;
import org.openqa.selenium.By;


/**
 * Created by user on 24.04.17.
 */
public class AddUser extends OpenDriver {


    @Test
    public void testUserAdd() {

        searchUser();

        // проверка, есть ли в реестре пользователь
        if (driver.findElement(By.id("j_idt112:usersTable_data")).getText().contains("Test")){
            deleteUser();
        }
        addUser();
    }
}

package work.osslabs_version_001;

import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by Arsen on 27.04.2017.
 */
public class DeleteUser extends OpenDriver {

    @Test
    public void testUserDelete() {

        searchUser();

        // проверка, есть ли в реестре пользователь
        if (driver.findElement(By.id("j_idt112:usersTable_data")).getText().contains("Test")){
            deleteUser();
        }
        //addUser();
    }
}

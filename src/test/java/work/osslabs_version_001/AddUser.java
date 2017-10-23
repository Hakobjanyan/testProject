package work.osslabs_version_001;
import org.junit.*;
import org.openqa.selenium.*;



/**
 * Created by user on 24.04.17.
 */
public class AddUser extends OpenDriver{


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


package work.osslabs;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Arsen on 26.04.2017.
 */
public class test2 {

    static WebDriver driver;

    @Before
    public  void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }


    @After
    public void testDown(){

        driver.quit();
    }
}

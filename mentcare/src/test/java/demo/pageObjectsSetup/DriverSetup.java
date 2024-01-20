package demo.pageObjectsSetup;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DriverSetup {

    protected WebDriver driver;

    @Before
    public void SetUp() {
        org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.gecko.driver",
                    Paths.get("src/test/resources/geckodriver_win64/geckodriver.exe").toString());
        }
        else if(SystemUtils.IS_OS_MAC)
        {
            System.setProperty("webdriver.gecko.driver",
                    Paths.get("src/test/resources/geckodriver_mac64/geckodriver").toString());
        }
        else if(SystemUtils.IS_OS_LINUX)
        {
            System.setProperty("webdriver.gecko.driver",
                    Paths.get("src/test/resources/geckodriver_linux64/geckodriver").toString());
        }
        if (driver == null)
            driver = new FirefoxDriver(firefoxOptions);
    }

    @After
    public void tearDown()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }

}

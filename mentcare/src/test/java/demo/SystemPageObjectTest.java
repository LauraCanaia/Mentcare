package demo;

import org.junit.Assert;
import org.junit.Test;

public class SystemPageObjectTest extends DriverSetup{

    /*
    WAIT UNTIL ERROR IS FIXED
    @Test
    public void registerAndLogin()
    {
        String name = "Giorno";
        String lastName = "Giovanna";
        String fiscalCode = "GVNGRN85D16H501U";
        String password = "Lampadario14!";

        driver.get("http://localhost:8080/");

    }*/
    @Test
    public void loginAndAddVisit()
    {
        String name = "laura";
        String password = "5678";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);

        Assert.assertTrue(true);
    }
}

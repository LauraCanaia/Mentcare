package demo;

import org.junit.Assert;
import org.junit.Test;

public class SystemPageObjectTest extends DriverSetup{

    @Test
    public void registerAndLogin()
    {
        String name = "Giorno";
        String lastName = "Giovanna";
        String fiscalCode = "GVNGRN85D16H501U";
        String password = "Lampadario14!";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertNull(welcome);

        RegistrationPageTest registration = mentcare.clickRegistration(driver);
        mentcare = registration.userSignUp(name, lastName, fiscalCode, password, driver);
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/login"));

        welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
    }

    /*@Test
    public void loginAndAddVisit()
    {
        String name = "laura";
        String password = "5678";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);

        Assert.assertTrue(true);
    }*/
}

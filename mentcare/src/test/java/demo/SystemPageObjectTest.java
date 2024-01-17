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

    @Test
    public void loginAndAddVisit()
    {
        String name = "laura";
        String password = "5678";

        String date = "14/02/2024";
        String time = "14:50";
        String motivation = "Random test motivation";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
        Integer dimBefore = welcome.findTableDimension(driver);

        AddVisitPageTest newVisit = welcome.addVisit(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Aggiungi Visita"));

        welcome = newVisit.compileVisitForm(driver, date, time, motivation);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
        Integer dimAfter = welcome.findTableDimension(driver);
        Assert.assertTrue(dimBefore < dimAfter);
    }
    @Test
    public void loginAndLogout()
    {
        String name = "laura";
        String password = "5678";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));

        mentcare = welcome.logoutAction(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Login"));
    }
    @Test
    public void deleteFirstVisit()
    {
        String name = "laura";
        String password = "5678";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
        Integer dimBefore = welcome.findTableDimension(driver);

        welcome.deleteVisit();
        Integer dimAfter = welcome.findTableDimension(driver);
        Assert.assertTrue(dimAfter < dimBefore);
    }
}

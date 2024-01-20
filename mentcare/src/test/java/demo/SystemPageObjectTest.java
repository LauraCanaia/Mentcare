package demo;

import demo.pageObjectsSetup.*;
import org.junit.Assert;
import org.junit.Test;

public class SystemPageObjectTest extends DriverSetup {

    @Test
    public void registerAndLogin()
    {
        String username = "JoJo";
        String name = "Giorno";
        String lastName = "Giovanna";
        String fiscalCode = "GVNGRN85D16H501U";
        String password = "Lampadario14!";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(username, password, driver);
        Assert.assertNull(welcome);

        RegistrationPageTest registration = mentcare.clickRegistration(driver);
        mentcare = registration.userSignUp(username, name, lastName, fiscalCode, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Login"));

        welcome = mentcare.SubmitCredentials(username, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
    }
    @Test
    public void registrationWithInvalidFiscalCode()
    {
        String username = "Leo";
        String name = "Leone";
        String lastName = "Abbacchio";
        String invalidFiscalCode = "sagvdjshdvasd";
        String password = "JojoRegistration!";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(username, password, driver);
        Assert.assertNull(welcome);

        RegistrationPageTest registration = mentcare.clickRegistration(driver);
        mentcare = registration.userSignUp(username, name, lastName, invalidFiscalCode, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Registration - Mentcare"));
    }
    @Test
    public void testWithInvalidPassword()
    {
        String username = "JoJo";
        String name = "Giorno";
        String lastName = "Giovanna";
        String fiscalCode = "GVNGRN85D16H501U";
        String invalidPassword = "ciao";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(username, invalidPassword, driver);
        Assert.assertNull(welcome);

        RegistrationPageTest registration = mentcare.clickRegistration(driver);
        mentcare = registration.userSignUp(username, name, lastName, fiscalCode, invalidPassword, driver);
        Assert.assertTrue(driver.getTitle().equals("Registration - Mentcare"));
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
    public void loginRegisterLoginButtonScenario()
    {
        String name = "laura";
        String password = "5678";
        String newMotivation = "edited motivation";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Login"));

        RegistrationPageTest registration = mentcare.clickRegistration(driver);
        Assert.assertTrue(driver.getTitle().equals("Registration - Mentcare"));

        mentcare = registration.returnToLogin(driver);
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
    @Test
    public void modifyFirstVisitDate()
    {
        String name = "laura";
        String password = "5678";
        String newDate = "15/03/2024";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));

        String date = welcome.getFirstVisitDateToString();
        Assert.assertFalse(date.equals(newDate));

        EditVisitPageTest edit = welcome.editButton(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Modifica Visita"));

        edit.editDate(newDate);
        welcome = edit.submitEdited(driver);
        Assert.assertTrue(welcome.getFirstVisitDateToString().equals(newDate));
    }
    @Test
    public void modifyFirstVisitTime()
    {
        String name = "laura";
        String password = "5678";
        String newTime = "9:35";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));

        String time = welcome.getFirstVisitTimeToString();
        Assert.assertFalse(time.equals(newTime));

        EditVisitPageTest edit = welcome.editButton(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Modifica Visita"));

        edit.editTime(newTime);
        welcome = edit.submitEdited(driver);
        Assert.assertTrue(welcome.getFirstVisitTimeToString().equals(newTime));
    }
    @Test
    public void modifyFirstVisitMotivation()
    {
        String name = "laura";
        String password = "5678";
        String newMotivation = "edited motivation";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));

        String motivationBefore = welcome.getFirstVisitMotivationToString();
        Assert.assertFalse(motivationBefore.equals(newMotivation));

        EditVisitPageTest edit = welcome.editButton(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Modifica Visita"));

        edit.editMotivation(newMotivation);
        welcome = edit.submitEdited(driver);
        Assert.assertTrue(welcome.getFirstVisitMotivationToString().equals(newMotivation));
    }
    @Test
    public void addVisitAndReturnToWelcome()
    {
        String name = "laura";
        String password = "5678";
        String newMotivation = "edited motivation";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
        Integer tableBefore = welcome.findTableDimension(driver);

        AddVisitPageTest addVisit = welcome.addVisit(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Aggiungi Visita"));

        welcome = addVisit.goBackToWelcome(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));
        Assert.assertTrue(tableBefore == welcome.findTableDimension(driver));
    }
    @Test
    public void modifyVisitAndReturnToWelcome()
    {
        String name = "laura";
        String password = "5678";
        String newMotivation = "edited motivation";

        driver.get("http://localhost:8080/");

        MentcarePageTest mentcare = new MentcarePageTest(driver);
        WelcomePageTest welcome = mentcare.SubmitCredentials(name, password, driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Benvenuto"));

        String date = welcome.getFirstVisitDateToString();
        String time = welcome.getFirstVisitTimeToString();
        String motivation = welcome.getFirstVisitMotivationToString();

        EditVisitPageTest edit = welcome.editButton(driver);
        Assert.assertTrue(driver.getTitle().equals("Mentcare - Modifica Visita"));

        welcome = edit.goBackButton(driver);
        Assert.assertTrue(welcome.getFirstVisitDateToString().equals(date));
        Assert.assertTrue(welcome.getFirstVisitTimeToString().equals(time));
        Assert.assertTrue(welcome.getFirstVisitMotivationToString().equals(motivation));
    }

}

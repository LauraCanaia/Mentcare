package demo;

import demo.model.User;
import demo.model.Visit;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    // Test user creation with no parameters
    @Test
    public void userWithNoParameters(){
        User userTest = new User();
        Assert.assertEquals("", userTest.getUsername());
        Assert.assertNotNull(userTest.getVisits());
    }

    // test user creation with parameters
    @Test
    public void userWithParameters()
    {
        String username = "Laura";
        String name = "Laura";
        String lastName = "Canaia";
        String fiscalCode = "CNALRA99H42L157X";
        String password = "Lampadario540!";
        User userTest = new User(username, name, lastName, fiscalCode, password);
        Assert.assertEquals(username, userTest.getUsername());
        Assert.assertEquals(lastName, userTest.getLastname());
        Assert.assertEquals(fiscalCode, userTest.getFiscalCode());
        Assert.assertEquals(password, userTest.getPassword());
        Assert.assertNotNull(userTest.getVisits());
    }

    @Test
    public void toStringTest()
    {
        String username = "Laura";
        String name = "Laura";
        String lastName = "Canaia";
        String fiscalCode = "CNALRA99H42L157X";
        String password = "Lampadario540!";
        User userTest = new User(username, name, lastName, fiscalCode, password);
        String result = String.format(
                "User[username='%s', firstname='%s', lastname='%s', fiscalCode='%s' password='%s']"
        , username, name, lastName, fiscalCode, password);
        Assert.assertEquals(result, userTest.toString());
    }

    @Test
    public void setUsernameTest()
    {
        User userTest = new User();
        Assert.assertEquals("", userTest.getUsername());
        userTest.setUsername("randomName");
        Assert.assertEquals("randomName", userTest.getUsername());
    }

    @Test
    public void setLastNameTest()
    {
        User userTest = new User();
        Assert.assertEquals("", userTest.getLastname());
        userTest.setLastname("Mammucari");
        Assert.assertEquals("Mammucari", userTest.getLastname());
    }

    @Test
    public void setPasswordTest()
    {
        User userTest = new User();
        Assert.assertEquals("", userTest.getPassword());
        userTest.setPassword("randomPasswordValue123!");
        Assert.assertEquals("randomPasswordValue123!", userTest.getPassword());
    }

    @Test
    public void setFiscalCodeTest()
    {
        User userTest = new User();
        Assert.assertEquals("", userTest.getFiscalCode());
        userTest.setFiscalCode("ZNCGRM01R18H199E");
        Assert.assertEquals("ZNCGRM01R18H199E", userTest.getFiscalCode());
    }

    @Test
    public void addAndRemoveVisitTest()
    {
        User userTest = new User();
        Visit visit = new Visit("01/01/01", "15:30", "random motivation test");
        userTest.addVisit(visit);
        Assert.assertTrue(userTest.getVisits().contains(visit));
        userTest.removeVisit(visit);
        Assert.assertFalse(userTest.getVisits().contains(visit));
    }

}

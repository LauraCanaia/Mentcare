package demo;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    // Test user creation with no parameters
    @Test
    public void userWithNoParameters(){
        User userTest = new User();
        Assert.assertEquals("", userTest.getUsername());
    }

    // test user creation with parameters
    @Test
    public void userWithParameters()
    {
        String username = "Laura";
        String lastName = "Canaia";
        String fiscalCode = "CNALRA99H42L157X";
        String password = "Lampadario540!";
        User userTest = new User(username, lastName, fiscalCode, password);
        Assert.assertEquals(username, userTest.getUsername());
        Assert.assertEquals(lastName, userTest.getLastname());
        Assert.assertEquals(fiscalCode, userTest.getFiscalCode());
        Assert.assertEquals(password, userTest.getPassword());
    }

    @Test
    public void toStringTest()
    {
        String username = "Laura";
        String lastName = "Canaia";
        String fiscalCode = "CNALRA99H42L157X";
        String password = "Lampadario540!";
        User userTest = new User(username, lastName, fiscalCode, password);
        String result = String.format(
                "User[username='%s', lastname='%s', fiscalCode='%s' password='%s']",
                username, lastName, fiscalCode, password);
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
    public void setPasswordTest()
    {
        User userTest = new User();
        Assert.assertEquals("", userTest.getPassword());
        userTest.setPassword("randomPasswordValue123!");
        Assert.assertEquals("randomPasswordValue123!", userTest.getPassword());
    }

}

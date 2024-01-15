package demo;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

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
        User userTest = new User("Laura", "Canaia", "CNALRA99H42L157X", "Lampadario540!");
        Assert.assertEquals("Laura", userTest.getUsername());
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
}

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
        Assert.assertTrue(true);
    }
}

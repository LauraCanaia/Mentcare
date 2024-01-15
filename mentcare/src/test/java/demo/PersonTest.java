package demo;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void personCreation()
    {
        Person person = new Person("Stefano", "Modenese");
        Assert.assertEquals("Stefano", person.getFirstName());
        Assert.assertEquals("Modenese", person.getLastName());
        Assert.assertNull(person.getId());
    }

    @Test
    public void personCreationWithNoParameters()
    {
        Person person = new Person();
        Assert.assertNull(person.getFirstName());
        Assert.assertNull(person.getLastName());
        Assert.assertNull(person.getId());
    }

    @Test
    public void toStringTest()
    {
        String name = "Stefano";
        String lastName = "Modenese";
        Person person = new Person(name, lastName);
        String result = String.format("Customer[id=%d, firstName='%s', lastName='%s']",
                person.getId(), name, lastName);
        Assert.assertEquals(result, person.toString());
    }
}

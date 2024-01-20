package demo;

import demo.utils.DateValidator;
import org.junit.Assert;
import org.junit.Test;

public class DateValidatorTest {

    @Test
    public void validDateTest()
    {
        Boolean test = DateValidator.isValidDateFormat("12/10/2023");
        Assert.assertTrue(test);
    }

    @Test
    public void invalidDateTest()
    {
        Boolean test = DateValidator.isValidDateFormat("134/13/20");
        Assert.assertFalse(test);
    }
}

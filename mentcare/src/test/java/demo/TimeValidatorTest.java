package demo;

import demo.utils.TimeValidator;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;

public class TimeValidatorTest {

    @Test
    public void rightTime()
    {
        Boolean test = TimeValidator.isValidTimeFormat("12:15");
        Assert.assertTrue(test);
    }

    @Test
    public void wrongTime()
    {
        Boolean test = TimeValidator.isValidTimeFormat("123:40");
        Assert.assertFalse(test);
    }

}

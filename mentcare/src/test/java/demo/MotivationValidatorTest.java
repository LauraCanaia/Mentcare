package demo;

import demo.utils.MotivationValidator;
import org.apache.xpath.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

public class MotivationValidatorTest {

    @Test
    public void motivationWithRightLength()
    {
        Boolean test = MotivationValidator.isWithinMaxLength("abcdefg", 15);
        Assert.assertTrue(test);
    }

    @Test
    public void motivationWithoutRightLength()
    {
        Boolean test = MotivationValidator.isWithinMaxLength("abcdefghijklmn", 10);
        Assert.assertFalse(test);
    }
}

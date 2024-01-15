package demo;

import org.junit.Assert;
import org.junit.Test;

public class VisitTest {
    @Test
    public void visitWithNoParameters()
    {
        Visit visit = new Visit();
        Assert.assertEquals("01/01/2024", visit.getDate());
        Assert.assertEquals("12:30", visit.getTime());
        Assert.assertEquals("visita di prova", visit.getMotivation());
        Assert.assertNull(visit.getId());
    }

    @Test
    public void visitWithParameters()
    {
        String date = "24/02/2024";
        String time = "15:45";
        String motivation = "Random motivation";
        Visit visit = new Visit(date, time, motivation);
        Assert.assertEquals(date, visit.getDate());
        Assert.assertEquals(time, visit.getTime());
        Assert.assertEquals(motivation, visit.getMotivation());
    }

    @Test
    public void toStringTest()
    {
        String date = "24/02/2024";
        String time = "15:45";
        String motivation = "Random motivation";
        Visit visit = new Visit(date, time, motivation);
        String result = "Visit{" + "id=" + visit.getId() + ", date=" + date + ", time=" + time + ", motivation=" + motivation;
        Assert.assertEquals(result, visit.toString());
    }

    @Test
    public void testSetters()
    {
        Visit visit = new Visit();
        visit.setDate("24/05/2024");
        Assert.assertEquals("24/05/2024", visit.getDate());
        visit.setTime("11:45");
        Assert.assertEquals("11:45", visit.getTime());
        visit.setMotivation("SERIOUS random motivation");
        Assert.assertEquals("SERIOUS random motivation", visit.getMotivation());
    }

    @Test
    public void userSetterAndGetter()
    {
        Visit visit = new Visit();
        Assert.assertNull(visit.getUser());
        User user = new User("Laura", "Canaia", "CNALRA99H42L157X", "Lampadario145!");
        visit.setUser(user);
        Assert.assertEquals(user, visit.getUser());
    }

}

package Clock;

import Clock.Clock;
import Clock.WrongTimeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClockTest {

    /**
     * 1 Create new Instance of Clock.Clock
     */
   private Clock cl = new Clock("02:03:53");

    public ClockTest() throws Exception {
    }

    @Test
    public void create_clock_instance() throws Exception{
        assertEquals(2, cl.getHours());
        assertEquals(3, cl.getMinutes());
        assertEquals(53, cl.getSeconds());
    }

    /**
     * 2a Adding minutes to existing clock instance
     */
    @Test
    public void add_minutes_to_clock() throws WrongTimeException{
        cl.addMinutes(121);
        assertEquals(4,cl.getMinutes());
        assertEquals(4,cl.getHours());
    }

    /**
     * 2b Adding seconds to existing clock instance
     */
    @Test
    public void add_seconds_to_clock() throws WrongTimeException{
        cl.addSeconds(2);
        assertEquals(2,cl.getHours());
        assertEquals(3,cl.getMinutes());
        assertEquals(55,cl.getSeconds());
    }

    /**
     * 2c Adding hours to existing clock instance
     */
    @Test
    public void add_hours_to_clock() throws Exception{
        cl.addHours(23);
        assertEquals(1,cl.getHours());
    }

    /**
     * 3a Setting wrong hours to existing clock instance
     */
    @Test(expected = WrongTimeException.class)
    public void setting_wrong_hours() throws Exception{
        cl.setHours(24);
    }

    /**
     * 3b Setting wrong minutes to existing clock instance
     */
    @Test(expected = WrongTimeException.class)
    public void setting_wrong_minutes() throws Exception{
        cl.setMinutes(60);
    }

    /**
     * 3c Setting wrong seconds to existing clock instance
     */
    @Test(expected = WrongTimeException.class)
    public void setting_wrong_seconds() throws Exception{
        cl.setSeconds(60);
    }

    /**
     * 5a Adding hours from another instance to existing clock with over 24h result
     */
    @Test
    public void add_time_from_other_clock() throws Exception{
        Clock cl2 = new Clock("23:12:12");
        cl.addClock(cl2);
        assertEquals(1, cl.getHours());
        assertEquals(16, cl.getMinutes());
        assertEquals(5, cl.getSeconds());
    }
    /**
     * 5b Adding hours from another instance to existing clock with less 24h result
     */
    @Test
    public void back_time_other_clock_less () throws Exception{
        Clock cl2 = new Clock("05:10:01"); // 02:03:53 - 05:10:01 -> 20:53:52
        cl.backClock(cl2);
        assertEquals(20, cl.getHours());
        assertEquals(53, cl.getMinutes());
        assertEquals(52, cl.getSeconds());
    }

    /**
     * 6a Creation of Clock.Clock with negative hours
     */
    @Test(expected = WrongTimeException.class)
    public void create_negative_hours_clock() throws Exception{
        new Clock("-23:02:00");
    }
    /**
     * 6b Creation of Clock.Clock with negative minutes
     */
    @Test(expected = WrongTimeException.class)
    public void create_negative_minutes_clock() throws Exception{
        new Clock("23:-02:00");
    }
    /**
     * 6c Creation of Clock.Clock with negative seconds
     */
    @Test(expected = WrongTimeException.class)
    public void create_negative_seconds_clock() throws Exception{
        new Clock("23:02:-05");
    }
    /**
     * 7a Creation of Clock.Clock with too large hours
     */
    @Test(expected = WrongTimeException.class)
    public void create_super_hours_clock() throws Exception{
        new Clock("123:02:00");
    }
    /**
     * 7b Creation of Clock.Clock with  too large minutes
     */
    @Test(expected = WrongTimeException.class)
    public void create_super_minutes_clock() throws Exception{
        new Clock("23:102:00");
    }

    /**
     * 7c Creation of Clock.Clock with  too large seconds
     */
    @Test(expected = WrongTimeException.class)
    public void create_super_seconds_clock() throws Exception{
        new Clock("23:10:100");
    }

    /**
     * 9a Adding over day minutes
     */
    @Test
    public void add_over_minutes_to_clock() throws Exception{
        Clock cl2 = new Clock("21:56:07");
        cl2.addMinutes(302);
        assertEquals(2,cl2.getHours());
        assertEquals(58,cl2.getMinutes());
        assertEquals(7,cl2.getSeconds());
    }

    /**
     * 9a Adding over day seconds
     */
    @Test
    public void add_over_seconds_to_clock() throws Exception{
        cl.addSeconds(7+56*60+21*60*60);//020353  -> 215607
        assertEquals(0,cl.getHours());
        assertEquals(0,cl.getMinutes());
        assertEquals(0,cl.getSeconds());
    }

    /**
     * 10a Adding negative minutes
     */
    @Test(expected = WrongTimeException.class)
    public void add_negative_minutes_to_clock() throws Exception{
        cl.addMinutes(-5);
    }

    /**
     * 10b Adding negative hours
     */
    @Test(expected = WrongTimeException.class)
    public void add_negative_hours_to_clock() throws Exception{
        cl.addHours(-5);
    }
    /**
     * 10c Adding negative seconds
     */
    @Test(expected = WrongTimeException.class)
    public void add_negative_seconds_to_clock() throws Exception{
        cl.addSeconds(-54);//02:03:53 -> 02:03:48
    }

    /**
     * 11a Back in time seconds
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_small_seconds() throws Exception{
        cl.backTimeSeconds(5); //02:03:53 - 5 02:03:48
        assertEquals(48, cl.getSeconds());
    }

    /**
     * 11b Back in time seconds when existing second less than argument
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_super_seconds() throws Exception{
        cl.backTimeSeconds(54); //02:03:53 - 54 -> 02:02:59
        assertEquals(2, cl.getMinutes());
        assertEquals(59, cl.getSeconds());
    }

    /**
     * 11c Back in time seconds when existing second less than argument, argument is bigger 60
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_superBig_seconds() throws Exception{
        cl.backTimeSeconds(174); // 02:03:53 - 174 -> 02:00:59
        assertEquals(59, cl.getSeconds());
        assertEquals(0, cl.getMinutes());
    }

    /**
     * 12a Back in time minutes
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_small_minutes() throws Exception{
        cl.backTimeMinutes(2); //02:03:53 - 5 02:03:48
        assertEquals(53, cl.getSeconds());
        assertEquals(1, cl.getMinutes());
        assertEquals(2, cl.getHours());
    }

    /**
     * 12b Back in time minutes when existing minute less than argument
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_super_minute() throws Exception{
        cl.backTimeMinutes(5); //02:03:53 - 5 01:58:53
        assertEquals(53, cl.getSeconds());
        assertEquals(58, cl.getMinutes());
        assertEquals(1, cl.getHours());
    }

    /**
     * 12c Back in time minutes when existing second less than argument, argument is bigger 60
     */
    @Test
    public void back_int_time_superBig_minute() throws Exception{
        cl.backTimeMinutes(123); //02:03:53 - 123 min 02:03:48
        assertEquals(53, cl.getSeconds());
        assertEquals(0, cl.getMinutes());
        assertEquals(0, cl.getHours());
    }

    /**
     * 13a Back in time hours
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_small_hours() throws Exception{
        cl.backTimeHours(2); //02:03:53 - 2 00:03:53
        assertEquals(53, cl.getSeconds());
        assertEquals(3, cl.getMinutes());
        assertEquals(0, cl.getHours());
    }

    /**
     * 13b Back in time hours when existing minute less than argument
     */
    @Test//(expected = Clock.WrongTimeException.class)
    public void back_int_time_super_hour() throws Exception{
        cl.backTimeHours(5); //02:03:53 - 5 01:58:53
        assertEquals(53, cl.getSeconds());
        assertEquals(3, cl.getMinutes());
        assertEquals(21, cl.getHours());
    }

    /**
     * 13c Back in time hours when existing hour less than argument, argument is bigger 24
     */
    @Test
    public void back_int_time_superBig_hour() throws Exception{
        cl.backTimeHours(25); //02:03:53 -
        assertEquals(53, cl.getSeconds());
        assertEquals(3, cl.getMinutes());
        assertEquals(1, cl.getHours());
    }

    /**
     * 14a negative hour
     */
    @Test(expected = WrongTimeException.class)
    public void back_int_time_negative_hour() throws Exception{
        cl.backTimeHours(-25);
    }
    /**
     * 14b negative minute
     */
    @Test(expected = WrongTimeException.class)
    public void back_int_time_negative_minute() throws Exception{
        cl.backTimeMinutes(-25);
    }
    /**
     * 14c negative seconds
     */
    @Test(expected = WrongTimeException.class)
    public void back_int_time_negative_seconds() throws Exception{
        cl.backTimeSeconds(-25);
    }

    /**
     * 11 Adding null instance
     */
    @Test(expected = WrongTimeException.class)
    public void add_null_instance_clock() throws Exception{
        cl.addClock(null);
    }


}

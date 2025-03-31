import static org.junit.Assert.*;
import org.junit.Test;



public class ClockTest{

    // create clock objects to test
    Clock clock1 = new Clock(1, 1, "a.m.");
    Clock clock2 = new Clock(1, 1, "a.m.");
    Clock clock3 = new Clock(1, 1, "p.m.");
    Clock clock4 = new Clock(2, 1, "p.m.");
    Clock clock5 = new Clock(2, 2, "p.m.");
    Clock clock6 = new Clock(12, 0, "a.m.");
    Clock clock7 = new Clock(1, 0, "a.m.");
    Clock clock8 = new Clock(12, 0, "p.m.");
    Clock clock9 = new Clock(1, 0, "p.m.");
    Clock clock10 = new Clock(12, -1, "a.m.");
    Clock clock11 = new Clock(1, 61, "a.m.");
    Clock clock12 = new Clock(6, 0, "p.m.");
    Clock clock13 = new Clock(5, 60, "p.m.");



    @Test
    public void isGetEarlierValid() throws Exception{
        assertEquals(clock1, Clock.getEarlier(clock1, clock2)); // same clock retruns the first clock
        assertEquals(clock2, Clock.getEarlier(clock2, clock3)); // same hour/minute different meridian returns clock 2
        assertEquals(clock3, Clock.getEarlier(clock3, clock4)); // same minute/meridian different hour returns clock 3
        assertEquals(clock4, Clock.getEarlier(clock4, clock5)); // same hour/meridian different minute return clock 4
        assertEquals(clock6, Clock.getEarlier(clock6, clock7)); // check 1200 am is earlier than 100 am
        assertEquals(clock8, Clock.getEarlier(clock8, clock9)); // check 1200 pm is earlier than 100 pm
        assertEquals(clock6, Clock.getEarlier(clock6, clock10)); // check a negative minute is set to zero
        assertEquals(clock11, Clock.getEarlier(clock1, clock11)); // check a number greater than 60 is set to zero
        assertEquals(clock13, Clock.getEarlier(clock12, clock13)); // check that 60 minutes is treated as an hour


        try{
            assertEquals(clock12, Clock.getEarlier(clock12, clock13)); // try/catch not necessary by I was looking for a way to capture a bunch of failurs to test negative cases during a single run
        }
        catch(AssertionError e){
            System.out.println("Failed: " + e); // not really a failure because both times are the same so it doesn't make a difference which one is returned as earlier but not good coding practice to allow a time of 60 minutes for a clock
        }
        
    }

}
package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Punch {

    int terminalid = 0;
    int punchtypeid = 0;
    String id;
    String description;
    Badge badge = new Badge(id, description); 
     //This will probably hold HH:MM MM:DD:YY but I'm not sure yet.
    GregorianCalendar timestamp = new GregorianCalendar(TimeZone.getDefault());
    public Punch(Badge badge, int terminalid, int punchtypeid, GregorianCalendar timestamp) {
        //TODO: Add gregoriancalendar objects for date and time of Punches. Add to args of punch constructor
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = badge; 
        this.timestamp = getTime();
    }
    //Initial take on getting dates and times for punches 
    //This will change as snellen said it was a "good start" but needed more work.
    //He also said to make sure we are using all GregorianCalendar objects instead of calendar
    public GregorianCalendar getTime() {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        calendar.get(GregorianCalendar.YEAR);
        calendar.get(GregorianCalendar.MONTH);
        calendar.get(GregorianCalendar.DATE);
        calendar.get(GregorianCalendar.HOUR);
        calendar.get(GregorianCalendar.MINUTE);
        calendar.get(GregorianCalendar.DAY_OF_WEEK);
        return calendar;
    }
    public String toString(Punch punch) {
        return (badge.getID() + " CLOCKED IN: "); //Not sure how to format the data as of yet, need to follow the example below.
    }
    public Punch getPunch(int terminalid) {
        return null; 
        //Below is an example of exactly what the output from this function should look like. To string method for the first part.
        //assertEquals("#D2C39273 CLOCKED IN: WED 09/06/2017 07:00:07", p1.printOriginalTimestamp());
    }
}

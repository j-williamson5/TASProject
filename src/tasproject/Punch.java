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
    int year, month, day, hour, minute, second, dayOfWeek;
    String id;
    String description;
    Badge badge = new Badge(id, description); 
    GregorianCalendar timestamp = new GregorianCalendar(TimeZone.getDefault());
    public Punch(Badge badge, int terminalid, int punchtypeid, GregorianCalendar timestamp) {
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = badge; 
        this.timestamp = getTime();
    }
    
    public GregorianCalendar getTime() {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DATE);
        hour = calendar.get(GregorianCalendar.HOUR);
        minute = calendar.get(GregorianCalendar.MINUTE);
        dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        calendar.set(year, month, day, hour, minute, second);
        //Can possibly replace month above as a string and use getDisplayName(); 
        //calendar.set(DAY_OF_WEEK, dayOfWeek); currently not working, unsure how to implement as of yet. 
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

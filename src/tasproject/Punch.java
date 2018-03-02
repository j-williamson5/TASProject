package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Punch {

    int terminalid = 0;
    int punchtypeid = 0;
    String id;
    String description;
    Badge badge = new Badge(id, description); 
     //This will probably hold HH:MM MM:DD:YY but I'm not sure yet.
    Calendar timestamp = new GregorianCalendar(TimeZone.getDefault());
    public Punch(Badge badge, int terminalid, int punchtypeid, Calendar timestamp) {
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
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DATE);
        calendar.get(Calendar.HOUR);
        calendar.get(Calendar.MINUTE);
        return calendar;
    }
    public Punch getPunch(String id) {
        return null; //Assuming we will call punches by badge id here. Returning null until completed and ready for testing
        //I think we may have to return these types of calls as a string to output. Format HH:MM MM:DD:YY
        //Still need to check tests once added to the project by Ian
    }
}

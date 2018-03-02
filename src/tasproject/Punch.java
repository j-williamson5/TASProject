package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */

import java.util.GregorianCalendar;

public class Punch {

    int terminalid = 0;
    int punchtypeid;
    String id;
    String description;
    
    Badge badge = new Badge(id, description); 
    // ^ This is the minimal information needed to represent the punch.
    // The other properties of this object (such as the ID and the adjusted timestamp) should be initialized to zero or to null, according to their type.
    public Punch(Badge badge, int terminalid, int punchtypeid) {
        //TODO: Add gregoriancalendar objects for date and time of Punches. Add to args of punch constructor
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = badge; 
    }
}

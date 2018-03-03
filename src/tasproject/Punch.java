package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Punch {

    //Instance Fields (I just made the comment) - Josh
    int terminalid = 0;
    int punchtypeid = 0;
    int year, month, day, hour, minute, second, dayOfWeek;
    String id;
    String description;
    Badge badge = new Badge(id, description); 
    GregorianCalendar timestamp = new GregorianCalendar(TimeZone.getDefault());
    
    //Empty constructor for TASDatabase - Josh
    public Punch(){};
    
    //Constructor (I just made the comment) - Josh
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
    
    /*
    public String toString(Punch punch) {
        return (badge.getID() + " CLOCKED IN: "); //Not sure how to format the data as of yet, need to follow the example below.
    }
    */
    
    //Placeholder for feature 3
    public String printOriginalTimestamp(){
        return null;//Change null after feature 3
    }

    //Setters and Getters - Josh
    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public void setPunchtypeid(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }
    
    
    /*What is this??? -Josh
    public Punch getPunch(int terminalid) {
        return null; 
        //Below is an example of exactly what the output from this function should look like. To string method for the first part.
        //assertEquals("#D2C39273 CLOCKED IN: WED 09/06/2017 07:00:07", p1.printOriginalTimestamp());
    }
    */
}

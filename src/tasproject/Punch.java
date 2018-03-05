package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Punch {

    //Instance Fields (I just made the comment) - Josh
    int terminalid = 0;
    int punchtypeid = 0;
    int year, month, day, hour, minute, second, dayOfWeek = 0;
    Badge badge;
    String badgeId;
    String badgeDescription;
    GregorianCalendar originalTimeStamp = new GregorianCalendar(TimeZone.getDefault());
    String eventData = null;
    
    //Empty constructor for TASDatabase - Josh
    public Punch(){};
    
    //Constructors
    public Punch(String badgeId, int terminalid, int punchtypeid){
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = new Badge(badgeId,"");
        this.badgeId = badgeId;
        this.badgeDescription = badge.getDescription();
        this.originalTimeStamp = defaultTime();
        System.out.println("OTHER CONSTRUCTOR");
    }
    
    public Punch(Badge badge, int terminalid, int punchtypeid) {
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = badge;
        this.badgeId = badge.getID();
        this.badgeDescription = badge.getDescription();
        this.originalTimeStamp = defaultTime();
    }
    
    public GregorianCalendar defaultTime() {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        year = calendar.get(GregorianCalendar.YEAR);
        month = calendar.get(GregorianCalendar.MONTH);
        day = calendar.get(GregorianCalendar.DATE);
        hour = calendar.get(GregorianCalendar.HOUR);
        minute = calendar.get(GregorianCalendar.MINUTE);
        dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        calendar.set(year, month, day, hour, minute, second);
        return calendar;
    }

    //This is how we should print the Punches
    public String printOriginalTimestamp(){
 
        SimpleDateFormat date = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss");
        String stringDate = date.format(originalTimeStamp.getTime());
        
        //I wanted to use the table event type in TASDatabase but in there the description doesn't include the ED in CLOCKED or TIMED so this seemed easier -Josh
        String typeOfPunch;
        switch(punchtypeid){
                case 0:
                    typeOfPunch = "CLOCKED OUT";
                    break;
                case 1:
                    typeOfPunch = "CLOCKED IN";
                    break;
                case 2:
                    typeOfPunch = "TIMED OUT";
                    break;
                default:
                    typeOfPunch = "";
                    break;
        }
                    
        String result = "#" + badge.getID() + " " + typeOfPunch + ": " + stringDate.toUpperCase();
        return result;
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

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String description) {
        this.badgeDescription = description;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    public String getBadgeId(){
        System.out.println("Badge's ID is: " + badge.getID());
        System.out.println("My ID is: " + badgeId);
        return badgeId;
    }

    public GregorianCalendar getOriginalTimestamp() {
        return originalTimeStamp;
    }

    //This one takes values you give it and sets the timestamp -Josh
    public void setOriginalTimeStamp(int year, int month, int day, int hour, int minute, int second) {
        this.originalTimeStamp.set(year, month, day, hour, minute, second);
    }
    
    //This one sets the timestamp to the values the class already has -Josh
    public void setOriginalTimeStamp(){
        this.originalTimeStamp.set(year,month,day,hour,minute,second);
    }
    
    public void setOriginalTimeStamp(GregorianCalendar time){
        this.originalTimeStamp = time;
    }
    
    public String getEventData(){
        return this.eventData;
    }
    
    public void setEventData(String eventData){
        this.eventData = eventData;
    }
}

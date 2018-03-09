package tasproject;

/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Punch {

    //Instance Fields
    int terminalid = 0;
    int punchtypeid = 0;
    Badge badge;
    String badgeid;
    String badgeDescription;
    GregorianCalendar originalTimeStamp = new GregorianCalendar(TimeZone.getDefault());
    GregorianCalendar adjustedTimeStamp = new GregorianCalendar(TimeZone.getDefault());
    String eventData = null;
    
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    
    //Empty constructor for TASDatabase - Josh
    public Punch(){};
    
    //Constructors
     public Punch(Badge badge, int terminalid, int punchtypeid) {
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = badge;
        this.badgeid = badge.getID();
        this.badgeDescription = badge.getDescription();
    }
     
    public Punch(String badgeId, int terminalid, int punchtypeid){
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        this.badge = new Badge(badgeId,"");
        this.badgeid = badgeId;
        this.badgeDescription = badge.getDescription();
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
    
    public int millisToHours(long ms){
        if (ms > DAY) {
            ms %= DAY;
          }
        if (ms > HOUR) {
            ms %= HOUR;
          }
        return (int) ms;
    }
    
    public int millisToMinutes(long ms){
        if (ms > DAY) {
            ms %= DAY;
          }
        if (ms > HOUR) {
            ms %= HOUR;
          }
        if (ms > MINUTE) {
            ms %= MINUTE;
          }
        return (int) ms;
    }
    
    //Must use constants for all Parameters!!! Read through the notes from class so I suppose this will probably change later but I'm going to leave it for now.
    public void adjust(Shift s) {
        Time startTime = s.getStartTime();
        Time stopTime = s.getStopTime();
        Time lunchStart = s.getLunchStart();
        Time lunchStop = s.getLunchStop();
        int startTimeHours = millisToHours(startTime.getTime());
        int  startTimeMinutes = millisToMinutes(startTime.getTime());
        int stopTimeHours = millisToHours(stopTime.getTime());
        int stopTimeMinutes = millisToMinutes(stopTime.getTime());
        int lunchStartHours = millisToHours(lunchStart.getTime());
        int lunchStartMinutes = millisToMinutes(lunchStart.getTime());
        int lunchStopHours = millisToHours(lunchStop.getTime());
        int lunchStopMinutes = millisToMinutes(lunchStop.getTime());
        int interval = s.getInterval();
        int dock = s.getDock();
        int gracePeriod = s.getGracePeriod();
        
        //getting the day of the week from the punch timestamp
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(originalTimeStamp.getTimeInMillis());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int min =  cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        
        if(day == 1 || day == 7){
            
        }
        else{
            //If the punch is a clock out punch
            if(punchtypeid == 0){
                
            }
            //If the punch is a clock in punch
            else if(punchtypeid == 1){
                
                //If the punch occured after the shift start
                if((millisToMinutes(startTime.getTime() - originalTimeStamp.getTimeInMillis()) < 0)){
                     //If the punch is within the grace period
                    if(Math.abs(millisToMinutes(startTime.getTime() - originalTimeStamp.getTimeInMillis())) < gracePeriod){//If the punch occured after the shift started and the punch is within the grace period
                        adjustedTimeStamp.set(year,month,day, startTimeHours,startTimeMinutes);//Moving the adjusted time stamp back to the right hour and minute of the start of the shift.
                    }
                    //If the punch is after the grace period but before the dock
                    else if(Math.abs(millisToMinutes(startTime.getTime() - originalTimeStamp.getTimeInMillis())) < dock){
                        adjustedTimeStamp.set(year,month,day,startTimeHours, startTimeMinutes + dock);
                    }
                }
                else{
                    if(millisToMinutes(startTime.getTime() - originalTimeStamp.getTimeInMillis()) <  interval && millisToMinutes(startTime.getTime() - originalTimeStamp.getTimeInMillis()) >= 0){//We want it before the interval but if it's less than 0 than that means that the punch occured after the shift started
                        adjustedTimeStamp.set(year,month,day,startTimeHours, startTimeMinutes);//Moving the adjusted time stamp up to the right hour and minute of the start of the shift.
                    }
                }
            }
        }
        
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
    
    public void setBadgeId(String badgeId){
        this.badgeid = badgeId;
    }
    public String getBadgeid(){
        return badgeid;
    }

    public GregorianCalendar getOriginaltimestamp() {
        return originalTimeStamp;
    }

    //This one takes values you give it and sets the timestamp -Josh
    public void setOriginalTimeStamp(int year, int month, int day, int hour, int minute, int second) {
        this.originalTimeStamp.set(year, month, day, hour, minute, second);
    }
    
    //This one sets the timestamp to the values the class already has -Josh
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

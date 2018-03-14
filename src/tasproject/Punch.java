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
    String adjustInfo = "None";
    
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    private static final int YEAR = 365 * DAY;
    
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
    
    public String printAdjustedTimestamp () {
 
        SimpleDateFormat date = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss");
        String stringDate = date.format(adjustedTimeStamp.getTime());
        
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
        
        
        //Compare adjustedTimeStamp and originalTimeStamp and set the type here
                    
        String result = "#" + badge.getID() + " " + typeOfPunch + ": " + stringDate.toUpperCase() + " (" + adjustInfo + ")";
        return result;
        
    }
    
    public int millisToHours(long ms){
        
        ms = Math.abs(ms);
        
        if(ms > YEAR){
            ms %= YEAR;
        }
        
        if (ms > DAY) {
            ms %= DAY;
          }
        if (ms > HOUR) {
            ms %= HOUR;
          }
        return (int) ms;
    }
    
    public int millisToMinutes(long ms){
        
        ms = Math.abs(ms);
        
         if(ms > YEAR){
            ms %= YEAR;
        }
         
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
        
        int interval = s.getInterval();
        int dock = s.getDock();
        int gracePeriod = s.getGracePeriod();

        //getting the day of the week from the punch timestamp
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(originalTimeStamp.getTimeInMillis());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int min =  cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int sec = cal.get(Calendar.SECOND);
        
        
        //Make the shift times into Gregorian Calendar Objects
        GregorianCalendar startTime = new GregorianCalendar();
        startTime.setTimeInMillis(s.getStartTime().getTime());
        startTime.set(year,month,day);
        GregorianCalendar stopTime = new GregorianCalendar();
        stopTime.setTimeInMillis(s.getStopTime().getTime());
        stopTime.set(year,month,day);
        GregorianCalendar lunchStart = new GregorianCalendar();
        lunchStart.setTimeInMillis(s.getLunchStart().getTime());
        lunchStart.set(year,month,day);
        GregorianCalendar lunchStop = new GregorianCalendar();
        lunchStop.setTimeInMillis(s.getLunchStop().getTime());
        lunchStop.set(year,month,day);
        
        //Compute the different "checkpoints" for times in our shift
        GregorianCalendar startInterval = new GregorianCalendar();
        startInterval.setTimeInMillis(startTime.getTimeInMillis());
        startInterval.add(Calendar.MINUTE,-(interval));
        
        GregorianCalendar startGrace = new GregorianCalendar();
        startGrace.setTimeInMillis(startTime.getTimeInMillis());
        startGrace.add(Calendar.MINUTE,gracePeriod);
        
        GregorianCalendar startDock = new GregorianCalendar();
        startDock.setTimeInMillis(startTime.getTimeInMillis());
        startDock.add(Calendar.MINUTE,dock);
        
        GregorianCalendar stopInterval = new GregorianCalendar();
        stopInterval.setTimeInMillis(stopTime.getTimeInMillis());
        stopInterval.add(Calendar.MINUTE,interval);
        
        GregorianCalendar stopGrace = new GregorianCalendar();
        stopGrace.setTimeInMillis(stopTime.getTimeInMillis());
        stopGrace.add(Calendar.MINUTE,-(gracePeriod));
        
        GregorianCalendar stopDock = new GregorianCalendar();
        stopDock.setTimeInMillis(stopTime.getTimeInMillis());
        stopDock.add(Calendar.MINUTE,-(dock));
        
        //Set the adjustedTimeStamp because it never gets set when the originalTimeStamp does and doing so causes errors 
        adjustedTimeStamp.setTimeInMillis(originalTimeStamp.getTimeInMillis());
        
        //If the punch is a weekend punch
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
            
            if(min % interval != 0){
                
                adjustedTimeStamp.setTimeInMillis(originalTimeStamp.getTimeInMillis());
                
                if(min % interval < interval/2){
                    adjustedTimeStamp.add(Calendar.MINUTE,-(min % interval));
                    adjustedTimeStamp.add(Calendar.SECOND, -sec);
                    adjustInfo = "Interval Round";
                }
                else{
                    adjustedTimeStamp.add(Calendar.MINUTE, (interval - min % interval ));
                    adjustedTimeStamp.add(Calendar.SECOND, -sec);
                    adjustInfo = "Interval Round";
                }
            }
        }
        //If the punch is a weekday punch
        else{
            //If the punch is a clock out punch
            if(punchtypeid == 0){
                //If the punch occured before the shift stop
                if(originalTimeStamp.before(stopTime)){
                    //If the punch within the stop grace period
                    if(originalTimeStamp.after(stopGrace) || originalTimeStamp.equals(stopGrace)){
                        adjustedTimeStamp.setTimeInMillis(stopTime.getTimeInMillis());
                        adjustInfo = "Shift Stop";
                    }
                    //If the punch after the stop dock(No need to check for within grace because that already occured
                    else if(originalTimeStamp.after(stopDock) || originalTimeStamp.equals(stopDock)){
                        adjustedTimeStamp.setTimeInMillis(stopDock.getTimeInMillis());
                        adjustInfo = "Shift Dock";
                    }
                    //If the punch during the lunch period
                    else if(originalTimeStamp.before(lunchStop) && originalTimeStamp.after(lunchStart)){
                        adjustedTimeStamp.setTimeInMillis(lunchStart.getTimeInMillis());
                        adjustInfo = "Lunch Start";
                    }
                    //If the punch is before the shift end but no where else
                    else{
                        if(min % interval != 0){
                
                            adjustedTimeStamp.setTimeInMillis(originalTimeStamp.getTimeInMillis());

                            if(min % interval < interval/2){
                                adjustedTimeStamp.add(Calendar.MINUTE,-(min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                            else{
                                adjustedTimeStamp.add(Calendar.MINUTE, (interval - min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                        }
                    }
                }
                //if the punch is after the shift stop
                else{
                    //If the punch is after the shift stop but before the stop interval
                    if(originalTimeStamp.before(stopInterval) || originalTimeStamp.equals(stopInterval)){
                        adjustedTimeStamp.setTimeInMillis(stopTime.getTimeInMillis());
                        adjustInfo = "Shift Stop";
                    }
                    
                    //If the punch follows no other rules
                    else{
                        if(min % interval != 0){
                

                            if(min % interval < interval/2){
                                adjustedTimeStamp.add(Calendar.MINUTE,-(min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                            else{
                                adjustedTimeStamp.add(Calendar.MINUTE, (interval - min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                        }
                    }
                }
            }
            //If the punch is a clock in punch
            else if(punchtypeid == 1){
                
                //If the punch occured after the shift start
                if(originalTimeStamp.after(startTime)){
                     //If the punch is within the grace period
                    if(originalTimeStamp.before(startGrace) || originalTimeStamp.equals(startGrace)){//If the punch occured after the shift started and the punch is within the grace period
                        adjustedTimeStamp.setTimeInMillis(startTime.getTimeInMillis());//Moving the adjusted time stamp back to the right hour and minute of the start of the shift.
                        adjustInfo = "Shift Start";
                    }
                    //If the punch is after the grace period but before the dock
                    else if(originalTimeStamp.before(startDock) || originalTimeStamp.equals(startDock)){
                        adjustedTimeStamp.setTimeInMillis(startDock.getTimeInMillis());
                        adjustInfo = "Shift Dock";
                    }
                    //If the punch is during the specified lunch time it needs to be moved to the end of lunch
                    else if(originalTimeStamp.after(lunchStart) && originalTimeStamp.before(lunchStop)){
                        adjustedTimeStamp.setTimeInMillis(lunchStop.getTimeInMillis());
                        adjustInfo = "Lunch Stop";
                    }
                    //If the punch is after the shift start but not in anywhere else
                    else{
                            if(min % interval != 0){

                                if(min % interval < interval/2){
                                    adjustedTimeStamp.add(Calendar.MINUTE,-(min % interval));
                                    adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                    adjustInfo = "Interval Round";
                                }
                                else{
                                    adjustedTimeStamp.add(Calendar.MINUTE, (interval - min % interval));
                                    adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                    adjustInfo = "Interval Round";
                                }
                        }
                    }
                }
                else{
                    //If the punch is after the interval but before the start time
                    if(originalTimeStamp.after(startInterval)  || originalTimeStamp.equals(startInterval)){//We want it before the interval
                        adjustedTimeStamp.setTimeInMillis(startTime.getTimeInMillis());//Moving the adjusted time stamp up to the right hour and minute of the start of the shift.
                        adjustInfo = "Shift Start";
                    }
                    //If the punch is before the shift start but not following any other rules
                    else{
                     if(min % interval != 0){

                            if(min % interval < interval/2){
                                adjustedTimeStamp.add(Calendar.MINUTE,-(min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                            else{
                                adjustedTimeStamp.add(Calendar.MINUTE, (interval - min % interval));
                                adjustedTimeStamp.add(Calendar.SECOND, -sec);
                                adjustInfo = "Interval Round";
                            }
                        }
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
/*
    //This one takes values you give it and sets the timestamp -Josh
    public void setOriginalTimeStamp(int year, int month, int day, int hour, int minute, int second) {
        this.originalTimeStamp.set(year, month, day, hour, minute, second);
        this.adjustedTimeStamp = this.originalTimeStamp;
    }
    */
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
    
    public GregorianCalendar getAdjustedTimeStamp(){
        return adjustedTimeStamp;
    }
}

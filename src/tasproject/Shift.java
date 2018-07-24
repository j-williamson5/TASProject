
package tasproject;

/**
 *
 * @author Dallen
 */
import java.sql.Time;

public class Shift {
    
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private Time startTime = new Time(0);
    private Time stopTime = new Time(0);
    private Time lunchStart = new Time(0);
    private Time lunchStop = new Time(0);
    long shiftLength;
    long lunchLength;
    
    //Need this empty constructor for databse -Matthew
    public Shift(){}
    
    //Constructor
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, String description, Time startTime,Time stopTime, Time lunchStart, Time lunchStop){
        
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock= dock;
        this.lunchDeduct = lunchDeduct;
        this.description = description;
    }
    
    
    @Override
    public String toString(){
        String returnString = description + ": " + startTime.toString().substring(0,5) + " - " + stopTime.toString().substring(0,5) + " (" + millisecondConverter(shiftLength) + " minutes); Lunch: " + lunchStart.toString().substring(0,5) + " - " + lunchStop.toString().substring(0,5) + " (" + millisecondConverter(lunchLength) + " minutes)";
        return returnString;
    }
    
    private String millisecondConverter(long milliseconds){
        long minutes = (milliseconds/1000) / 60;
        String result = String.valueOf(minutes);
        return result;
    }
    
    //Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getStopTime() {
        return stopTime;
    }

    public void setStopTime(Time stopTime) {
        this.stopTime = stopTime;
    }

    public Time getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(Time lunchStart) {
        this.lunchStart = lunchStart;
    }

    public Time getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(Time lunchStop) {
        this.lunchStop = lunchStop;
    }

    public long getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(long shiftLength) {
        this.shiftLength = shiftLength;
    }

    public long getLunchLength() {
        return lunchLength;
    }

    public void setLunchLength(long lunchLength) {
        this.lunchLength = lunchLength;
    }

}

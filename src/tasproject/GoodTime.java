/*
 This was to be used for Shift Class. We finally figured out nullPointer problems, and setter problems due to errors in TASDatabase
 */
package tasproject;

/**
 *
 * @author Josh and Matthew DON'T TOUCH!
 */
public class GoodTime {
    
    //Instance Fields
    int hours;
    int minutes;
    int seconds;
    
    //Constructors
    public GoodTime(){}
    
    public GoodTime(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    //Adds Leading Zeros to All numbers < 10
    public String addLeadingZeros(int i){
        if(i < 10){
            return "0" + String.valueOf(i);
        }
        else{
            return String.valueOf(i);
        }
    }
    
    @Override
    public String toString(){ //Returns in HH:MM:SS
        String stringHours = addLeadingZeros(hours);
        String stringMinutes = addLeadingZeros(minutes);
        String stringSeconds = addLeadingZeros(seconds);
        return stringHours + ":" + stringMinutes + ":" + stringSeconds;
    }
    
    //Setters and Getters
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
}

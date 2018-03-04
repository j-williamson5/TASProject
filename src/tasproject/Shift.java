/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasproject;

/**
 *
 * @author Dallen
 */
import java.sql.Time;

public class Shift {
    
    private int id, interval, gracePeriod, dock, lunchDeduct;
    private String description;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;
    private int lunchStartHour;
    private int lunchStartMin;
    private int lunchEndHour;
    private int lunchEndMin;
    //-Josh
    private Time tShiftStart;
    private Time tShiftEnd;
    private Time tLunchStart;
    private Time tLunchEnd;
    long shiftLength;
    long lunchLength;
            
    //Need this empty constructor for databse -Matthew
    public Shift(){}
    
    //Constructor
    public Shift(int id, int interval, int gracePeriod, int dock, int lunchDeduct, String description, int startHour, int startMin, int endHour, int endMin, int lunchStartHour, int lunchStartMin, int lunchEndHour, int lunchEndMin){
        
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock= dock;
        this.lunchDeduct = lunchDeduct;
        this.description = description;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.lunchStartHour = lunchStartHour;
        this.lunchStartMin = lunchStartMin;
        this.lunchEndHour = lunchEndHour;
        this.lunchEndMin = lunchEndMin;
        establishTimeObjects();
    }
    
    private void establishTimeObjects(){
        this.tShiftStart = new Time(startHour,startMin,0);
        this.tShiftEnd = new Time(endHour,endMin,0);
        this.tLunchStart = new Time(lunchStartHour,lunchStartMin,0);
        this.tLunchEnd = new Time(lunchEndHour,lunchEndMin,0);
        this.shiftLength = this.tShiftEnd.getTime() - this.tShiftStart.getTime();
        this.lunchLength = this.tLunchEnd.getTime() - this.tLunchStart.getTime();
    }
    
    @Override
    public String toString(){
        String returnString = description + ": " + tShiftStart.toString().substring(0,5) + " - " + tShiftEnd.toString().substring(0,5) + "(" + millisecondConverter(shiftLength) + "); Lunch: " + tLunchStart.toString().substring(0,5) + " - " + tLunchEnd.toString().substring(0,5) + "(" + millisecondConverter(lunchLength) + ")";
        return returnString;
    }
    
    private String millisecondConverter(long milliseconds){
        String result = String.valueOf((milliseconds / 1000) / 60);
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

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public int getLunchStartHour() {
        return lunchStartHour;
    }

    public void setLunchStartHour(int lunchStartHour) {
        this.lunchStartHour = lunchStartHour;
    }

    public int getLunchStartMin() {
        return lunchStartMin;
    }

    public void setLunchStartMin(int lunchStartMin) {
        this.lunchStartMin = lunchStartMin;
    }

    public int getLunchEndHour() {
        return lunchEndHour;
    }

    public void setLunchEndHour(int lunchEndHour) {
        this.lunchEndHour = lunchEndHour;
    }

    public int getLunchEndMin() {
        return lunchEndMin;
    }

    public void setLunchEndMin(int lunchEndMin) {
        this.lunchEndMin = lunchEndMin;
    }
}

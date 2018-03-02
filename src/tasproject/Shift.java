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
    
    private int id, interval, gracePeriod, dock, deduction;
    private String description;
    
    /*
    dallan this is matthew, Snellen said all these times need to be integers to i created the instance fields for you 
    if you will modify the constructor cool. 
    I just had problems with the databse with the Time data type so i went and asked him, other than that it's all good
    */
    
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;
    private int lunchStartHour;
    private int lunchStartMin;
    private int lunchEndHour;
    private int lunchEndMin;
    
    public Shift(){}
    public Shift(int id, int interval, int gracePeriod, int dock, int deduction, String description, Time startTime, Time stopTime, Time lunchStart, Time lunchStop){
        
        this.id = id;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock= dock;
        this.deduction = deduction;
        this.description = description;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        
    }

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

    public int getDeduction() {
        return deduction;
    }

    public void setDeduction(int deduction) {
        this.deduction = deduction;
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
    
    
    
    
}

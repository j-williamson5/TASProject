/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasproject;

import java.util.ArrayList;

import java.util.GregorianCalendar;

/**
 *
 * @author madel
 */
public class TASLogic {
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        int totalMinutes;
        int totalInMillis;
        
        // Creating time objects for each aspect of the shift 
        
        GregorianCalendar startTime = new GregorianCalendar();
        startTime.setTimeInMillis(shift.getStartTime().getTime());
        
        GregorianCalendar stopTime = new GregorianCalendar();
        stopTime.setTimeInMillis(shift.getStopTime().getTime());
        
        GregorianCalendar lunchStart = new GregorianCalendar();
        lunchStart.setTimeInMillis(shift.getLunchStart().getTime());
        
        GregorianCalendar lunchStop = new GregorianCalendar();
        lunchStop.setTimeInMillis(shift.getLunchStop().getTime());
        
        
        
        
        
        
        
        
        
        return totalMinutes;
    }
    
}

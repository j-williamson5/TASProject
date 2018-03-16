/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasproject;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import java.util.GregorianCalendar;

/**
 *
 * @author madel
 */
public class TASLogic {
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        int totalMinutes = 0;
        int totalInMillis = 0;
        
        // Creating time objects for each aspect of the shift 
        
        GregorianCalendar startTime = new GregorianCalendar();
        startTime.setTimeInMillis(shift.getStartTime().getTime());
        long startInMillis = startTime.getTimeInMillis();
        
        GregorianCalendar stopTime = new GregorianCalendar();
        stopTime.setTimeInMillis(shift.getStopTime().getTime());
        long stopInMillis = stopTime.getTimeInMillis();
        
        GregorianCalendar lunchStart = new GregorianCalendar();
        lunchStart.setTimeInMillis(shift.getLunchStart().getTime());
        long lunchStartMillis = lunchStart.getTimeInMillis();
        
        GregorianCalendar lunchStop = new GregorianCalendar();
        lunchStop.setTimeInMillis(shift.getLunchStop().getTime());
        long lunchStopMillis = lunchStop.getTimeInMillis();
        
        
        
        
        
        
        
        
        
        
        
        return totalMinutes;
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        /* Create ArrayList Object */
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();

        /* Create HashMap Object (one for every Punch!) */
        HashMap<String, String>  punchData = new HashMap<>();
        
        /*
        THINGS TO ADD
        id: The punch ID
        badgeid: The badge ID
        terminalid: The terminal ID
        eventtypeid: The event/punch type (0 for Clock Out, 1 for Clock In, etc.)
        eventdata: The adjustment description added to the punch during the adjustment (see Feature 3)
        originaltimestamp: The original timestamp (retrieve this timestamp as a long integer, then convert it into a string for storage in the HashMap)
        */
        
        for(Punch punch: dailypunchlist){
              /* Add Punch Data to HashMap */
              punchData.put("id", String.valueOf(punch.getId()));
              punchData.put("badgeid", String.valueOf(punch.getBadgeid()));
              punchData.put("terminalid", String.valueOf(punch.getTerminalid()));
              punchData.put("eventtypeid",String.valueOf(punch.getPunchtypeid()));
              punchData.put("eventdata",punch.getEventData());
              punchData.put("originaltimestamp",String.valueOf(punch.getOriginaltimestamp().getTimeInMillis()));
        }
        
        
        /* Append HashMap to ArrayList */
        jsonData.add(punchData);
        
    }
    
}

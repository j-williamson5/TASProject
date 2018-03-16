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
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailyPunchList, Shift shift){
        int totalMinutes = 0;
        int totalInMillis = 0;
        boolean insidePair;
        
        //ALGORITHM - Looping through the punch list and finding a "CLOCK IN" punch and a corresponding "CLOCK OUT" punch. Once we find those two we are
        //inside of a pair. Once inside a pair we calculate the minutes. After that we continue loop from the last "CLOCK OUT" punch. 
        
        int outFoundAt = 0;
        for(int i = outFoundAt; i < dailyPunchList.size();++i){
            //Getting a "CLOCK IN punch"
            if(dailyPunchList.get(i).id == 1){
                //boolean flag to flip once we find a "CLOCK OUT" punch.
                insidePair = false;
               
                //Searching for a corresponding "CLOCK OUT" punch to pair the "CLOCK IN" with.
                for(int j =outFoundAt; j < dailyPunchList.size();++j){
                    if(dailyPunchList.get(j).id == 0){
                        insidePair = true;
                        outFoundAt = j;
                        // We are inside a "CLOCK IN" and "CLOCK OUT" pair. Now we need to calculate minutes in between the two.
                        if(insidePair){
                            
                        }
                    }
                    
                        
                }
            }
        }
        
        
        
        
        
        
        
        
        
        
        
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

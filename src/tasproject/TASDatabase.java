/*
 Matthew and Josh
 */
package tasproject;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Josh and Matthew
 */
public class TASDatabase {
    
    //Instance fields
     String url;
     String username;
     String password;
     Connection conn;
     Statement stmt;
     
     //Constructor
    public TASDatabase() throws SQLException, InstantiationException, IllegalAccessException{
        this.url = "jdbc:mysql://localhost/tas";
        this.username = "tasuser";
        this.password = "teamone";
        openConnection();
    }
    
    //Methods
    //loads the JDBC Driver and connects to the SQL databse using .getConnection()
    private void openConnection() throws SQLException, InstantiationException, IllegalAccessException{
         try {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             conn = DriverManager.getConnection(url, username,password);
             stmt = conn.createStatement();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    //closes all connections to databse and stmt
    private void close() throws SQLException, InstantiationException, IllegalAccessException{
        conn.close();
        stmt.close();
    }
    
    public Punch getPunch(int id) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException{
        
        //SQL query to ask for the punch given the id
        this.stmt = conn.createStatement();
        Integer integerID = (Integer) id;
        String stringID = integerID.toString();
        ResultSet result = stmt.executeQuery("SELECT * FROM Event WHERE id=" + "'" + stringID + "'");
        
        //Initialize punch
        Punch p = new Punch();
        Badge b = new Badge();
        
        //Getting things from resultset
        if ( result != null ){
            while(result.next()){
                
                //Make a Time object for easy use in setters for the Punch object
                /*
                Timestamp timeStamp = result.getTimestamp("originaltimestamp");
                LocalDateTime dateTime = timeStamp.toLocalDateTime();
                ZoneId zone = TimeZone.getDefault().toZoneId();
                ZonedDateTime zoneDateTime = dateTime.atZone(zone);
                GregorianCalendar time = GregorianCalendar.from(zoneDateTime);
                */
                Timestamp timeStamp = result.getTimestamp("originaltimestamp");
                GregorianCalendar time = new GregorianCalendar();
                time.setTimeInMillis(timeStamp.getTime());
                
                //result.next();
                b.setId(result.getString("badgeid"));
                p.setBadge(b);
                p.setBadgeId(result.getString("badgeid"));
                p.setTerminalid(result.getInt("terminalid"));
                p.setPunchtypeid(result.getInt("eventtypeid"));
                p.setOriginalTimeStamp(time);
            }
        }
        
        return p;
        
    }
    
    public Badge getBadge(String id) throws SQLException, InstantiationException, IllegalAccessException{
        
        //SQL query to ask for the punch given the id
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Badge WHERE id=" + "'" + id + "'");
        
        //Initialize Badge to return
        Badge b = new Badge();
        
        //Getting things from resultset
        if ( result != null ){
            while(result.next()){
                //result.next();
                id = result.getString("id");
                String desc = result.getString("description");
                b.setId(id);
                b.setDescription(desc);
            }
        }
        
        return b;
        
    }
    
    public Shift getShift(int id) throws SQLException, InstantiationException, IllegalAccessException{
        
        //SQL Query for shift
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Shift WHERE id=" + "'" + id + "'");
        
        //Initialize shift to return
        Shift s = new Shift();
        
        //Getting things from resultset
        if(result != null){
            while(result.next()){
                //result.next();
                s.setId(result.getInt("id"));
                s.setDescription(result.getString("description"));
                s.setInterval(result.getInt("interval"));
                s.setGracePeriod(result.getInt("graceperiod"));
                s.setDock(result.getInt("dock"));
                s.setLunchDeduct(result.getInt("lunchdeduct"));
                s.setStartTime(result.getTime("start"));
                s.setStopTime(result.getTime("stop"));
                s.setLunchStart(result.getTime("lunchstart"));
                s.setLunchStop(result.getTime("lunchstop"));
                s.setShiftLength(s.getStopTime().getTime() - s.getStartTime().getTime());
                s.setLunchLength(s.getLunchStop().getTime() - s.getLunchStart().getTime());
            }
        }
        
        return s;
        
    }
    
    public Shift getShift(String badgeID) throws SQLException{
            
        //SQL Query for shift
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Employee WHERE badgeid=badgeID");
        
        String employeeShiftID = "";
        //Getting things from resultset
        if(result != null){
            while(result.next()){
                result.next();
                employeeShiftID = result.getString("shiftid");
            }
        }

        return getShift(employeeShiftID);
    }
    
    public int insertPunch(Punch p) throws SQLException{
        
        //SQL Query for punch
        int key = 0, result = 0;
        ResultSet keys = null;
        String sql =("INSERT INTO event (badgeid, originaltimestamp, terminalid, eventtypeid) VALUES (?,?,?,?)");
        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        //Vatiables for inserting into database
        int id;
        int terminalID = p.getTerminalid();
        String badgeID = p.getBadgeid();
        GregorianCalendar originalTimeStamp = p.getOriginaltimestamp();
        int eventtypeid = p.getPunchtypeid();
        String eventdata = p.getEventData();
        System.out.println("The ID being entered into the database is: " + badgeID);
        
        ps.setString(1, badgeID);
        ps.setString(2, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(originalTimeStamp.getTime()));
        ps.setInt(3,terminalID);
        ps.setInt(4, eventtypeid);
        
        result = ps.executeUpdate();
        System.out.println("Result: " + ps);
        if (result == 1) {
            keys = ps.getGeneratedKeys();
        }
        if (keys.next()) {
            key = keys.getInt(1);
        }
        return key;
    }
    
    public ArrayList getDailyPunchList(Badge b, GregorianCalendar ts) throws SQLException{
        //list to return
        ArrayList <Punch> resultList = new ArrayList<>();
        
        //sql statement and query for daily punch list
        this.stmt = conn.createStatement();
        //retreiving the timestamp from database that belong to the badgeid given
        ResultSet result = stmt.executeQuery("SELECT * FROM Event WHERE badgeid = " + "'"+ b +"'");
        
        //get day from gregorian calendar
        long milliTime = ts.getTimeInMillis();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(milliTime);
        int dayGiven = c.get(Calendar.DAY_OF_MONTH);
        
        //iterating througt the results
        if(result != null){
            while(result.next()){
                result.next();
                //get day from timestamp that the query returned
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(result.getLong("originaltimestamp"));
                int dayRetreived = cal.get(Calendar.DAY_OF_MONTH);
                
                //if the days are the same we want to return all this badgeid's punches
                //punch takes badgeid, terminalid, and punchtypeid
                if(dayGiven == dayRetreived){
                    //creating and adding the punches to the list to return
                    Punch p = new Punch(result.getString("badgeid"), result.getInt("terminalid"), result.getInt("eventtypeid"));
                    resultList.add(p);
                    
                }//end if day given equals day retreived
            }//end while
        }//end if result is not null
        
        return resultList;
        
    }//end arrraylist method
    
}//end class

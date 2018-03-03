/*
 Matthew and Josh
 */
package tasproject;
import java.sql.*;
import java.lang.*;
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
             DriverManager.getConnection(url, username,password);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    //closes all connections to databse and stmt
    private void close() throws SQLException, InstantiationException, IllegalAccessException{
        conn.close();
        stmt.close();
    }
    
    public Punch getPunch(String id) throws SQLException, InstantiationException, IllegalAccessException{
        
        //SQL query to ask for the punch given the id
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Event WHERE id=id");
        
        //Initialize punch
        Punch p = new Punch();
        Badge b = new Badge();
        
        //Getting things from resultset
        if ( result != null ){
            //Make a Time object for easy use in setters for the Punch object
            Time timeStamp = result.getTime("originaltimestamp");
            
            result.next();
            b.setId(result.getString("badgeid"));
            p.setBadge(b);
            p.setTerminalid(result.getInt("terminalid"));
            p.setPunchtypeid(result.getInt("eventtypeid"));
            p.setYear(timeStamp.getYear());
            p.setMonth(timeStamp.getMonth());
            p.setDay(timeStamp.getDay());
            p.setHour(timeStamp.getHours());
            p.setMinute(timeStamp.getMinutes());
            p.setSecond(timeStamp.getSeconds());
            p.setOriginalTimeStamp();
        }
        
        return p;
        
    }
    
    public Badge getBadge(String id) throws SQLException, InstantiationException, IllegalAccessException{
        
        //SQL query to ask for the punch given the id
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Badge WHERE id=id");
        
        //Initialize Badge to return
        Badge b = new Badge();
        
        //Getting things from resultset
        if ( result != null ){
            result.next();
            id = result.getString("id");
            String desc = result.getString("description");
            b.setId(id);
            b.setDescription(desc);
        }
        
        return b;
        
    }
    
    public Shift getShift(String id) throws SQLException, InstantiationException, IllegalAccessException{
        
        //SQL Query for shift
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Shift WHERE id=id");
        
        //Initialize shift to return
        Shift s = new Shift();
        
        //Getting things from resultset
        if(result != null){
            result.next();
            s.setId(result.getInt("id"));
            s.setDescription(result.getString("description"));
            s.setInterval(result.getInt("interval"));
            s.setGracePeriod(result.getInt("graceperiod"));
            s.setDock(result.getInt("dock"));
            s.setDeduction(result.getInt("deduction"));
            s.setStartHour(result.getTime("start").getHours());
            s.setStartMin(result.getTime("start").getMinutes());
            s.setEndHour(result.getTime("stop").getHours());
            s.setEndMin(result.getTime("stop").getMinutes());
            s.setLunchStartHour(result.getTime("lunchstart").getHours());
            s.setLunchStartMin(result.getTime("lunchstart").getMinutes());
            s.setLunchEndHour(result.getTime("lunchstop").getHours());
            s.setLunchEndMin(result.getTime("lunchstop").getMinutes());
        }
        
        return s;
        
    }
}

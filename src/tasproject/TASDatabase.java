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
        ResultSet result = stmt.executeQuery("SELECT * FROM Punch WHERE id=id");
        //getting things from resultset
        if ( result != null ){
            result.next();
            id = result.getString("id");
            String desc = result.getString("description");
        }
        return ;
    }
    
    public Badge getBadge(String id) throws SQLException, InstantiationException, IllegalAccessException{
        //SQL query to ask for the punch given the id
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Badge WHERE id=id");
        Badge b = new Badge();
        //getting things from resultset
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
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Shift WHERE id=id");
        Shift s = new Shift();
        
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

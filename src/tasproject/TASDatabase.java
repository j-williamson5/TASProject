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
    private void closeConnection() throws SQLException, InstantiationException, IllegalAccessException{
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
            b = new Badge(id, desc);
        }
        return b;
    }
    public Shift getShift(String id) throws SQLException, InstantiationException, IllegalAccessException{
        this.stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Shift WHERE id=id");
        Shift s = new Shift();
        
        if(result != null){
            result.next();
            int idInt = result.getInt("id");
            String desc = result.getString("description");
            int interval = result.getInt("interval");
            int gracePer = result.getInt("graceperiod");
            int dock = result.getInt("dock");
            int deduct = result.getInt("deduction");
            int startH = result.getTime("start").getHours();
            int startM = result.getTime("start").getMinutes();
            int endH = result.getTime("stop").getHours();
            int endM = result.getTime("stop").getMinutes();
            int lunchStartH = result.getTime("lunchstart").getHours();
            int lunchStartM = result.getTime("lunchstart").getMinutes();
            int lunchEndH = result.getTime("lunchstop").getHours();
            int lunchEndM = result.getTime("lunchstop").getMinutes();
            // int lunchStartHour, int lunchStartMin, int lunchEndHour, int lunchEndMin
            s = new Shift(idInt, interval, gracePer, dock, deduct, desc, startH, startM, endH, endM, lunchStartH, lunchStartM, lunchEndH, lunchEndM);
        }
        return s;
        
    }
    
    

}

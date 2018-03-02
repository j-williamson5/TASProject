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
            id = result.getString(id);
            String desc = result.getString("description");
            int interval = result.getInt("interval");
            int gracePer = result.getInt("graceperiod");
            int dock = result.getInt("dock");
            
            s = new Shift(id, desc, interval, gracePer, dock, )
        }
        
    }
    
    

}

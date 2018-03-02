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
             this.stmt = conn.createStatement();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    //closes all connections to databse and stmt
    private void closeConnection() throws SQLException, InstantiationException, IllegalAccessException{
        conn.close();
        stmt.close();
    }
    
    public Punch getPunch(String id){
        
    }
    public Badge getBadge(String id){
        
    }
    public Shift getShift(String id){
        
    }
    
    

}

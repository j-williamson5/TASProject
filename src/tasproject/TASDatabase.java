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
        establishConnections();
    }
    //Methods
    private void establishConnections() throws SQLException, InstantiationException, IllegalAccessException{
         try {
             Class.forName("com.mysql.jdbc.Driver").newInstance();
             DriverManager.getConnection(url, username,password);
             this.stmt = conn.createStatement();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(TASDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    

}

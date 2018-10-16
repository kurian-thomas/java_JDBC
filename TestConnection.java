import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
 
public class TestConnection {
 
  public static void main(String[] argv) throws Exception{
        Connection conn = null;
     
        String driver   = "com.mysql.jdbc.Driver";
        String db       = "kurian";
        String url      = "jdbc:mysql://localhost:3306/" + db;
        String user     = "root";
        String pass     = "";
  
        try {
          Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database : " + db);
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
     
    }
}
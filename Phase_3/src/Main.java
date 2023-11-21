import java.sql.*;

public class Main{
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USER_ID = "fift";
    public static final String USER_PASSWD = "comp322";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Success!");
        } catch (ClassNotFoundException e) {
            System.err.println("error = " + e.getMessage());
            System.exit(1);
        }

        // Make a connection
        try {
            conn = DriverManager.getConnection(URL, USER_ID, USER_PASSWD);
            System.out.println("Connected.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
            System.err.println("Cannot get a connection: " + ex.getMessage());
            System.exit(1);
        }

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
        } catch (SQLException ex2) {
            System.err.println("sql error = " + ex2.getMessage());
            System.exit(1);
        }

        FiftService.start_Service(conn,stmt);

        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
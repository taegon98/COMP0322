import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class init {

    public static void init_(Connection conn,Statement stmt) throws SQLException {
        init_OrderDetail(conn,stmt);
        init_Customer(conn,stmt);
    }
    public static void init_OrderDetail(Connection conn, Statement stmt) throws SQLException {

        String query="UPDATE order_detail o " +
                "SET o.total_price = (" +
                "SELECT p.price " +
                "FROM product p " +
                "WHERE p.product_id = o.product_id) * o.quantity";
        PreparedStatement psmt=conn.prepareStatement(query);
        psmt.executeQuery();
    }

    public static void init_Customer(Connection conn,Statement stmt) throws SQLException {
        String query="UPDATE customer c " +
                "SET c.amount = (" +
                "SELECT SUM(total_price) " +
                "FROM orders o " +
                "JOIN order_detail d ON o.order_Id = d.order_Id " +
                "WHERE o.customer_id = c.customer_id " +
                "GROUP BY o.customer_id) " +
                "WHERE EXISTS (" +
                "SELECT 1 " +
                "FROM orders o " +
                "WHERE o.customer_id = c.customer_id)";
        PreparedStatement psmt=conn.prepareStatement(query);
        psmt.executeQuery();
    }
}

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    public static void getOrderDetailBetweenDate(Connection conn, Statement stmt) {// 8. 주문일이 특정 시점 사이에 있는 오더 디테일 검색
        try {

            System.out.print("Enter the start date (yyyy-MM-dd): ");
            String startDateStr = sc.next();
            System.out.print("Enter the end date (yyyy-MM-dd): ");
            String endDateStr = sc.next();


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);


            String sql = "SELECT * FROM ORDERS O " +
                    "INNER JOIN ORDER_DETAIL OD ON O.Order_id = OD.Order_id " +
                    "WHERE O.Order_date BETWEEN ? AND ?";


            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setDate(1, new java.sql.Date(startDate.getTime()));
            psmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = psmt.executeQuery();


            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int orderId = rs.getInt("Order_id");
                int customerId = rs.getInt("Customer_id");
                Date orderDate = rs.getDate("Order_date");

                System.out.println("Order ID: " + orderId + ", Customer ID: " + customerId + ", Order Date: " + orderDate);
            }

            if (!hasResults) {
                System.out.println("No orders found for the given date range.");
            }

        } catch (SQLException | ParseException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }
}

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


            String sql="SELECT O.Order_date,C.NAME,P.Name AS Product_name, OD.Quantity,OD.total_price " +
                    "FROM ORDERS O JOIN CUSTOMER C ON O.CUSTOMER_ID=C.CUSTOMER_ID " +
                    "JOIN ORDER_DETAIL OD ON O.Order_id = OD.Order_id " +
                    "JOIN PRODUCT P ON OD.Product_id = P.Product_id " +
                    "WHERE O.Order_date BETWEEN ? AND ?";

            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setDate(1, new java.sql.Date(startDate.getTime()));
            psmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = psmt.executeQuery();


            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                Date orderDate = rs.getDate("Order_date");
                String user_Name=rs.getString("Name");
                String product_Name=rs.getString("Product_Name");
                int Quantity=rs.getInt("Quantity");
                int total_Price=rs.getInt("total_Price");

                System.out.println("Order Date : "+orderDate+" || Name : "+user_Name
                +" || Product Name : "+product_Name+" || Quantity : "+Quantity+" || total Price : "+total_Price);
            }

            if (!hasResults) {
                System.out.println("No orders found for the given date range.");
            }

        } catch (SQLException | ParseException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }


    //금액을 입력 받고 Order_Detail에서 총 구매액이 입력 금액 보다 큰 값 반환
    public static void getOrderDetail(Connection conn, Statement stmt) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Price: ");
            int price = sc.nextInt();
            System.out.println();

            String query = "SELECT C.NAME, O.ORDER_DATE, D.TOTAL_PRICE " +
                    "FROM ORDER_DETAIL D " +
                    "INNER JOIN PRODUCT P ON P.PRODUCT_ID = D.PRODUCT_ID " +
                    "INNER JOIN ORDERS O ON O.ORDER_ID = D.ORDER_ID " +
                    "INNER JOIN CUSTOMER C ON C.CUSTOMER_ID = O.CUSTOMER_ID " +
                    "WHERE D.TOTAL_PRICE >= ? " +
                    "ORDER BY C.NAME ASC";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, price);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("NAME");
                Date orderDate = rs.getDate("ORDER_DATE");
                double totalPrice = rs.getDouble("TOTAL_PRICE");

                System.out.println("Customer Name: " + customerName + ", Order Date: " + orderDate + ", Total Price: " + totalPrice);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }

}

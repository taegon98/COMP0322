import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OrderService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    private static void query8(Connection conn, Statement stmt) {// 주문일이 특정 시점 사이에 있는 오더 디테일 검색
        try {
            // 입력 받은 주문일의 시작일과 종료일 설정
            System.out.print("Enter the start date (yyyy-MM-dd): ");
            String startDateStr = sc.next();
            System.out.print("Enter the end date (yyyy-MM-dd): ");
            String endDateStr = sc.next();

            // SimpleDateFormat을 사용하여 입력받은 문자열을 Date 객체로 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            // SQL 쿼리문 작성
            String sql = "SELECT * FROM ORDERS O " +
                    "INNER JOIN ORDER_DETAIL OD ON O.Order_id = OD.Order_id " +
                    "WHERE O.Order_date BETWEEN ? AND ?";

            // PreparedStatement를 사용하여 쿼리 실행
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setDate(1, new java.sql.Date(startDate.getTime()));
            psmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = psmt.executeQuery();

            // 결과 출력
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

    //금액을 입력 받고 Order_Detail에서 총 구매액이 입력 금액 보다 큰 값 반환
    private static void getOrderDetail(Connection conn, Statement stmt) {

        System.out.print("금액: ");
        int price = sc.nextInt();
        System.out.println();

        String query = "SELECT C.NAME, O.ORDER_DATE, D.TOTAL_PRICE " +
                "FROM ORDER_DETAIL D " +
                "INNER JOIN PRODUCT P ON P.PRODUCT_ID = D.PRODUCT_ID " +
                "INNER JOIN CUSTOMER C ON C.CUSTOMER_ID = D.CUSTOMER_ID " +
                "WHERE D.TOTAL_PRICE >= " + price +
                "ORDER BY C.NAME ASC";
    }
}

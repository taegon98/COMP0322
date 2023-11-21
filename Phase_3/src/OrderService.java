import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class OrderService {

    static Scanner sc = new Scanner(System.in);

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

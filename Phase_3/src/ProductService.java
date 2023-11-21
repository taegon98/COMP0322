import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProductService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;


    private static void query3(Connection conn, Statement stmt) {

        System.out.print("어깨 사이즈: ");
        String shoulder = sc.next();
        System.out.println();

        System.out.print("허리 사이즈: ");
        String waist = sc.next();
        System.out.println();

        String query = "SELECT COUNT(*) AS \"THE NUMBER OF MEMBERS\" " +
                "FROM CUSTOMER C " +
                "INNER JOIN CUSTOMER_SIZE S ON C.CUSTOMER_ID = S.CUSTOMER_ID " +
                "WHERE S.SHOULDER >= '" + shoulder + "' AND S.WAIST >= '" + waist + "'";
    }

    private static void query4(Connection conn, Statement stmt) {

        System.out.print("구매 금액: ");
        int price = sc.nextInt();
        System.out.println();

        String query = "SELECT C.NAME, C.AMOUNT, T.NAME " +
                "FROM CUSTOMER C " +
                "INNER JOIN TIER T ON C.TIER_ID = T.TIER_ID " +
                "WHERE C.AMOUNT >= " + price +
                "ORDER BY C.PRICE ASC";
    }


    private static void query6(Connection conn, Statement stmt) {

        System.out.print("가격 (예: 50000): ");
        int price = sc.nextInt();
        System.out.println();

        String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                "FROM PRODUCT P " +
                "WHERE P.PRICE >= " + price +
                "ORDER BY P.PRICE DESC";
    }

    private static void query7(Connection conn, Statement stmt) {

        System.out.print("물품 이름 (예: JJANG JJANG JEANS): ");
        String name = sc.next();
        System.out.println();

        String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                "FROM PRODUCT P " +
                "WHERE P.NAME LIKE '%" + name + "%' " +
                "ORDER BY P.PRICE DESC";
    }

    private static void query9(Connection conn, Statement stmt) {

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

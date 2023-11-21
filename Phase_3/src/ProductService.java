import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProductService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

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

    private static void query8(Connection conn, Statement stmt) {

        System.out.print("회원 아이디: ");

    }
}

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class ProductService {
    static Scanner sc = new Scanner(System.in);

    //금액을 입력받고 해당 금액보다 큰 물품 반환
    private static void getProductByPrice(Connection conn, Statement stmt) {

        System.out.print("가격 (예: 50000): ");
        int price = sc.nextInt();
        System.out.println();

        String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                "FROM PRODUCT P " +
                "WHERE P.PRICE >= " + price +
                "ORDER BY P.PRICE DESC";
    }

    // 물품 이름의 일부를 입력 받고 해당 글자가 포함된 물품 반환
    private static void getProductByName(Connection conn, Statement stmt) {

        System.out.print("물품 이름 (예: JJANG JJANG JEANS): ");
        String name = sc.next();
        System.out.println();

        String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                "FROM PRODUCT P " +
                "WHERE P.NAME LIKE '%" + name + "%' " +
                "ORDER BY P.PRICE DESC";
    }
}

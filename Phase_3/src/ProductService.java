import java.sql.*;
import java.util.Scanner;

public class ProductService {

    //금액을 입력받고 해당 금액보다 큰 물품 반환
    private static void getProductByPrice(Connection conn, Statement stmt) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter price: ");
            int price = sc.nextInt();
            System.out.println();

            String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                    "FROM PRODUCT P " +
                    "WHERE P.PRICE >= ? " +
                    "ORDER BY P.PRICE DESC";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, price);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("NAME");
                String productDescription = rs.getString("DESCRIPTION");
                double productPrice = rs.getDouble("PRICE");

                System.out.println("Product Name: " + productName + ", Description: " + productDescription + ", Price: " + productPrice);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 물품 이름의 일부를 입력 받고 해당 글자가 포함된 물품 반환
    private static void getProductByName(Connection conn, Statement stmt) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            System.out.println();

            String query = "SELECT P.NAME, P.DESCRIPTION, P.PRICE " +
                    "FROM PRODUCT P " +
                    "WHERE P.NAME LIKE ? " +
                    "ORDER BY P.PRICE DESC";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, "%" + name + "%");

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("NAME");
                String productDescription = rs.getString("DESCRIPTION");
                double productPrice = rs.getDouble("PRICE");

                System.out.println("Product Name: " + productName + ", Description: " + productDescription + ", Price: " + productPrice);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
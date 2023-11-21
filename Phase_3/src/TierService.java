import java.sql.*;
import java.util.Scanner;

public class TierService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    private static void query10(Connection conn, Statement stmt) {// 티어 입력 할인율

        try {

            System.out.print("Enter the coupon Id: ");
            int couponId = sc.nextInt();


            String query = "SELECT Percentage FROM COUPON WHERE Coupon_id = ?";


            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, couponId);

            // 쿼리 실행
            ResultSet rs = psmt.executeQuery();

            // 결과 출력
            if (rs.next()) {
                int discountPercentage = rs.getInt("Percentage");
                System.out.println("Coupon discount percentage: " + discountPercentage + "%");
            } else {
                System.out.println("No corresponding coupon found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void query5(Connection conn, Statement stmt) {// 특정 티어 이상의 회원 찾기
        System.out.print("Please enter the Tier:");
        String tier = sc.next();
        System.out.println();

        String sql = "SELECT C.Customer_id FROM CUSTOMER C WHERE Tier_id >= (SELECT Tier_id FROM TIER WHERE Name = ?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, tier);

            ResultSet rs = psmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int customerId = rs.getInt("Customer_id");
                System.out.println("Customer ID: " + customerId);
            }

            if (!hasResults) {
                System.out.println("No customers found for the given tier.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }
}

import java.sql.*;
import java.util.Scanner;

public class TierService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    public static void getTierDiscount(Connection conn, Statement stmt) {// 10. 티어 입력 할인율
//        Coupon_id       NUMBER      NOT NULL,
        try {

            System.out.print("Enter the coupon Id: ");
            int couponId = sc.nextInt();

            System.out.println(couponId);

            String query = "SELECT Percentage FROM COUPON WHERE Coupon_id = ?";


            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, couponId);


            ResultSet rs = psmt.executeQuery();


            if (rs.next()) {
                double discountPercentage = rs.getDouble("Percentage");
                System.out.println("Coupon discount percentage: " + discountPercentage + "%");
            } else {
                System.out.println("No corresponding coupon found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getTierCustomer(Connection conn, Statement stmt) {// 5. 특정 티어 이상의 회원 찾기
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

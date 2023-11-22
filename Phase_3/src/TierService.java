import java.sql.*;
import java.util.Scanner;

public class TierService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    public static void getTierDiscount(Connection conn, Statement stmt) {// 11. 티어 입력 할인율
//        Coupon_id       NUMBER      NOT NULL,
        try {

            System.out.print("Enter the level: ");
            int tierId = sc.nextInt();
            String query = "SELECT t.BENEFIT FROM TIER t WHERE t.TIER_ID = ?";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, tierId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                double discountPercentage = rs.getDouble("BENEFIT");
                System.out.println("level : " + tierId + " discount percentage: " + discountPercentage + "%");
            } else {
                System.out.println("No corresponding coupon found.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getTierCustomer(Connection conn, Statement stmt) {
        System.out.print("Please enter the level: ");
        int tier_Id=sc.nextInt();

        String sql = "SELECT C.user_id, C.name FROM CUSTOMER C WHERE c.tier_id=?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,tier_Id);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                String user_id=rs.getString("user_id");
                String user_Name=rs.getString("Name");
                System.out.println("User_ID: " + user_id + ", Name: " + user_Name);
            }
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }

    }
}

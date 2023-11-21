import java.sql.*;
import java.util.Scanner;

public class CustomerService {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs;

    private static void query1(Connection conn, Statement stmt) {// 비밀번호 변경
        System.out.print("Please enter the ID:");
        String id = sc.next();
        System.out.print("Please enter the current password for the change:");
        String unchangedPw = sc.next();
        System.out.print("Please enter the new password for the change:");
        String updatePw = sc.next();
        System.out.println();

        String sql = "UPDATE USERS set Password = ? WHERE User_id = ? AND Password = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, updatePw);
            psmt.setString(2, id);
            psmt.setString(3, unchangedPw);
            int canChange = psmt.executeUpdate();
            if (canChange != 0) {
                System.out.println("The password has been changed.");
            } else {
                System.out.println("The password change was not completed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void query2(Connection conn, Statement stmt) {// 신체 사이즈 업데이트

        System.out.print("Please enter the ID:");
        String id = sc.next();
        System.out.print("Please enter the Top_length:");
        String top_length = sc.next();
        System.out.print("Please enter the Waist:");
        String waist = sc.next();
        System.out.println();

        String sql = "UPDATE CUSTOMER_SIZE set Top_Length = ?, Waist = ? WHERE User_id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, top_length);
            psmt.setString(2, waist);
            psmt.setString(3, id);

            int canChange = psmt.executeUpdate();
            if (canChange != 0) {
                System.out.println("The size has been successfully updated.");
            } else {
                System.out.println("The size update has failed.");
            }
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }

}

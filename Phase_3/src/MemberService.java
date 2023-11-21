import java.sql.*;
import java.util.Scanner;

public class MemberService {

    static Scanner sc = new Scanner(System.in);

    //어깨 사이즈 & 허리 사이즈 입력 → 해당하는 사이즈 보다 크거나 같은 사이즈를 같은 회원 수 반환
    public static void getMemberCountBySize(Connection conn, Statement stmt) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Shoulder size: ");
            String shoulder = sc.next();
            System.out.println();

            System.out.print("Waist size: ");
            String waist = sc.next();
            System.out.println();

            String query = "SELECT COUNT(*) AS \"THE NUMBER OF MEMBERS\" " +
                    "FROM CUSTOMER C " +
                    "INNER JOIN CUSTOMER_SIZE S ON C.CUSTOMER_ID = S.CUSTOMER_ID " +
                    "WHERE S.SHOULDER >= ? AND S.WAIST >= ?";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, shoulder);
            psmt.setString(2, waist);

            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                int memberCount = rs.getInt("THE NUMBER OF MEMBERS");
                System.out.println("Number of members with sizes greater than or equal to the input: " + memberCount);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }


    //구매한 금액을 입력 받고 해당 금액보다 같거나 큰 회원 정보 반환
    public static void getMembersByAmount(Connection conn, Statement stmt) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Purchase amount: ");
            int price = sc.nextInt();
            System.out.println();

            String query = "SELECT C.NAME, C.AMOUNT, T.NAME " +
                    "FROM CUSTOMER C " +
                    "INNER JOIN TIER T ON C.TIER_ID = T.TIER_ID " +
                    "WHERE C.AMOUNT >= ? " +
                    "ORDER BY C.AMOUNT ASC";

            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setInt(1, price);

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                String memberName = rs.getString("NAME");
                int amount = rs.getInt("AMOUNT");
                String tierName = rs.getString("NAME");

                System.out.println("Member Name: " + memberName + ", Purchase Amount: " + amount + ", Tier: " + tierName);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void register(Connection conn, Statement stmt) {

        String user_name,user_Id,user_Pw,phone_Number,Address;
        int postal_code;

        System.out.println("-------------------");
        System.out.println("I'll sign up as a member");
        System.out.println("Please enter each item");
        System.out.println("User_Name: ");
        user_name=sc.next();

        System.out.println("User_Id: ");
        user_Id=sc.next();

        System.out.println("user_Pw: ");
        user_Pw=sc.next();

        System.out.println("phone_Number: ");
        phone_Number=sc.next();

        System.out.println("Address: ");
        Address=sc.next();

        System.out.println("Postal_Code: ");
        postal_code=sc.nextInt();

        // 쿼리 들어갈 부분


        System.out.println("I have registered as a member");
    }

    public static void login(Connection conn,Statement stmt) {
        String memberId;
        String memberPw;
        System.out.println("-------------------");
        System.out.println("ID : ");
        memberId=sc.next();
        System.out.println("PW : ");
        memberPw=sc.next();
        System.out.println("-------------------");

        // 쿼리 들어갈 부분

        System.out.println("You have been logged in.");
    }

    public static void getUpdatePassword(Connection conn, Statement stmt) {// 1. 비밀번호 변경
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

    public static void getUpdateCustomerSize(Connection conn, Statement stmt) {// 2. 신체 사이즈 업데이트

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

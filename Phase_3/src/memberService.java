import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class memberService {

    static Scanner sc = new Scanner(System.in);

    public static void register(Connection conn,Statement stmt) throws SQLException {


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
        sc.nextLine();

        // 쿼리 들어갈 부분
        ResultSet rs=null;

        // 현재 Customer 수
        String sql="";
        sql="SELECT COUNT(*) FROM CUSTOMER";
        rs=stmt.executeQuery(sql);
        int curr_Count = 0;

        if (rs.next()) {
            // Retrieve the value of the first column from the current row
            curr_Count = rs.getInt(1);
        } else {
            System.out.println("No rows found in the result set.");
        }
        //rs.close();


        PreparedStatement pstmt=null;
        sql="INSERT INTO CUSTOMER (CUSTOMER_ID,TIER_ID,NAME,USER_ID,PASSWORD,PHONE_NUMBER,ADDRESS,POSTAL_CODE,AMOUNT,COUPON_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,(curr_Count+1));
            pstmt.setInt(2,1);
            pstmt.setString(3,user_name);
            pstmt.setString(4,user_Id);
            pstmt.setString(5,user_Pw);
            pstmt.setString(6,phone_Number);
            pstmt.setString(7,Address);
            pstmt.setInt(8,postal_code);
            pstmt.setInt(9,0);
            pstmt.setInt(10,0);
            pstmt.executeUpdate();

            System.out.println("You have registered as a member");
        }
        catch (SQLException e) {
            System.err.println("SQL error = " + e.getMessage());
            e.printStackTrace();
        }
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
}

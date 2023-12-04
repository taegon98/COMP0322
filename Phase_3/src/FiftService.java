import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FiftService {
    
    static Scanner sc = new Scanner(System.in);

    public static void start_Service(Connection conn, Statement stmt) throws SQLException {
        System.out.println("Welcome to Our Shopping Mall");

        while(true) {
            System.out.println("---------------------------");
            System.out.println("Are you an existing member?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.println("---------------------------");

            int isMember=sc.nextInt();
            if(isMember==1) {
                MemberService.login(conn,stmt);
                break;
            }
            else {
                MemberService.register(conn,stmt);
            }
        }


        int serviceCom;
        boolean check_Flag=false;

        while(true) {
            System.out.println("---------------------------");
            System.out.println("Try the following functions");
            System.out.println("1) 해당하는 사이즈 보다 크거나 같은 사이즈를 같은 회원 수 검색");
            System.out.println("2) 구매한 금액을 입력 받고 해당 금액보다 같거나 큰 회원 정보 검색");
            System.out.println("3) 비밀번호 변경");
            System.out.println("4) 신체 사이즈 업데이트");
            System.out.println("5) 금액을 입력받고 해당 금액보다 큰 물품 검색");
            System.out.println("6) 물품 이름의 일부를 입력 받고 해당 글자가 포함된 물품 검색");
            System.out.println("7) 기간 입력시 해당 기간의 오더 디테일 검색");
            System.out.println("8) 금액을 입력 받고 Order_Detail에서 총 구매액이 입력 금액 보다 큰 값 검색");
            System.out.println("9) 티어 입력 할인율");
            System.out.println("10) 특정 티어 이상의 회원 검색");
            System.out.println("11) QUIT");
            System.out.println("---------------------------");

            serviceCom=sc.nextInt();

            switch (serviceCom) {
                case 1:
                    MemberService.getMemberCountBySize(conn,stmt);
                    break;
                case 2:
                    MemberService.getMembersByAmount(conn,stmt);
                    break;
                case 3:
                    MemberService.getUpdatePassword(conn,stmt);
                    break;
                case 4:
                    MemberService.getUpdateCustomerSize(conn,stmt);
                    break;
                case 5:
                    ProductService.getProductByPrice(conn,stmt);
                    break;
                case 6:
                    ProductService.getProductByName(conn,stmt);
                    break;
                case 7:
                    OrderService.getOrderDetailBetweenDate(conn,stmt);
                    break;
                case 8:
                    OrderService.getOrderDetail(conn,stmt);
                    break;
                case 9:
                    TierService.getTierDiscount(conn,stmt);
                    break;
                case 10:
                    TierService.getTierCustomer(conn,stmt);
                    break;
                case 11:
                    check_Flag=true;
                    break;
            }
            if(check_Flag){
                System.out.println("Good Bye~~");
                break;
            }
        }
    }
}

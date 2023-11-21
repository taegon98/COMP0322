import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class fiftService {


    static Scanner sc = new Scanner(System.in);

    public static void start_Service(Connection conn, Statement stmt) throws SQLException {
        System.out.println("Welcome to Our Shopping Mall");

        while(true) {
            System.out.println("---------------------------");
            System.out.println("Are you an existing member?");
            System.out.println("1.Yes");
            System.out.println("2.No");

            while(true) {
                int isMember=sc.nextInt();
                if(isMember==1) {
                    memberService.login(conn,stmt);
                    break;
                }
                else {
                    memberService.register(conn,stmt);
                }
            }

            int serviceCom;
            boolean check_Flag=false;

            while(true) {
                System.out.println("Try the following functions");
                System.out.println("1) ");
                System.out.println("2) ");
                System.out.println("3) ");
                System.out.println("4) ");
                System.out.println("5) ");
                System.out.println("6) ");
                System.out.println("7) ");
                System.out.println("8) ");
                System.out.println("9) ");
                System.out.println("10) ");
                System.out.println("Any Num) QUIT");
                System.out.println("---------------------------");

                serviceCom=sc.nextInt();

                switch (serviceCom) {
                    case 1:
                        System.out.println(11);
                        break;
                    case 2:
                        System.out.println(22);
                        break;
                    case 3:
                        System.out.println(33);
                        break;
                    case 4:
                        System.out.println(44);
                        break;
                    case 5:
                        System.out.println(55);
                        break;
                    case 6:
                        System.out.println(66);
                        break;
                    case 7:
                        System.out.println(77);
                        break;
                    case 8:
                        System.out.println(88);
                        break;
                    case 9:
                        System.out.println(99);
                        break;
                    case 10:
                        System.out.println(1010);
                        break;
                    case default:
                        System.out.println("Good bye");
                        check_Flag=true;
                        break;
                }
                if(check_Flag){
                    break;
                }
            }
            if(check_Flag){
                break;
            }
        }
    }
}

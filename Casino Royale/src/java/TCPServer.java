
import DataAccess.DataAccessTemplate;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import static java.lang.System.out;
import java.net.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Abhishek Karan
 */
public class TCPServer {

    public static void main(String[] args) {

        try {
            //Scanner sc = new Scanner(System.in);
            ServerSocket ss = new ServerSocket(1121);
            Socket sock = ss.accept();
            DataInputStream dis = new DataInputStream(sock.getInputStream());

            String str = (String) dis.readUTF();
            //System.out.println("Maine bheja:" + str);
            //System.out.println("Enter Message:");

            ApplicationContext contx = new ClassPathXmlApplicationContext("Beans.xml");
            DataAccessTemplate dat = (DataAccessTemplate) contx.getBean("casinoJDBCTemplate");

            int stat = dat.insert(str);
            //str = sc.nextLine();
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            dos.writeUTF(String.valueOf(stat));

            sock.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//main

}//class

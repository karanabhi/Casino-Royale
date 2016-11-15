
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Abhishek Karan
 */
public class TCPClient {

    String name = "";

    public TCPClient(String name) {
        this.name = name;
    }

    public String runCLient() {
        String stat = "";
        try {
            Socket sock = new Socket("localhost", 1121);
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
            //System.out.println("Enter Message:");
            //Scanner sc = new Scanner(System.in);
            //String str = sc.nextLine();
            dos.writeUTF(name);

            DataInputStream dis = new DataInputStream(sock.getInputStream());
            //System.out.println("Client:" + (String) dis.readUTF());
            stat = (String) dis.readUTF();

            dos.flush();
            dos.close();
            sock.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return stat;

    }//main()

}//class

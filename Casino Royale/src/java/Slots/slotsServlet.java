package Slots;

import DataAccess.DataAccessTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Abhishek Karan, Shreyas Hebbar, Elvis D'Souza
 */
public class slotsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet slotsServlet</title>");
            out.println("</head>");
            out.println("<body>");

            /*int num1 = 64 + (int) (Math.floor(Math.random() * 26) + 1);
             int num2 = 64 + (int) (Math.floor(Math.random() * 26) + 1);
             int num3 = 64 + (int) (Math.floor(Math.random() * 26) + 1);
             out.println("num1: " + num1 + " num2: " + num2 + " num3: " + num3);
             char n1 = (char) num1;
             char n2 = (char) num2;
             char n3 = (char) num3;
             out.println("<br/>Char1: " + n1 + " Char2: " + n2 + " Char3: " + n3);
             String randomStr = "" + n1 + n2 + n3;
             out.println("<br/>Random Str: " + randomStr);

             if (randomStr.equals("ICT") || randomStr.equals("SNP") || randomStr.equals("XXX") || randomStr.equals("AJP") || randomStr.equals("NHP")) {
             out.println("<br/>You WON!");
             } else {
             out.println("<br/>You Lost!");
             }

             out.println("<br/><a href='Selector.jsp'>Go Home</a>");
             */
            int x = (int) Math.floor(Math.random() * 5) + 1;

            String SLOT;
            HttpSession session = request.getSession(true);
            int pts = Integer.parseInt(session.getAttribute("u_points").toString()), i;
            pts = -10;
            String[] dic = {"AJP", "ICT", "ANK", "SNP", "SHA", "ELD", "ABK", "ILU", "LOL", "FTW", "WTF", "WTH", "MIT", "KMC", "FCK", "KFC", "MCD", "LTD", "LAB", "CCE", "ECE", "ITB", "IIT", "JEE", "BBT", "GOT", "DBZ", "PKM", "CCD", "CTF", "TCS", "ACC", "SEX", "ASS", "FML", "DEV", "GIT", "CSS", "JSP", "HOD", "DJT", "DOG", "CAT", "RAT", "TST", "XML", "UTF", "TXT", "WIN", "JAR"};

            if (x == 1) {
                x = (int) Math.floor(Math.random() * 50);
                SLOT = dic[x];
                pts += 50;
                //System.out.println("YOU GOT 1/5");
            } else {
                SLOT = "";
                x = (int) Math.floor(Math.random() * 26);
                SLOT += (char) (x + 'A');
                x = (int) Math.floor(Math.random() * 26);
                SLOT += (char) (x + 'A');
                x = (int) Math.floor(Math.random() * 26);
                SLOT += (char) (x + 'A');
                if (SLOT.charAt(0) == SLOT.charAt(1) && SLOT.charAt(0) == SLOT.charAt(2)) {
                    pts += 100;
                    if (SLOT.charAt(0) == 'A' || SLOT.charAt(1) == 'E' || SLOT.charAt(2) == 'S') {
                        pts += 400;
                    }
                } else {
                    for (i = 0; i < 50; i++) {
                        if (SLOT.equals(dic[i])) {
                            pts += 50;
                            //          System.out.println("You got here at " + i);
                            break;
                        }
                    }
                }
            }//Shreyas's code

            ApplicationContext contx = new ClassPathXmlApplicationContext("Beans.xml");
            DataAccessTemplate dat = (DataAccessTemplate) contx.getBean("casinoJDBCTemplate");
            int stat = dat.updatePoint(pts, session.getAttribute("u_id").toString());
            if (stat == 1) {
                session.setAttribute("u_points", pts);
                session.setAttribute("strs", SLOT);
                response.sendRedirect("Slots.jsp");
            } else {
                out.println("Something went wrong...try again!");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

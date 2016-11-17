package Roulette;

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
public class RouletteServlet extends HttpServlet {

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
            out.println("<title>RouletteServlet</title>");
            out.println("</head>");
            out.println("<body>");

            HttpSession session = request.getSession(true);

            /*double num1 = Math.floor(Math.random() * 10) + 1;
             String randomNumber = String.valueOf((int) num1);
             String unum = request.getParameter("unum");
             out.println("Random Number: " + randomNumber + "<br/>User Number:" + unum);
             if (randomNumber.equals(unum)) {
             out.println("<br/>You WON!");
             } else {
             out.println("<br/>You Lost!");
             }
             */
            String res = request.getParameter("res");
            int bet = Integer.parseInt(request.getParameter("bet"));
            int points = Integer.parseInt(session.getAttribute("u_points").toString());
            if (bet > points) {
                session.setAttribute("u_stat", "Bet amount > Current Pot!");
                response.sendRedirect("Selector.jsp");
            } else {
                if (res.equals("yes")) {
                    points += bet * 2;
                } else {
                    points -= bet;
                }

                ApplicationContext contx = new ClassPathXmlApplicationContext("Beans.xml");
                DataAccessTemplate dat = (DataAccessTemplate) contx.getBean("casinoJDBCTemplate");
                int stat = dat.updatePoint(points, session.getAttribute("u_id").toString());
                if (stat == 1) {
                    session.setAttribute("u_points", points);
                    if (res.equals("yes")) {
                        session.setAttribute("u_stat", "You WIN!");
                    } else {
                        session.setAttribute("u_stat", "You LOST!!!");
                    }
                    response.sendRedirect("Selector.jsp");
                } else {
                    out.println("Something went wrong...try again!");
                }
            }
            //out.println(res + " :yolo: " + bet + "::" + points);
            //out.println("<br/><a href='Selector.jsp'>Go Home</a>");
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

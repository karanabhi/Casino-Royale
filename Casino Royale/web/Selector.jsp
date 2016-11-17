<%-- 
    Document   : index
    Created on : Nov 16, 2016, 2:34:53 AM
    Author     : Abhishek Karan
--%>

<%@page import="java.util.List"%>
<%@page import="CasinoPOJO.Casino"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="DataAccess.DataAccessTemplate"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>Choose...</div>
        <br/>
        <a href="BlackJack.jsp">Black Jack</a><br/><br/>
        <a href="Roulette_html.html">Roulette</a><br/><br/>
        <form action="slots" method="post">
            <input type="submit" name="slt" value="Slot Machine"/>
        </form>
        <br/><br/><br/>
        Leader Board:

        <table border="1">
            <tr>
                <th>
                    Position
                </th>
                <th>
                    User Name
                </th>
                <th>
                    Points
                </th>
                <th>
                    Timestamp
                </th>
            </tr>


            <%
                int x = 1;

                ApplicationContext contx = new ClassPathXmlApplicationContext("Beans.xml");
                DataAccessTemplate dat = (DataAccessTemplate) contx.getBean("casinoJDBCTemplate");

                List<Casino> lst = dat.getLeaderboardData();
                for (Casino c : lst) {
            %>
            <tr>            
                <td>
                    <%=x++%>
                </td>
                <td>
                    <%=c.getUname()%>
                </td>
                <td>
                    <%=c.getPoints()%>
                </td>
                <td>
                    <%=c.getTimestamp()%>
                </td>
            </tr>
            <%
                }//for-loop

            %>
        </table>
        <br/><br/><br/>        
        <%            if (session.getAttribute("u_stat") != null) {
        %>
        Status:<%=session.getAttribute("u_stat").toString()%>
        <%
            }
        %>        
        Current Pot:<%=session.getAttribute("u_points").toString()%>
        <%//IF NEEDED
            //    session.invalidate();
%>
        <a href="logout">Exit</a>




    </body>
</html>

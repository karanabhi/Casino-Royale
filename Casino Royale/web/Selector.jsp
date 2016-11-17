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
        <title>Home</title>
    </head>
    <body>
        <style>
            body {
                background: black;
                color: white;
                font-family: sans-serif;
            }
            table {
                border-collapse: collapse;
                border: solid white 1px;
            }

            th, td {
                text-align: left;
                padding: 8px;
            }
            tr:nth-child(even){background-color: #959e1b}
            .btn {
                -webkit-border-radius: 6;
                -moz-border-radius: 6;
                border-radius: 6px;
                font-family: Georgia;
                color: #ffffff;
                font-size: 20px;
                background: rgba(202,214,39,0.8);/*#cad627;*/
                padding: 10px 20px 10px 20px;
                text-decoration: none;
            }

            .btn:hover {
                background: #d3e021;
                text-decoration: none;
            }
            .games {
                margin: 2%;
                margin-bottom: 4%;
            }
            .blinker {
                animation: blinker 1s linear infinite;
            }

            @keyframes blinker {  
                50% { opacity: 0; }
            }
        </style>

    <center>
        <div class="container">
            <span class="letter" style="font-size:70px;"><b>CASINO ROYALE</b><hr></span>
            <div class="blinker" style="font-size:30px;">


                <%            if (session.getAttribute("u_stat") != null) {
                        if (session.getAttribute("u_stat").equals("YOU WIN!")) {
                %>
                <span style="color:green;"><%=session.getAttribute("u_stat").toString()%></span>
                <%} else {
                %> <span  style = "color:red;" ><%=session.getAttribute("u_stat").toString()%></span>
                <%
                        }//inner-if
                    }//outer-if
%>        

            </div>
            <div>CURRENT POT : $ <%=session.getAttribute("u_points").toString()%></div>
        </div>
        <div class="games">
            <h2>Let's play a game!</h2>
            <a href="Roulette_html.html" class="btn">Roulette</a>
            <a href="slots" class="btn">Slot Machine</a>
        </div>
        <h3>LEADERBOARD</h3>
        <table width="30%">
            <tr><th>Rank</th><th>Username</th><th>Pot</th></tr>
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
            </tr>
            <%
                }//for-loop

            %>

        </table>

        <br/><br/>
        <a href="logout" class='btn' style="background:rgba(255,255,255,0.85);color:black;">Exit</a>
    </center>
</body>
</html>

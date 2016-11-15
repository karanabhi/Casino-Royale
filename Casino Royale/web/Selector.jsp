<%-- 
    Document   : index
    Created on : Nov 16, 2016, 2:34:53 AM
    Author     : Abhishek Karan
--%>

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
            <tr>
                <td>
                    2
                </td>
                <td>
                    ABC
                </td>
                <td>
                    123
                </td>
                <td>
                    123dsf12
                </td>
            </tr>
        </table>

    </body>
</html>

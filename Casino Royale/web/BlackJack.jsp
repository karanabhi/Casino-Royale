<%-- 
    Document   : BlackJack
    Created on : Nov 15, 2016, 11:19:07 PM
    Author     : Abhishek Karan, Shreyas Hebbar, Elvis D'Souza
--%>

<%@page import="BlackJack.BJ"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <%
            String num1 = "", num2 = "";
            int num1_num = 0, num2_num = 0, sum = 0;
            String btn = request.getParameter("btn").toString();

            if (btn.equals("hit")) {

            } else if (btn.equals("settle")) {

            } else {

                BJ bj = new BJ();
                bj.generateNum();

                num1 = "" + bj.nums + bj.suite;
                num1_num = bj.nums;

                if (num1_num > 10) {
                    num1_num = 10;
                } else if (num1_num == 1) {
                    num1_num = 11;
                }

                bj.generateNum();

                num2 = "" + bj.nums + bj.suite;
                if (num1.equals(num2)) {
                    bj.generateNum();
                    num2 = "" + bj.nums + bj.suite;
                }//if
                num2_num = bj.nums;

                if (num2_num > 10) {
                    num2_num = 10;
                } else if (num2_num == 1) {
                    num2_num = 11;
                }

                sum = num1_num + num2_num;
                if (sum > 21) {
                    sum = sum - 10;
                }
            }//ITE

        %>
        <br/><br/>
        Cards:        
        <%=num1%>                
        <%=num2%>        
        <br/><br/>
        Sum:        
        <%=sum%>        
        <br/><br/>

        <form method="post" action="/bJack">
            <input type="hidden" value="<%=%>" name="data"/>
            <input type="submit" value="HIT" name="btn"/><br/><br/>
            <input type="submit" value="SETTLE" name="btn"/>
        </form>

    </body>
</html>

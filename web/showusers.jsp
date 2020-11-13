<%-- 
    Document   : showbooks
    Created on : Oct 30, 2010, 1:42:01 AM
    Author     : kifter
--%>

<%@page import="java.util.List"%>
<%@page import= "model.Books" %>
<%@page import= "model.Users" %>
<%@page import= "model.Model" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Show Users </title>
    </head>
    <body>
        <%
                    /* session and  display users */
                    Model p = new model.Model();
                    Users[] users = p.getUsers(); 
                    HttpSession mySess = request.getSession(); 
                    boolean isAdmin = (mySess.getAttribute("admin") != null);/* check if admin logged in*/
                    
        %>
        <h3>Here  is  a list  of    current  users of   the book store </h3>
        <h4><font color="red"><%= request.getAttribute("message") != null ? request.getAttribute("message") : ""%></font></h4>


        <br>

        <form>
            <input type="button" class="button" name="goto" value="  View Books" onclick="document.location='/myBooksManager/showbooks.jsp';" />
        </form>

        <br>

        <table border =  \"1\" >

            <tr><td><strong> UserName</strong> </td><td><strong> Address </strong> </td>


                <%= (isAdmin) ? " <td> <strong>Password</strong> </td><th>Modify& delete</th>" : ""%>
            </tr>


            <div id="users">

                <%

                            for (Users user : users) {



                                out.println("<tr><td>" + user.getName() + "</td>");
                                //out.println(" id:"+user.getName());
                                out.println("<td>" + user.getAddress() + "</td>");



                                if (isAdmin) {
                                    out.println("<td>" + user.getPassword() + "</td>");
                %>
                <td>
                    <form action="ctr" method="post">
                        <input type="hidden" name="id" value="<%=user.getuserId()%>"/>
                        <input type="submit" name="action" value="Modify-Delete2" />
                    </form>
                </td>
                <%
                                }
                                out.println("</tr>");


                            }
                %>
            </div>

        </table>
    </body>
</html>


<%-- 
    Document   : showbooks
    Created on : Oct 30, 2010, 2:16:46 PM
    Author     : kifter
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="model.Books"%>
<%@page import="java.util.List"%>
<%@page import="model.Users"%>
<%@page import="model.Model" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Books</title>
    </head>
    <body>
        <%
            /* Displays  books in  database  */
            Model p = new model.Model();
            List<Books> books = p.fetchAll(); //  fetch all  books  as list from the  database
            HttpSession mySess = request.getSession(); 
            boolean isAdmin = (mySess.getAttribute("admin") != null);// check if admin logged in
            
        %>
        <h1>Welcome  to  our  collection of  Books!</h1>
        <h4><font color="red"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></font></h4>
        
        <br>
        <form>
            <input type="button" class="button" name="goto" value="  View Users" onclick="document.location='/myBooksManager/showusers.jsp';" />
        </form>

        <br>



        <table border =  \"1\" >

            <tr><td><strong> Book Cover</strong> </td><td><strong> Title</strong> </td>
                <td> <strong>Author</strong> </td>
                <td> <strong>Price €</strong> </td>
                <%= (isAdmin) ? "<th>Modify/delete</th>" : ""%>
            </tr>


            <div id="books">

                <%

                            for (Books book : books) {


                               
                                out.println("<tr><td><img src=\"images/" + book.getImage() + "\" alt=\"\" /></td>");

                                out.println("<td>" + book.getTitle() + "</td>");
                                out.println("<td>" + book.getAuthor() + "</td>");

                                DecimalFormat priceFormater = new DecimalFormat("0.00");
                                out.println("<td>" + priceFormater.format(book.getPrice()) + " &euro;</td>");
                                if (isAdmin) {
                %>
                <td>
                    <form action="ctr" method="post">  
                        <input type="hidden" name="bookId" value="<%= book.getBookId()%>"/>
                        <input type="submit" name="action" value="Modify-Delete" />
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





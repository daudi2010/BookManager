<%-- 
    Document   : addbooks
    Created on : Nov 7, 2010, 10:41:15 PM
    Author     : kifter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My  book store</title>
    </head>
    <body>
        <h1>Adding  Books</h1>

        <%
            /* Displays books */

     //   showing  all  books from the  database
            HttpSession mySess = request.getSession(); //  initialize  session
            boolean isAdmin = (mySess.getAttribute("admin") != null);// check if admin logged in
           
        %>

        <% if (isAdmin) {     %>
        <form  action="Controller">
             Title: <input type="text" name="title"/><br>
             <br/>
             Author: <input type="text" name="author"/><br>
            <br/>
             Subject: <input type="text" name="subject"/><br>
            <br/>
             Year of publish: <input type="text" name="year_publish"/><br>
             <br/>
             Count: <input type="text" name="count"/><br>
            <br/>
             Price (€): <input type="text" name="price"/><br>
            <br/>
            Image :(Book Cover) <input type="text" name="image"/><br>
            <br/>

            <input type="submit" value="AddBook" name="action"/>

          
          <input type="button" class="button" name="goto" value="View books" onclick="document.location='/myBooksManager/showbooks.jsp';" />

        </form>
        <% }  else{
         out.print("<font color=\"red\">You  need  to  be logged  in as  admin to add   books</font>");
}     %>







    </body>
</html>

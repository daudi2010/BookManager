<%-- 
    Document   : addusers
    Created on : Nov 7, 2010, 11:48:26 PM
    Author     : kifter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My book Store</title>
    </head>
    <body>
           <%
            /* Displays books */

      //   showing  all  books from the  database
            HttpSession mySess = request.getSession(); //  initialize  session
            boolean isAdmin = (mySess.getAttribute("admin") != null);// check if admin logged in
            
        %>

        <% if (isAdmin) {     %>

        <h1> Add users </h1>

         <p><strong> New  Users  can be added  be  added   using the  form below.    </strong> </p>
        <form  action="Controller">
             Name: <input type="text" name="name"/><br>
             <br/>
             Address: <input type="text" name="address"/><br>
            <br/>
             Password: <input type="text" name="password"/><br>
            <br/>
            <input type="submit" value="Add" name="action"/>

            <input type="submit" value="list" name="action"/>
         <input type="button" class="button" name="goto" value="View users" onclick="document.location='/myBooksManager/showusers.jsp';" />

        </form>
       <% }  else{
         out.print("<font color=\"red\">You  need  to  be logged  in as  admin to add users</font>");
}     %>






    </body>
</html>

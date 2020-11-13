<%-- 
    Document   : index
    Created on : Oct 29, 2010, 7:40:51 PM
    Author     : kifter
--%>

<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>My Book store </title>
    </head>
    <body>

        <form>
            <input type="button" class="button" name="goto" value="  Back to Users" onclick="document.location='/myBooksManager/showusers.jsp';" />
        </form>

        <br>

           <%
            /**test if the user is logged in as admin; if not DON'T show the form!
            The user info should come from request */
           HttpSession mySess = request.getSession(); //  initialize  session
           boolean isAdmin = (mySess.getAttribute("admin") != null);// check if admin logged in
           
         Users user = null;
            try {
               user = (Users)request.getAttribute("user-to-be-modify");
            } catch (Exception e) {
            }
        %>
        
          <% if (isAdmin) {   %>
        <p><strong>Users  info  can  be  edited  here using the  form below.    </strong> </p>
        
        <form  action="Controller">
             Name: <input type="text" name="name" value ="<%=(user != null) ? user.getName() : "" %>"/><br>
             <br/>
             Address: <input type="text" name="address" value ="<%=(user != null) ? user.getAddress() : "" %>"/><br>
            <br/>
             Password: <input type="text" name="password" value ="<%=(user != null) ? user.getPassword() : "" %>"/><br>
            <br/>
            <br/>
            <input type="hidden" name="name" value="<%=(user != null) ? user.getName() : -1 %>"/>
            <input type="hidden" name="id" value="<%=user.getuserId() %>"/>
             <input type="button" class="button" name="goto" value="Add Users" onclick="document.location='/myBooksManager/addusers.jsp';" />

            <input type="submit" value="Delete" name="action"/>
            <input type="submit" value="Update" name="action"/>
           
         <input type="button" class="button" name="goto" value="View users" onclick="document.location='/myBooksManager/showusers.jsp';" />

        </form>
            <% }  else{
         out.print("<font color=\"red\">You  need  to  be logged  in as  admin to edit  users</font>");
}     %>


    </body>
</html>

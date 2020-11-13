<%@ page import=" model.Users" %>
<%@ page import=" model.Model" %>

<%-- 
    Document   : login
    Created on : Oct 25, 2010, 11:56:54 PM
    Author     : kifter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BookStore Login</title>
   	
</head>
<body>

<h1>Book Store</h1>


<%
	/* Retreives user data */
	Users user = (Users)session.getAttribute("user");
	if(user instanceof Users) {

%>
<form>
	<input type="button" class="button" name="goto" value="Back" onclick="document.location='/myBooksManager/';" />
</form>
<%
	} else {
	/* Displays login form */
%>

 <form>

            <input type="button" class="button" name="goto" value="  View Books" onclick="document.location='/myBooksManager/showbooks.jsp';" />
            <input type="button" class="button" name="goto" value="  View Users" onclick="document.location='/myBooksManager/showusers.jsp';" />

        </form>
  <br>

<form action="ctr">
	<input type="hidden" name="page" value="Login" />
        <p><strong>Please Login  here</strong> </p>
         <table>
           
           <tr>
			<td>Name:</td>
			<td><input type="text" name="userName" value="" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="userPassword" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:right;">
			<input type="submit" class="button" name="action" value="Login" />
			<input type="button" class="button" name="goto" value="Cancel" onclick="document.location='/myBooksManager/showbooks.jsp';" />
			</td>
		</tr>
	</table>
</form>
<%
	}
%>

</body>
</html>
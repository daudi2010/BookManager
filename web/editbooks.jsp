<%-- 
    Document   : editbooks
    Created on : Oct 31, 2010, 2:41:40 AM
    Author     : kifter
--%>

<%@page import="model.Books"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Book Store </title>
    </head>
    <body>

        <form>
            <input type="button" class="button" name="goto" value="  Back to Books" onclick="document.location='/myBooksManager/showbooks.jsp';" />
        </form>
        <br>
        <h1>Editing  Books  Entries </h1>
        <%
            //test if the user is logged in as admin; if not DON'T show the form!
            //The book info should come from request
           HttpSession mySess = request.getSession(); 
           boolean isAdmin = (mySess.getAttribute("admin") != null);// check if admin logged in


            Books b = null;
            try {
                b = (Books)request.getAttribute("book");
            } catch (Exception e) {
            }
        %>
         <% if (isAdmin) {     %>
        <p><strong>Information about  books  can  be  edited  here using the  form below.    </strong> </p>
        <form  action="Controller">
            Title: <input type="text" name="title" value="<%=(b != null) ? b.getTitle() : "" %>"/><br>
            <br/>
            Author: <input type="text" name="author" value="<%=(b != null) ? b.getAuthor() : "" %>"/><br>
            <br/>
            Subject: <input type="text" name="subject" value="<%=(b != null) ? b.getSubject() : "" %>"/><br>
            <br/>
            Year Published: <input type="text" name="year_publish" value="<%=(b != null) ? b.getYearPublish() : "" %>"/><br>
            <br/>
            Price (€): <input type="text" name="price" value="<%=(b != null) ? b.getPrice() : "" %>"/><br>
            <br/>
            Quantity: <input type="text" name="count"value="<%=(b != null) ? b.getCount() : "" %>" /><br>
            <br/>
            Book Cover(image): <input type="text" name="image" value="<%=(b != null) ?  b.getImage() : "" %>" /><br>
            <br/>

            <input type="hidden" name="book_id" value="<%=(b != null) ? b.getBookId() : -1 %>"/>
           <input type="button" class="button" name="goto" value="Add books" onclick="document.location='/myBooksManager/addbooks.jsp';" />
            <input type="submit" value="DeleteBook" name="action"/>
            <input type="submit" value="UpdateBook" name="action"/>
            <input type="button" class="button" name="goto" value="View Books" onclick="document.location='/myBooksManager/showbooks.jsp';" />

        </form>
        <% }  else{
         out.print("<font color=\"red\">You  need  to  be logged  in as  admin to edit   books</font>");
}     %>




    </body>
</html>

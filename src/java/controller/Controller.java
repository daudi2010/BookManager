
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import model.Users;
import model.Books;

/**
 *
 * @author  david  kanyari
 */
@WebServlet(urlPatterns = {"/Controller", "/ctr"})
public class Controller extends HttpServlet {

    private Model p; //  instance   of  model
    private String name;
    private Users k;
    private Books book;
    private String address;
    private String password;

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        HttpSession Mysession = request.getSession();
        p = new model.Model();
       
        try {
            if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("add")) {
               
                name = request.getParameter("name");
                address = request.getParameter("address");
                password = request.getParameter("password");

                out.println("<h2>HELLO " + name + "</h2>");

               
                //   add  a  user  to  database
                k = new Users();
                k.setAddress(address);
                k.setName(name);
                k.setPassword(password);
                p.addUser(k);


                out.println("Added to database :)");

                RequestDispatcher rd = request.getRequestDispatcher("/showusers.jsp");
                rd.forward(request, response);
                out.println("Added to database :)");
                
            } // delete user

            else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("delete")) {
               
                try {
                    if (p.deleteUsers(Integer.parseInt(request.getParameter("id")))) {
                        request.setAttribute("message", "Successfully deleted user");
                    } else {
                        request.setAttribute("message", "FAILED  to delete user, try again! :(");
                    }
                } catch (Exception e) {
                    request.setAttribute("message", "Exception delete user " + e);
                }
                RequestDispatcher rd = request.getRequestDispatcher("/showusers.jsp");
                rd.forward(request, response);

            }

            //  Update  users
            else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("update")) {
                

                try { //  get  user    attributes  from request
                    name = request.getParameter("name");
                    address = request.getParameter("address");
                    password = request.getParameter("password");
                    
                    Users ku = new Users();
                    ku.setuserId(Integer.parseInt(request.getParameter("id")));
                    ku.setAddress(address);
                    ku.setName(name);
                    ku.setPassword(password);
                    p.updateUsers(ku);
                    request.setAttribute("message", "User updated   successfully");
                } catch (Exception e) {
                    request.setAttribute("message", "Exception updating user " + e);
                }

                RequestDispatcher rd = request.getRequestDispatcher("/showusers.jsp");
                rd.forward(request, response);
                

           } else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("login")) {
              
                RequestDispatcher rd;
                try { 
                    String userName = (String) request.getParameter("userName");
                    String userPassword = (String) request.getParameter("userPassword");
                  
                    if (p.login(userName, userPassword)) { 
                       
                        rd = request.getRequestDispatcher("/showbooks.jsp");
                        if (userName.equalsIgnoreCase("admin") && userPassword.equalsIgnoreCase("admin")) {
                            Mysession.setAttribute("admin", "YES");
                        }
                        rd.forward(request, response);
                    } else {
                       
                       
                        request.setAttribute("Problem", "login failed");
                        rd = request.getRequestDispatcher("/index.jsp");
                        rd.forward(request, response);
                    }
                } catch (Exception e) {
                   
                    
                    request.setAttribute("Severe  problem", "login failed  again: " + e);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

      
              /**handle  modify delete  users**/
            } else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("Modify-Delete2")) {
                
              
                Users us = p.getUser(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("user-to-be-modify", us);
                RequestDispatcher rd = request.getRequestDispatcher("/editusers.jsp"); //  send  user  to     edit  books  page
                rd.forward(request, response);

              /**  handle   modify or  delete  books   **/
            } else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("Modify-Delete")) {
               
                request.setAttribute("book", p.getBook(Integer.parseInt(request.getParameter("bookId"))));
                RequestDispatcher rd = request.getRequestDispatcher("/editbooks.jsp"); //  send  user  to     edit  books  page
                rd.forward(request, response);

                /** delete  a book **/
            } else if (request.getParameter("action") != null && request.getParameter("action").equalsIgnoreCase("DeleteBook")) {
                try {
                    int id = Integer.parseInt(request.getParameter("book_id"));
                    if (p.deleteBooks(id)) {
                        request.setAttribute("message", "Successfully deleted book");
                    } else {
                        request.setAttribute("message", "Failed to delete book :(");
                    }
                } catch (Exception e) {
                    request.setAttribute("message", "Exception delete book :( " + e);
                }
                RequestDispatcher rd = request.getRequestDispatcher("/showbooks.jsp");
                rd.forward(request, response);


            } 
            /** add  a book or  update  book**/

            else if (request.getParameter("action") != null && (request.getParameter("action").equalsIgnoreCase("addBook") || request.getParameter("action").equalsIgnoreCase("UpdateBook"))) {


                String title = request.getParameter("title");
                String subject = request.getParameter("subject");
                String author = request.getParameter("author");
                int year_publish = Integer.parseInt(request.getParameter("year_publish"));
                int count = Integer.parseInt(request.getParameter("count"));
                String image = request.getParameter("image");
                Double price = Double.parseDouble(request.getParameter("price"));
                int id = -1;
                  

                try {
                    id = Integer.parseInt(request.getParameter("book_id"));
                } catch (Exception e) {
                }

                try {
                    book = new Books();
                    book.setAuthor(author);
                    book.setPrice(BigDecimal.valueOf(price));
                    book.setTitle(title);
                    book.setYearPublish(year_publish);
                    book.setCount(count);
                    book.setImage(image);
                    book.setSubject(subject);
                    if (id == -1) {
                        if((title == null)||(subject==null)||(author==null)){

                      request.setAttribute("message", "You have empty  field(s),no book added :(");
                      }else{

                   
                        p.insertBooks(book); // add book to data base
                        request.setAttribute("message", "Book added success");
                    }

                    }else {
                        book.setBookId(id);
                        if (p.updateBooks(book)) {
                            request.setAttribute("message", "Book update success");
                        } else {
                            request.setAttribute("message", "Book update failed :(");
                        }
                    }


                } catch (Exception e) {
                    request.setAttribute("message", "Exception in inserting book :( " + e);
                }
       
                RequestDispatcher rd = request.getRequestDispatcher("/showbooks.jsp");
                rd.forward(request, response);

            } 

         

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author David Kanyari
 */
public class Model {

    private static Connection con = null;
    private ResultSet rset = null;
    private PreparedStatement pstmt = null;
    private String dbName = "your db"; 
    private String username = "your username";
    private String pwd = "your strong password";

    private void init() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, pwd);
            }
        } catch (ClassNotFoundException e) {
            throw new Exception("Problem at connection", e);
        } catch (SQLException e) {
            throw new Exception("Problem at connection", e);
        }
    }

    private void close() throws Exception {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rset != null) {
                rset.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new Exception("Problem closing connection", e);
        }
    }

            
     //  get all users
    
     public Users[] getUsers() throws Exception {
        try {
            init();
            pstmt = con.prepareStatement("SELECT * FROM Users  " );
            rset = pstmt.executeQuery();
            ArrayList<Users> ps = new ArrayList<Users>();
            Users p;
            while (rset.next()) {
                p = new Users();
                p.setuserId(rset.getInt("user_id"));
                p.setName(rset.getString("name"));
                p.setPassword(rset.getString("password"));
                p.setAddress(rset.getString("address"));

                ps.add(p);
            }
            close();
            return ps.toArray(new Users[ps.size()]);
        } catch (SQLException ex) {
            throw new Exception("Query problem", ex);
        }
    }

     // ordering  by name
    public Users[] getUsers(String name) throws Exception {
        try {
            init();  // connect to  data  base
            pstmt = con.prepareStatement("SELECT * FROM Users ORDER BY " + name);
            rset = pstmt.executeQuery();
            ArrayList<Users> ps = new ArrayList<Users>();
            Users p;
            while (rset.next()) {
                p = new Users();
                p.setName(rset.getString("name"));
                p.setPassword(rset.getString("password"));
                p.setAddress(rset.getString("address"));

                ps.add(p);
            }
            close();
            return ps.toArray(new Users[ps.size()]);
        } catch (SQLException ex) {
            throw new Exception("Query problem", ex);
        }
    }

       // get   just one user  by   user_id
     public Users getUser(int id) throws Exception {

        init(); //  connect  to  database

        PreparedStatement st2 = con.prepareStatement("SELECT * FROM Users WHERE user_id = " + id);

        ResultSet rs = st2.executeQuery();
        rs.first();
        Users user = new Users(rs.getInt("user_id"), rs.getString("name"), rs.getString("address"), rs.getString("password"));

        close();
        return user;

    }

       // add  new  user   to  data base


    public boolean addUser(Users p) throws Exception {
        try {
            init();

            pstmt = con.prepareStatement("INSERT INTO Users (name, address, password) VALUES (?, ?, ?)");
            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getAddress());
            pstmt.setString(3, p.getPassword());

            return pstmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new Exception("Query problem", ex);
        }

    }

    //  delete  user  

    public boolean deleteUsers(int id) throws Exception {
      
            PreparedStatement st = con.prepareStatement(
                "DELETE FROM Users WHERE user_id = " + id);
       
            int num = st.executeUpdate();
            return (num != 0);
    }


    //update users  
    public void updateUsers(Users usr) throws Exception {
        init();
       
        PreparedStatement st = con.prepareStatement(
                "UPDATE Users SET name= ?, password= ?, address= ? " + " WHERE user_id=" + usr.getuserId());
      
        st.setString(1, usr.getName());
        st.setString(2,usr.getPassword());
        st.setString(3,usr.getAddress());
        System.out.println("the Query: " + st);
        int num = st.executeUpdate();
       
    }

    
    // update  book
     public boolean updateBooks(Books  book ) throws Exception {
        init();
       
        PreparedStatement st = con.prepareStatement(
                "UPDATE Books SET title=?, subject=? ,author=?, year_publish=?, count=?, price=?, image=?" +" WHERE book_id=" + book.getBookId());

   
        st.setString(1, book.getTitle());
        st.setString(2, book.getAuthor());
        st.setString(3, book.getSubject());
        st.setInt(4, book.getYearPublish());
        st.setInt(5, book.getCount());
        st.setBigDecimal(6, book.getPrice());
         st.setString(7, book.getImage());
        int num = st.executeUpdate();
        return (num != 0);
    }

     // inserting books  or adding  new  book
    public int insertBooks(Books book) throws Exception {

        init(); //  connect to data base
        PreparedStatement st = con.prepareStatement(
                "INSERT INTO Books (title, author, subject, year_publish, count, price) VALUES (?, ?, ?, ?, ?, ?)");

        st.setString(1, book.getTitle());
        st.setString(2, book.getAuthor());
        st.setString(3, book.getSubject());
        st.setInt(4, book.getYearPublish());
        st.setInt(5, book.getCount());
        st.setBigDecimal(6, book.getPrice());
         st.executeUpdate();

        ResultSet rs = st.executeQuery("select last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }

    //  delete   books.........  delete  by title
    public boolean deleteBooks(int id) throws Exception {
        init();
        PreparedStatement st = con.prepareStatement(
                "DELETE FROM Books WHERE book_id = " + id);
       
        int num = st.executeUpdate();
        return (num != 0);
    }

    //   fetch  all  books
    public List<Books> fetchAll() throws Exception {

        init(); //  connect  to  database

        PreparedStatement st2 = con.prepareStatement("SELECT * FROM Books");

        ResultSet rs = st2.executeQuery();

        List<Books> L = new LinkedList<Books>();
        while (rs.next()) {
            int id = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String subject = rs.getString("subject");
            int yearPublish = rs.getInt("year_publish");
            String image = rs.getString("image");
            int count = rs.getInt("count");
            BigDecimal Price = rs.getBigDecimal("price");
            Books book = new Books(id, title, author, subject, yearPublish, count, Price, image);
            L.add(book);

        }
        close();
        return L;

    }

     public Books getBook(int id) throws Exception {

        init(); //  connect  to  database

        PreparedStatement st2 = con.prepareStatement("SELECT * FROM Books WHERE book_id = " + id);

        ResultSet rs = st2.executeQuery();
        rs.first();
        Books book = new Books(rs.getInt("book_id"), rs.getString("title"), rs.getString("author"), rs.getString("subject"), rs.getInt("year_publish"), rs.getInt("count"), rs.getBigDecimal("price"), rs.getString("image"));

        close();
        return book;

    }

    public boolean login(String username, String password) throws Exception {


        init();
     
        //alternative to loop: add the WHERE clause in select query... Then, empty result means no login, at least one result, login ok.
        PreparedStatement st2 = con.prepareStatement("select * from Users");

        ResultSet rs = st2.executeQuery();
       
        while (rs.next()) {
            if (username.equalsIgnoreCase(rs.getString("name")) && (password.equalsIgnoreCase(rs.getString("password")))) {

               return true;
            } else {

                System.out.println("Bad  login?");

            }
        }
        return false;

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 *
 * @author kifter
 */
@Entity
@Table(name = "Books")
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByAuthor", query = "SELECT b FROM Books b WHERE b.author = :author"),
    @NamedQuery(name = "Books.findBySubject", query = "SELECT b FROM Books b WHERE b.subject = :subject"),
    @NamedQuery(name = "Books.findByYearPublish", query = "SELECT b FROM Books b WHERE b.yearPublish = :yearPublish"),
    @NamedQuery(name = "Books.findByCount", query = "SELECT b FROM Books b WHERE b.count = :count"),
    @NamedQuery(name = "Books.findByPrice", query = "SELECT b FROM Books b WHERE b.price = :price"),
    @NamedQuery(name = "Books.findByImage", query = "SELECT b FROM Books b WHERE b.image = :image")})

    public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    @Column(name = "book_id")
    private Integer bookId;
   
    @Column(name = "title")
    private String title;
    
    @Column(name = "author")
    private String author;
  
    @Column(name = "subject")
    private String subject;
   
    @Column(name = "year_publish")
    private int yearPublish;
    
    @Column(name = "count")
    private int count;
   
    @Column(name = "price")
    private BigDecimal price;
  
    @Column(name = "image")
    private String image;
    private String table_name = "books";
   

    public Books() {
    }

    public Books(Integer bookId) {
        this.bookId = bookId;
    }

    public Books(Integer bookId, String title, String author, String subject, int yearPublish, int count, BigDecimal price, String image) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.yearPublish = yearPublish;
        this.count = count;
        this.price = price;
        this.image = image;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getYearPublish() {
        return yearPublish;
    }

    public void setYearPublish(int yearPublish) {
        this.yearPublish = yearPublish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "books.Books[bookId=" + bookId + "]";
    }


//   Add a getter and setter for the class.

/**
 * Set the properties
 * @param book
 */
public void setBook(Books book){
	this.setBookId(book.getBookId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setSubject(book.getSubject());
	this.setYearPublish(book.getYearPublish());
        this.setCount(book.getCount());
	this.setImage(book.getImage());
	

}

/**
 * @return books object
 */
 public Books getBook(){

	return new Books (this.getBookId(),
	this.getTitle(),
        this.getAuthor(),
        this.getSubject(),
        this.getYearPublish(),
        this.getCount(),
        this.getPrice(),
        this.getImage()
        );

    
}
}

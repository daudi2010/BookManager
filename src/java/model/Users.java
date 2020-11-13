/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

/**
 *
 * @author kifter
 */
@Entity
@Table(name = "Users")

public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
     private Integer user_id;
  
    
    @Column(name = "name")
    private String name;
   
    @Column(name = "address")
    private String address;
   
    @Column(name = "password")
    private String password;

    public Users() {
    }

    

    public Users(Integer user_id, String name, String address, String password) {
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.password = password;
    }

  
   public int getuserId() {
        return (int)user_id;
    }


    public void setuserId(int user_id) {
        this. user_id=user_id;
    }
   
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    @Override
    public String toString() {
        return "books.Users[user-id=" + user_id + "]";
    }

}

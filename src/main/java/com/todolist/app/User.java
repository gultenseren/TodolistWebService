/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todolist.app;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GultenSeren
 */
@XmlRootElement(name="todolist")
public class User implements Serializable{
    
	
    int user_id;
    String user_name,user_password,user_email;
    

    public User() {
    }
    
    

    public User(int user_id, String user_name, String user_password, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
    }
    

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public String getUser_email() {
        return user_email;
    }
    
    
    
    @XmlElement
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @XmlElement
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @XmlElement
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @XmlElement
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    
    
}

package com.todolist.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	Connection con = null;
	public UserRepository()  {
		try {
			 
			 con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/todolist","root","");  

		  

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
        
        
	//GET ALL USERLIST----------------------------------------------------------------------------------
		public List<User> getAllUserList()
		{		
			List<User> userlist = new ArrayList<>();
			String sql = "Select * from users";
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					User s = new User();
					s.setUser_id(rs.getInt(1));
					s.setUser_name(rs.getString(2));				
					s.setUser_password(rs.getString(3));
					s.setUser_email(rs.getString(4));
					
					
					userlist.add(s);
				}
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			return userlist;
		}
		
		//GET SPECIFIC USER ----------------------------------------------------------------------------------
	        public User getUser(String name)
		{		
			User user = new User();
			String sql = "Select * from users where user_name='"+name+"'";
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					
					user.setUser_id(rs.getInt(1));
					user.setUser_name(rs.getString(2));				
					user.setUser_password(rs.getString(3));
	                user.setUser_email(rs.getString(4));
					
					
				}
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			return user;
		}
	        
	        
	        
	        //ADD USER--------------------------------------------------------------------------------------------
	        public String createUser(User user) {
	            
			String sql = "insert into users (user_name , user_password , user_email) VALUES (?,?,?)";
			try {
				PreparedStatement st = con.prepareStatement(sql);
	                        
				
				st.setString(1, user.getUser_name());
				st.setString(2, user.getUser_password());
				st.setString(3, user.getUser_email());
				
			 int result = st.executeUpdate();
	                            
	                            
	                            if(result==1){return "success";}
					
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
	                        
	                        return "faill";
		}
	        
	        
	        //UPDATE USER -------------------------------------------------------------------------------------
	        
	        public void updateUser(User user) {
				String sql = "update users set user_id=?,user_name=?,user_password=?,user_email=?,";
				try {
					PreparedStatement st = con.prepareStatement(sql);
					
					st.setInt(1, user.getUser_id());
					st.setString(2, user.getUser_name());
					st.setString(3, user.getUser_password());
					st.setString(4, user.getUser_email());
					
					
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				
			}
	        
	}
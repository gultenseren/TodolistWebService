package com.todolist.app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GultenSeren
 */
public class ListNameRepository {
    
	Connection con = null;
	public ListNameRepository()  {
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
        
    
           
        public List<ListName> getAllListName()
	{		
		List<ListName> list = new ArrayList<>();
		String sql = "Select * from listnames";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ListName listName = new ListName();
                                
				listName.setList_id(rs.getInt(1));
                                listName.setUser_id(rs.getInt(2));
				listName.setList_name(rs.getString(3));
            
				list.add(listName);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}
        
        
        //----------------------------------------------------------------------------------------
        //Get Specific list
        
         public List<ListName> getListofUser(int id)
	{		
		List<ListName> list = new ArrayList<>();
		String sql = "Select * from listnames where user_id =" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ListName listName = new ListName();
                                
				listName.setList_id(rs.getInt(1));
                                listName.setUser_id(rs.getInt(2));
				listName.setList_name(rs.getString(3));
            
				list.add(listName);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}
         
         
          //ADD List--------------------------------------------------------------------------------------------
        public String createList(ListName list) {
            int result=0;
             int candidateId = 0;
		String sql = "insert into listnames(user_id,list_name) values (?,?)";
                
                
		try {
			PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        
                        
			st.setInt(1,list.getUser_id());
			st.setString(2, list.getList_name());
			
		
		  result = st.executeUpdate();
                  
                  
                                       
                      ResultSet rs = st.getGeneratedKeys();
                      if(rs.next())
                         candidateId = rs.getInt(1);
                            
                            
                            if(result==1){return ""+candidateId ;}
				
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			
                        
                        return "faill"+candidateId;
		
	}
        
        
              //DELETE Listname from DB
	public String removeList(ListName listname) {
		String sql = "delete from listnames where list_id=? and user_id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
                        
			st.setInt(1,listname.getList_id());
                        st.setInt(2,listname.getUser_id());
			
		 int result = st.executeUpdate();
         
                            if(result==1){return "delete success";}
				
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			
                        
                        return "delete faill";
		
	}
		
        public List<ListName> getLastID(){
        
          List<ListName> list = new ArrayList<>();
            String sql = "SELECT * FROM listnames ORDER BY list_id DESC LIMIT 1";
		try {
			PreparedStatement st = con.prepareStatement(sql);
                        ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				ListName listName = new ListName();
                                
				listName.setList_id(rs.getInt(1));
                                listName.setUser_id(rs.getInt(2));
				listName.setList_name(rs.getString(3));
            
				list.add(listName);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
                        
                     
		  
        }
	
}

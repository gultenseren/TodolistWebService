package com.todolist.app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.lang.System.console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 *
 * @author GultenSeren
 */
@Repository
public class TaskRepository {
    
	
	Connection con = null;
	public TaskRepository()  {
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
        
        public List<Task> getAllTasks()
	{		
		List<Task> tasklist = new ArrayList<>();
		String sql = "Select * from tasks";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Task task = new Task();
				task.setTask_id(rs.getInt(1));
				task.setList_id(rs.getInt(2));
                                task.setTask_name(rs.getString(3));
                                task.setTask_description(rs.getString(4));
                                task.setTask_deadline(rs.getString(5));
                                task.setTask_status(rs.getString(6));
                            
				tasklist.add(task);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return tasklist;
	}
        
        
        //---------------------------------------------------------------------
        
        //Get Specific Task
        
           public List<Task> getTaskofList(int list_id)
	{		
		List<Task> tasklist = new ArrayList<>();
		String sql = "Select * from tasks where list_id="+list_id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Task task = new Task();
				task.setTask_id(rs.getInt(1));
				task.setList_id(rs.getInt(2));
                                task.setTask_name(rs.getString(3));
                                task.setTask_description(rs.getString(4));
                                task.setTask_deadline(rs.getString(5));
                                task.setTask_status(rs.getString(6));
                            
				tasklist.add(task);
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return tasklist;
	}
        
        //---------------------------------------------------------------------
        
        
           //UPDATE INTO DB
		public String updateTask(Task task) {
                    
			String sql = "update tasks set task_id=?, list_id=?, task_name=?, task_description=?, "+
                                "task_deadline=?, task_status=? where task_id=?";
			try {
				
                            PreparedStatement st = con.prepareStatement(sql);
                            
                            st.setInt(1,task.getTask_id());
                            st.setInt(2,task.getList_id());
                            st.setString(3, task.getTask_name());
                            st.setString(4, task.getTask_description());
                            st.setString(5, task.getTask_deadline());
                            st.setString(6, task.getTask_status());
                            st.setInt(7,task.getTask_id());
                            
                            int result = st.executeUpdate();
                            
                            
                            if(result==1){return "success";}
				
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			
                        
                        return "faill" + task.getTask_name();
		}
                
        
        //-----------------------------------------------------------------------------------------------------------
        //INSERT INTO DB
	public String insertTask(Task task) {
            int result =0;
             int candidateId = 0;
             String a="";
             
		String sql = "insert into tasks (list_id,task_name,task_description,task_deadline,task_status) VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                        
			
				st.setInt(1,task.getList_id());
				st.setString(2, task.getTask_name());
				st.setString(3, task.getTask_description());
				st.setString(4, task.getTask_deadline());
	            st.setString(5, task.getTask_status());
                        
			 result = st.executeUpdate();
                        
                         ResultSet rs = st.getGeneratedKeys();
                      if(rs.next())
                         candidateId = rs.getInt(1);
                            
                            
                     if(result==1){return "olduuu" ;}
				
			}
			catch(Exception ex) {
				a=ex.toString()+" hatası";
			}
			
                        
                        return "faillll "+a;
	}
        
        
        
          //DELETE INTO DB
	public String removeTask(int id) {
		String sql = "delete from tasks where task_id="+id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
		 int result = st.executeUpdate();
                            
                            
                            if(result==1){return "success";}
				
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
			
                        
                        return "faill";
		
	}
    
}

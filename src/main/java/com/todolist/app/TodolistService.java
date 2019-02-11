package com.todolist.app;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path="/todolistservice")
@CrossOrigin(origins = {"*"})
public class TodolistService {
	
	@Autowired
	UserRepository userRepository = new UserRepository();
	ListNameRepository listNameRepository = new ListNameRepository();
	TaskRepository taskRepository = new TaskRepository();
	
	
  
	
	@RequestMapping(path="/userlist", method = RequestMethod.GET, headers="Accept=application/json")
	@CrossOrigin(origins = {"*"})
    @ResponseBody
    public List<User> displayUser(){
        
        return userRepository.getAllUserList();  
        
    }
    
    
    @RequestMapping( path = "/user/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public User getUserr(@RequestBody @PathVariable("name") String name){
        
        return userRepository.getUser(name);  
        
    }
    
    
    @RequestMapping( path = "/createuser", method = RequestMethod.POST, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String createUser(@RequestBody User user){
        
        return userRepository.createUser(user);  
        
    }
    
    //----------------------------------------------------------------------------------------------------
    
    
    @RequestMapping( path = "/lists", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public List<ListName> displayListNames(){
    
        return listNameRepository.getAllListName();
    }
    
    
    @RequestMapping( path = "/listofuser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
     public List<ListName> getListofUser(@RequestBody @PathVariable("id") int id){
    
        return listNameRepository. getListofUser(id);
    }
    
    
    @RequestMapping( path = "/createlist", method = RequestMethod.POST, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String createList(@RequestBody ListName list){
    
        return listNameRepository.createList(list);
    }
    
  
    @RequestMapping( path = "/getlastid", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public List<ListName> getLastID(){
    
        return listNameRepository.getLastID();
    }
    
    
    @RequestMapping( path = "/deletelist", method = RequestMethod.PUT, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String removeList(@RequestBody ListName list){
    
        return listNameRepository.removeList(list);
    }
    
    //--------------------------------------------------------------------------------------------------------
    
    
    @RequestMapping( path = "/tasklist", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public List<Task> displayTasks(){
    
        return taskRepository.getAllTasks();
    }
    
    
    @RequestMapping( path = "/taskoflist/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public List<Task> getTaskofList(@RequestBody @PathVariable("id") int id){
    
        return taskRepository.getTaskofList(id);
    }
    
    
    @RequestMapping( path = "/updatetask", method = RequestMethod.PUT, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String updateTask(@RequestBody Task task) {
    
        return taskRepository.updateTask(task);
    }
    
    
    @RequestMapping( path = "/removetask/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String removeTask(@RequestBody @PathVariable("id") int id){
    
        return taskRepository.removeTask(id);
    }
    
    
    @RequestMapping( path = "/inserttask", method = RequestMethod.POST , headers = "Accept=application/json")
    @CrossOrigin(origins = {"*"})
    @ResponseBody
    public String insertTask(@RequestBody  Task task) {
    		
    	
        return taskRepository.insertTask(task);
    }

    //--------------------------------------------------------------------------------------------------------

}

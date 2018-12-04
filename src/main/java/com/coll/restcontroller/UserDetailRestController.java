package com.coll.restcontroller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.UserDetailDAO;
import com.coll.Model.UserDetail;

@RestController
public class UserDetailRestController 
{
  @Autowired
  UserDetailDAO userDetailDAO;
  @PostMapping("/registerUser")
  public ResponseEntity<String> registerUser(@RequestBody UserDetail userDetail)
  {
	  
	 
	  if(userDetailDAO.registerUser(userDetail))
		  return new ResponseEntity <String>("ADDED",HttpStatus.OK);
	  else
	  
	return new ResponseEntity<String>("FAILURE",HttpStatus.INTERNAL_SERVER_ERROR);
	  
  }
  @PostMapping("/checkUser")
  public ResponseEntity<UserDetail> checkUser(@RequestBody UserDetail userDetail, HttpSession session)
  {
	  UserDetail userDetail1=userDetailDAO.checkUserValidation(userDetail.getLoginname(),userDetail.getPassword());
	  if(userDetail!=null)
	  {
		 session.setAttribute("userDetail", userDetail1); 
		 return new ResponseEntity<UserDetail>(userDetail1,HttpStatus.OK);
	  }
	  else
	  {
		  return new ResponseEntity<UserDetail>(userDetail,HttpStatus.OK);
	  }
	
  }
  @PutMapping("/updateUser/{loginname}")
  public ResponseEntity <String> updateUser(@PathVariable("loginname") String loginname)
  {
	  UserDetail userDetail=(UserDetail)userDetailDAO.getUser(loginname);
	  
	return null;
	  
  }
}

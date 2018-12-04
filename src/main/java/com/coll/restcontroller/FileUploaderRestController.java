package com.coll.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.coll.DAO.ProfilePictureDAO;
import com.coll.Model.ProfilePicture;
import com.coll.Model.UserDetail;

@RestController
public class FileUploaderRestController {
 @Autowired
 ProfilePictureDAO profilePictureDAO;
 @RequestMapping(value="/doUpload",method=RequestMethod.POST)
 public ResponseEntity<?>uploadProfilePicture(@RequestParam(value="file") CommonsMultipartFile fileUpload,HttpSession session)
 {
	UserDetail userDetail=(UserDetail)session.getAttribute("userDetail"); 
	System.out.println("File Bytes Length:"+fileUpload.getBytes().length);
	if(userDetail==null)
	{
		return new ResponseEntity<String>("Unauthorized User",HttpStatus.NOT_FOUND);
	}
	else
	{
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setLoginname(userDetail.getLoginname());
		profilePicture.setImage(fileUpload.getBytes());
		profilePictureDAO.save(profilePicture);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
 }
 @RequestMapping(value="/getImage/{loginname}",method=RequestMethod.GET)
 public @ResponseBody byte[] getProfilePicture(@PathVariable("loginname")String loginname,HttpSession session)
 {
	 UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
	 if(userDetail==null)
	 {
		 return null;
	 }
	 else
	 {
		 ProfilePicture profilePicture=profilePictureDAO.getProfilePicture(loginname);
		if(profilePicture!=null)
		{
			return profilePicture.getImage();
		}
		else
		{
			return null;
		}
	 }
 }
}

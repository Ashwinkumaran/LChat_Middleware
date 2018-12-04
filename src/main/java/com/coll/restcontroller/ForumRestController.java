package com.coll.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.ForumDAO;
import com.coll.Model.Blog;
import com.coll.Model.Forum;

@RestController
public class ForumRestController 
	{
      @Autowired
      ForumDAO forumDAO;
      @GetMapping("/showAllForums")
      public ResponseEntity<List<Forum>> showAllForums()
      {
         List<Forum> listForums=forumDAO.listForums();
         if(listForums.size()>0)
 			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
 		else
 			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.INTERNAL_SERVER_ERROR);
      }
      @PostMapping("/addForum")
      public ResponseEntity<String> addForum(@RequestBody Forum forum)
      {
    	  forum.setCreateDate(new Date());
    	  forum.setLoginname("ashwin");
          forum.setStatus("NA");
          if(forumDAO.addForum(forum))
          
        	  return new ResponseEntity<String>("FORUM ADDED",HttpStatus.OK);
          else
        	return new ResponseEntity<String>("FAILURE",HttpStatus.INTERNAL_SERVER_ERROR); 
          
          
      }
      @GetMapping("/getForum/{forumId}")
		public ResponseEntity<Forum>getAForum(@PathVariable("forumId") int forumId)
		{
			Forum forum=(Forum)forumDAO.getForum(forumId);
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
      @GetMapping("/deleteForum/{forumId}")
  	public ResponseEntity<String> deleteForum(@PathVariable("forumId")int forumId)
  	{
  		Forum forum=(Forum)forumDAO.getForum(forumId);
  		if(forumDAO.deleteForum(forum))
  		{
  			return new ResponseEntity<String>("DELETED",HttpStatus.OK);
  		}
  		else
  		{
  			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
  		}
	}
      @PutMapping("/updateForum/{forumid}")
      public ResponseEntity<String>updateForum(@PathVariable("forumid") int forumid,@RequestBody Forum forum)
{
    	  Forum forum1 = forumDAO.getForum(forumid);
  		forum1.setCreateDate(new Date());
  		forum1.setForumContent(forum.getForumContent());
  		forum1.setForumname(forum.getForumname());
  		forum1.setLoginname(forum.getLoginname());
  		forum1.setStatus(forum.getStatus());

  		if (forumDAO.updateForum(forum1))
  			return new ResponseEntity<String>("Forum Updated", HttpStatus.OK);
  		else
  			return new ResponseEntity<String>("Forum Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
}
      @GetMapping("/approveForum/{forumId}")
		public ResponseEntity<String> approveForum(@PathVariable("forumId") int forumId) {
			Forum forum = forumDAO.getForum(forumId);

			if (forumDAO.approveForum(forum))
				return new ResponseEntity<String>("Forum Approved", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Forum Not Approved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
      
      @GetMapping("/rejectForum/{forumId}")
		public ResponseEntity<String> rejectForum(@PathVariable("forumId") int forumId) {
			Forum forum = forumDAO.getForum(forumId);

			if (forumDAO.rejectForum(forum))
				return new ResponseEntity<String>("Forum rejected", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Forum Not Approved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

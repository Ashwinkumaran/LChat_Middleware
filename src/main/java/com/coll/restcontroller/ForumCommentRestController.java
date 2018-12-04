package com.coll.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.ForumCommentDAO;
import com.coll.DAO.ForumDAO;
import com.coll.Model.BlogComment;
import com.coll.Model.ForumComment;

@RestController
public class ForumCommentRestController
{
 @Autowired
 ForumDAO forumDAO;
 @Autowired
 ForumCommentDAO forumCommentDAO;
 @PostMapping("/addForumComment")
 public ResponseEntity<String> addForumComment(@RequestBody ForumComment forumcomment)
 {
	 forumcomment.setCommentDate(new Date());
	 if(forumCommentDAO.addComment(forumcomment))
	 {
		 return new ResponseEntity<String>("Success",HttpStatus.OK);
	 }
	 else
		 return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
 }
 @GetMapping("/deleteForumComment/{commentId}")
	public ResponseEntity<String> deleteForumComment(@PathVariable("commentId")int commentId)
	{
		ForumComment forumComment=forumCommentDAO.getForumComment(commentId);
		if(forumCommentDAO.deleteComment(forumComment))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
		@GetMapping("/listForumComment/{forumId}")
		public ResponseEntity<List<ForumComment>>listForumComment(@PathVariable("forumId")int forumId)
		{
			List<ForumComment> listForumComment=forumCommentDAO.listForumComments(forumId);
			if(listForumComment.size()>0)
			{
				return new ResponseEntity<List<ForumComment>> (listForumComment,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<ForumComment>>(listForumComment,HttpStatus.NOT_FOUND);
			}
	  }
}

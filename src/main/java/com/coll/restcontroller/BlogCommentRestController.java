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

import com.coll.DAO.BlogCommentDAO;
import com.coll.DAO.BlogDAO;
import com.coll.Model.BlogComment;

@RestController
public class BlogCommentRestController 
{
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@PostMapping("/addBlogComment")
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment)
	{
		blogComment.setCommentDate(new Date());
		if(blogCommentDAO.addComment(blogComment))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/deleteComment/{commentId}")
	public ResponseEntity<String> deleteBlogComment(@PathVariable("commentId")int commentId)
	{
		BlogComment blogComment=blogCommentDAO.getBlogComment(commentId);
		if(blogCommentDAO.deleteComment(blogComment))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
		@GetMapping("/listBlogComment/{blogId}")
		public ResponseEntity<List<BlogComment>>listBlogComment(@PathVariable("blogId")int blogId)
		{
			List<BlogComment> listBlogComment=blogCommentDAO.listBlogComments(blogId);
			if(listBlogComment.size()>0)
			{
				return new ResponseEntity<List<BlogComment>>(listBlogComment,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<BlogComment>>(listBlogComment,HttpStatus.NOT_FOUND);
			}
	  }
	}


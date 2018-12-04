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

import com.coll.DAO.BlogDAO;
import com.coll.Model.Blog;

@RestController
public class BlogRestController 
{
 @Autowired
 BlogDAO blogDAO;
	@GetMapping("/showAllBlogs")
	public ResponseEntity<List<Blog>> showAllBlogs()
	{
		List<Blog> listBlogs=blogDAO.listBlogs();
		if(listBlogs.size()>0)
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		else
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		blog.setCreatedate(new Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setLoginname("Ashwin");
		blog.setStatus("NA");
		 if(blogDAO.addBlog(blog))
			 return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		 else
			 return new ResponseEntity<String>("Faliure",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@GetMapping("/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId")int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		if(blogDAO.approvedBlog(blog))
		{
			return new ResponseEntity<String>("APPROVED",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/rejectblog/{blogid}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		if(blogDAO.rejectBlog(blog))
		{
			return new ResponseEntity<String>("REJECT",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/deleteBlog/{blogid}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("DELETED",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		@GetMapping("/getBlog/{blogId}")
		public ResponseEntity<Blog>getABlog(@PathVariable("blogId") int blogId)
		{
			Blog blog=(Blog)blogDAO.getBlog(blogId);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	
	@GetMapping("/incrementLikesBlog/{blogid}")
	public ResponseEntity<String> incrementlikesBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		if(blogDAO.incrementLikes(blogid))
		{
			return new ResponseEntity<String>("INCREMENTLIKES",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/incrementDisLikesBlog/{blogid}")
	public ResponseEntity<String> incrementDislikesBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogid);
		if(blogDAO.incrementDisLikes(blogid))
		{
			return new ResponseEntity<String>("INCREMENTLIKES",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("FALIURE",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}


package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.DAO.FriendDAO;
import com.coll.Model.Friend;
import com.coll.Model.UserDetail;

@RestController
public class FriendRestController 
{
	@Autowired
	FriendDAO friendDAO;
	
	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDAO.sendFriendRequest(friend))
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/showFriendList/{loginname}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("loginname")String loginname)
	{
		List<Friend> listFriends=friendDAO.showFriendList(loginname);
		
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showPendingFriendRequest/{loginname}")
	public ResponseEntity<List<Friend>> showPendingFriendRequestList(@PathVariable("loginname")String loginname)
	{
		List<Friend> listPendingFriendRequests=friendDAO.showPendingFriendRequest(loginname);
		
		if(listPendingFriendRequests.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listPendingFriendRequests,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listPendingFriendRequests,HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value="/showSuggestedFriendList/{loginname}")
	public ResponseEntity<List<UserDetail>> showSuggestedFriendList(@PathVariable("loginname")String loginname)
	{
		List<UserDetail> listSuggestedFriendList=friendDAO.showSuggestedFriend(loginname);
		
		if(listSuggestedFriendList.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriendList,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
}

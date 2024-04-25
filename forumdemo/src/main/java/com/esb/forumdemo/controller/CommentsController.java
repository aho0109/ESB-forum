package com.esb.forumdemo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esb.forumdemo.model.Comments;
import com.esb.forumdemo.model.Forummembers;
import com.esb.forumdemo.service.CommentsService;
import com.esb.forumdemo.service.ForummembersService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private ForummembersService forummembersService;
	
	// 刪除
	@DeleteMapping("/forum/postandreply/comments/{commentID}")
	public String doDeletetComments(@PathVariable(name = "commentID") Integer commentID) {;
		commentsService.deleteById(commentID);
		return "已刪除留言";
	}
	
	// 找單一
	@GetMapping("/forum/postandreply/comments/{commentID}")
	public Comments doCommentsByID(@PathVariable(name = "commentID")Integer  commentID) {
		return commentsService.findById(commentID);
	}
	
	// 找全by postID
	@GetMapping("/forum/postandreply/comments")
	public List<Comments> doAllCommentsByPost(String targetType, Integer postID, Integer replyID) {
		return commentsService.findByTargetTypeAndID(targetType, postID, replyID);
		//return commentsService.findByTargetTypeAndID("Posts", 1, null);
	}
	
	// 作者查全
	@GetMapping("/forum/postandreplyDesc/{authorNickname}")
	public List<Comments> doAllCommentsByAuthorNicknameDesc(@PathVariable(name = "authorNickname")String fnickname) {
		return commentsService.findAllCommentsByForummembersFnicknameOrderByCommentIDDesc(fnickname);
	}
//	// 新 作者查全
//	@GetMapping("/forum/postandreplyDesc/{authorNickname}")
//	public List<Comments> doAllCommentsByForummemberIDDesc(@PathVariable(name = "authorNickname")Integer forummemberID) {
//		return commentsService.findAllCommentsByForummemberIDOrderByCommentIDDesc(forummemberID);
//	}
	
	// 新增
	@PostMapping("/forum/postandreply/comments")
	public Comments doInsertComments(@RequestBody CommentsDto commentsDto) {
		Comments ncomments = new Comments();
	    Forummembers forummember = forummembersService.findForummemberByFnickname(commentsDto.getFnickname());

//		String typepString = String.valueOf(comments.getPostID()); // 將 typep 轉換為 String
//		String typerString = String.valueOf(comments.getReplyID()); // 將 typer 轉換為 String
//		String targetType = null;
//		if (typepString.equals("null")) {
//		    targetType = "Posts";
//		} else if (typerString.equals("null")) {
//		    targetType = "Replies";
//		}

		Integer p = commentsDto.getPostID();
	    Integer r = commentsDto.getReplyID();
	    String targetType = null;
	    boolean hasPostID = p != null;
	    boolean hasReplyID = r != null;

	    if (hasPostID) {
	        targetType = "Posts";
	    } else if (hasReplyID) {
	        targetType = "Replies";
	    }
	    
		ncomments.setTargetType(targetType);
		ncomments.setCommentContent(commentsDto.getCommentContent());
		//ncomments.setAuthorNickname(comments.getAuthorNickname());
		ncomments.setForummemberID(forummember.getForummemberID());
		//ncomments.setTargetType(comments.getTargetType());
		ncomments.setPostID(commentsDto.getPostID());
		ncomments.setReplyID(commentsDto.getReplyID());
		ncomments.setCommentTime(LocalDateTime.now());
		ncomments.setDeleted(false);
		commentsService.insert(ncomments);
		System.out.println(ncomments);
		return ncomments;
		
	}
	
	
//	// 找全by replyID
//	@GetMapping("/forum/postreply/comments")
//	public List<Comments> doAllCommentsByReply(String targetType, Integer replyID) {
//		return commentsService.findByTargetTypeAndReplyID(targetType, replyID);
//	}
}

package com.esb.forumdemo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esb.forumdemo.model.Forummembers;
import com.esb.forumdemo.model.Replies;
import com.esb.forumdemo.service.ForummembersService;
import com.esb.forumdemo.service.RepliesService;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RepliesController { 

	
	@Autowired
	private RepliesService repliesService;
	
	@Autowired
	private ForummembersService forummembersService;
	
	// 刪除
	@DeleteMapping("/forum/replies/{replyID}")
	public String doDeletetReplies(@PathVariable(name = "replyID") Integer replyID) {;
	repliesService.deleteById(replyID);
		return "已刪除回覆";
	}
	
	// 找單一
	@GetMapping("/forum/replies/{replyID}")
	public Replies doRepliesByID(@PathVariable(name = "replyID")Integer replyID) {
		return repliesService.findById(replyID);
	}
	
	// 找全o
	@GetMapping("/forum/replies")
	public List<Replies> doAllPosts() {
		return repliesService.findAllReplies();
	}
	
	// 找全by postID
	@GetMapping("/forum/post={postID}/replies")
	public List<Replies> doAllRepliesByPostID(@PathVariable(name = "postID") Integer postID) {
		return repliesService.findAllRepliesByPostsID(postID);
	}
	
	// 作者查全
	@GetMapping("/forum/repliesDesc/{authorNickname}")
	public List<Replies> doAllRepliesByAuthorNicknameDesc(@PathVariable(name = "authorNickname")String fnickname) {
		return repliesService.findAllRepliesByForummembersFnicknameOrderByReplyIDDesc(fnickname);
	}
//	// 新 作者查全
//	@GetMapping("/forum/repliesDesc/{authorNickname}")
//	public List<Replies> doAllRepliesByForummemberIDDesc(@PathVariable(name = "authorNickname")Integer forummemberID) {
//		return repliesService.findAllRepliesByForummemberIDOrderByReplyIDDesc(forummemberID);
//	}
	
	
	// 新增o
	@PostMapping("/forum/post={postID}/replies")
	public Replies doInsertReplies(@PathVariable(name = "postID") Integer postID,
									@RequestBody RepliesDto repliesDto) {
		Replies nreplies = new Replies();
	    Forummembers forummember = forummembersService.findForummemberByFnickname(repliesDto.getFnickname());
		nreplies.setReplyContent(repliesDto.getReplyContent());
		//nreplies.setAuthorNickname(reply.getAuthorNickname());
		nreplies.setForummemberID(forummember.getForummemberID());
		//nreplies.setSboardID(replies.getSboardID());// 有fk所以不能輸入不存在之sboardID
		nreplies.setPostID(postID);
		nreplies.setReplyTime(LocalDateTime.now());
		nreplies.setDeleted(false);
		repliesService.insert(nreplies);
		return nreplies;
		//return postsService.insert(npost);
	}
	
	// 修改o
	@PutMapping("/forum/post={postID}/replies/{replyID}")  //某某使修改某某使用者的文
	public Replies doUpdateReplies(/*@PathVariable(name = "postID") Integer postID,*/
									@PathVariable(name = "replyID") Integer replyID, 
									@RequestBody Replies reply) {
		Replies ureplies = repliesService.findById(replyID);
		ureplies.setReplyContent(reply.getReplyContent());
		//ureplies.setMembers(members);
		//ureplies.setAuthorNickname(reply.getAuthorNickname());
		//ureplies.setSboards(replies.getSboards());// 有fk所以不能輸入不存在之sboardID
		//ureplies.setReplyTime(reply.getReplyTime());
		//ureplies.setDeleted(true);
		repliesService.update(ureplies);
		return ureplies;
	}
	
}

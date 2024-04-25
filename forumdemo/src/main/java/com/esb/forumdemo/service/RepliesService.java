package com.esb.forumdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esb.forumdemo.model.Replies;
import com.esb.forumdemo.model.RepliesRepository;


@Service
@Transactional
public class RepliesService {

	@Autowired
	private RepliesRepository repRepos;
	
	@Autowired
	private PostsService postsService;
	
	// 新增
	public Replies insert(Replies replies) {
		return repRepos.save(replies);
	}
	
	// 修改
	public Replies update(Replies replies) {
		return repRepos.save(replies);
	}
	
	// 刪除
	public void deleteById(Integer replyID) {
		repRepos.deleteById(replyID);
	}
	
	// 找單一
	public Replies findById(Integer replyID) {
		Optional<Replies> optional = repRepos.findById(replyID);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null ;
		}
	}
	
	// 找全o
	public List<Replies> findAllReplies() {
		return repRepos.findAll();
	}
	// 找全by postID
	public List<Replies> findAllRepliesByPostsID(Integer postID) {
		return repRepos.findByPostID(postID);
	}
	
	// 作者查全
	public List<Replies> findAllRepliesByForummembersFnicknameOrderByReplyIDDesc(String fnickname) {
		return repRepos.findAllRepliesByForummembersFnicknameOrderByReplyIDDesc(fnickname);
	}
	// 新 作者查全
	public List<Replies> findAllRepliesByForummemberIDOrderByReplyIDDesc(Integer forummemberID) {
		return repRepos.findAllRepliesByForummemberIDOrderByReplyIDDesc(forummemberID);
	}
}

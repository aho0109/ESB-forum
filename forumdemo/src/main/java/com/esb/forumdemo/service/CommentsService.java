package com.esb.forumdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esb.forumdemo.model.Comments;
import com.esb.forumdemo.model.CommentsRepository;

@Service
@Transactional
public class CommentsService {

	@Autowired
	private CommentsRepository comRepos;
	
	// 新增
	public Comments insert(Comments comments) {
		return comRepos.save(comments);
	}
	
	// 修改
	public Comments update(Comments comments) {
		return comRepos.save(comments);
	}
	
	// 刪除
	public void deleteById(Integer replyID) {
		comRepos.deleteById(replyID);
	}
	
	// 找單一by replyID
	public Comments findById(Integer replyID) {
		Optional<Comments> optional = comRepos.findById(replyID);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null ;
		}
	}
	
	// 找全 單純
	public List<Comments> findAllReplies() {
		return comRepos.findAll();
	}

	// 找全 自行依據postID 或 replyID
	public List<Comments> findByTargetTypeAndID(String targetType, Integer postID, Integer replyID) {
		return comRepos.findByTargetTypeAndPostIDAndReplyID(targetType, postID, replyID);
	}
	
	// 作者全
	public List<Comments> findAllCommentsByForummembersFnicknameOrderByCommentIDDesc(String fnickname) {
		return comRepos.findAllCommentsByForummembersFnicknameOrderByCommentIDDesc(fnickname);
	}
	// 新 作者全
	public List<Comments> findAllCommentsByForummemberIDOrderByCommentIDDesc(Integer forummemberID) {
		return comRepos.findAllCommentsByForummemberIDOrderByCommentIDDesc(forummemberID);
	}
	
//	// 找全by replyID
//	public List<Comments> findByTargetTypeAndReplyID(String targetType, Integer replyID) {
//		return comRepos.findByTargetTypeAndReplyID(targetType, replyID);
//	}
	
}

package com.esb.forumdemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

	List<Comments> findByTargetTypeAndPostIDAndReplyID(String targetType, Integer postID, Integer replyID);
	
	//作者全
	public List<Comments> findAllCommentsByForummembersFnicknameOrderByCommentIDDesc(String fnickname);
	
	// 新 作者全
	public List<Comments> findAllCommentsByForummemberIDOrderByCommentIDDesc(Integer forummemberID);

	
	//List<Comments> findByTargetTypeAndReplyID(String targetType, Integer replyID);

}

package com.esb.forumdemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepliesRepository extends JpaRepository<Replies, Integer> {

	List<Replies> findByPostID(Integer postID);
	
	// 查全
	public List<Replies> findAllRepliesByForummembersFnicknameOrderByReplyIDDesc(String fnickname);
	// 作者查全
	public List<Replies> findAllRepliesByForummemberIDOrderByReplyIDDesc(Integer forummemberID);
	
	
}

package com.esb.forumdemo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Integer> {
	
	// 內建 查全 新增o 修改o
	
	// 查全 由新到舊
	public List<Posts> findAllByOrderByPostIDDesc();
	
	
	// 查by membername
	//public List<Posts> findByMembers_MemberName(String memberName);
	
	// 查全by sboardID
	public List<Posts> findBySboardID(Integer sboardID);
	
	// 作者查全
	public List<Posts> findByForummembersFnicknameOrderByPostIDDesc(String fnickname);
	// 新
	public List<Posts> findByForummemberIDOrderByPostIDDesc(Integer forummemberID);
	
	// 查全by sboardID由新到舊
	public List<Posts> findBySboardIDOrderByPostIDDesc(Integer sboardID);
	
	// 最新動態排列
    @Procedure(procedureName = "GetPostsOrderByLastActivityDesc")
	List<Posts> findAllOrderByLastActivityDesc();
	
	//查詢標題like
	public List<Posts> findByPostTitleContaining(String xxxtitle);
	
	//查詢內文like，不知可不可行
	public List<Posts> findByPostContentContaining(String xxxcontent);
	 
	// 關鍵字模糊搜尋
    @Procedure(procedureName = "FindByKeywords")
	List<Posts> findByKeywords(@Param("keyword") String keyword);
	
	
	//List<Posts> findForummembersForummemberIDByForummembersFnickname(String fnickname);


}


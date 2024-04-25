package com.esb.forumdemo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esb.forumdemo.model.Posts;
import com.esb.forumdemo.model.PostsRepository;


@Service
@Transactional
public class PostsService {
	
	@Autowired
	private PostsRepository poRepos;

	// 新增
	public Posts insert(Posts post) {
		return poRepos.save(post);
	}
	
	// 修改
	public Posts update(Posts post) {
		return poRepos.save(post);
	}
	
	// 刪除
	public void deleteById(Integer postID) {
		poRepos.deleteById(postID);
	}
	
	// 找單一
	public Posts findById(Integer postID) {
		Optional<Posts> optional = poRepos.findById(postID);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null ;
		}
	}
	
	// 找全o
	public List<Posts> findAllPosts() {
		return poRepos.findAll();
	}
	
	// 找全由新到舊
	public List<Posts> findAllPostsDesc() {
		return poRepos.findAllByOrderByPostIDDesc();
	}
	
	public List<Posts> findAllByLastActivityDesc() {
		return poRepos.findAllOrderByLastActivityDesc();
	};
	
	// 找by sboardID
	public List<Posts> findAllPostsBySboardID(Integer sboardID) {
		return poRepos.findBySboardID(sboardID);
		
	}
	
	// 作者查全
	public List<Posts> findAllPostsByForummembersFnicknameOrderByPostIDDesc(String fnickname) {
		return poRepos.findByForummembersFnicknameOrderByPostIDDesc(fnickname);
	}
	// 新 作者查全
	public List<Posts> findAllPostsByForummemberIDOrderByPostIDDesc(Integer forummemberID) {
		return poRepos.findByForummemberIDOrderByPostIDDesc(forummemberID);
	}
	
	// 找by sboardID 由新到舊
	public List<Posts> findAllPostsBySboardIDOrderByPostIDDesc(Integer sboardID) {
		return poRepos.findBySboardIDOrderByPostIDDesc(sboardID);
			
	}
	
	// 關鍵字模糊搜尋
	public List<Posts> findAllByKeywords(String keyword) {
		return poRepos.findByKeywords(keyword);
	}

	
//	public List<Posts> findForummembersForummemberIDByForummembersFnickname(String fnickname) {
//		return poRepos.findForummembersForummemberIDByForummembersFnickname(fnickname);
//	}
	
//	public List<Posts> findByMemberName(String memberName) {
//		return poRepos.findByMembers_MemberName(memberName);
//	}
	
	
}

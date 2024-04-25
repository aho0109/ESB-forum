package com.esb.forumdemo.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esb.forumdemo.model.Forummembers;
import com.esb.forumdemo.model.ForummembersRepository;


@Service
@Transactional
public class ForummembersDetailsService implements UserDetailsService {

	@Autowired
	private ForummembersRepository forummemRepos;

	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		Forummembers forummembers = forummemRepos.findByFnickname(phoneNumber);
        if (forummembers == null) {
            throw new UsernameNotFoundException("無此使用者");
        }
        return forummembers;
	}

//	public Forummembers findById(Integer forummemberID) {
//		Optional<Forummembers> optional = forummemRepos.findById(forummemberID);
//		if (optional.isPresent()) {
//			return optional.get();
//		} else {
//			return null;
//		}
//	}
//	
//	public Forummembers findFnicknameByForummemberID(Integer forummemberID) {
//		return forummemRepos.findFnicknameByForummemberID(forummemberID);
//	}

	

	

}

package com.esb.forumdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esb.forumdemo.model.Forummembers;
import com.esb.forumdemo.model.ForummembersRepository;

@Service
@Transactional
public class ForummembersService {

	@Autowired
	private ForummembersRepository forummemRepos;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public void createUser(Forummembers forummembers) throws Exception {
		Forummembers dbUser = forummemRepos.findByUserName(forummembers.getUsername());
        if (dbUser != null) {
            throw new Exception("帳號重複註冊");
        }
        String encodedPassword = passwordEncoder.encode(forummembers.getFpassword());
        forummembers.setFpassword(encodedPassword);
        forummemRepos.save(forummembers);
    }
	
	public Forummembers authenticateUser(String phoneNumber, String password) throws Exception {
	    // 根據phoneNumber找到對應的用戶
	    Forummembers forummembers = forummemRepos.findByPhoneNumber(phoneNumber);
	    if (forummembers == null) {
	        throw new Exception("用戶不存在");
	    }
	    // 驗證密碼是否正確
	    if (!passwordEncoder.matches(password, forummembers.getFpassword())) {
	        throw new Exception("密碼錯誤");
	    }
	    return forummembers;
	}

	
	public Forummembers findById(Integer forummemberID) {
		Optional<Forummembers> optional = forummemRepos.findById(forummemberID);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Forummembers findFnicknameByForummemberID(Integer forummemberID) {
		return forummemRepos.findFnicknameByForummemberID(forummemberID);
	}

	public Forummembers findForummemberByFnickname(String fnickname) {
		return forummemRepos.findByFnickname(fnickname);
	}
	

}

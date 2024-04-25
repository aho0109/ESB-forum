package com.esb.forumdemo.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esb.forumdemo.model.Forummembers;
import com.esb.forumdemo.service.ForummembersDetailsService;
import com.esb.forumdemo.service.ForummembersService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ForummemberController {

	@Autowired
    private ForummembersService forummembersService;
	
	@Autowired
    private ForummembersDetailsService forummembersDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/forum/register")
	public ResponseEntity<Object> register(@RequestBody Forummembers forummembers) {
	    System.out.println("Received registration request for user: " + forummembers.getUsername());
	    System.out.println("Received registration request for user: " + forummembers.getFnickname());
	    System.out.println("Received registration request for user: " + forummembers.getEmail());
	    System.out.println("Received registration request for user: " + forummembers.getPhoneNumber());
	    System.out.println("Received registration request for user: " + forummembers.getFpassword());
	        try {
	            // 帶入參數實現註冊
	        	forummembersService.createUser(forummembers);
	            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
	                    "status", true,
	                    "message", "註冊成功"));
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
	                            "status", false,
	                            "message", "註冊失敗",
	                            "error", ex.getMessage())
	                   );
	        }
	}
	
	@PostMapping("/forum/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
	    System.out.println("Received login request for user: " + loginDto.getUserName());
	    try {
	        // 驗證用戶名和密碼
	        Forummembers loggedInUser = forummembersService.authenticateUser(loginDto.getPhoneNumber(), loginDto.getFpassword());
	        
//	        // 將用戶存入 Session
//	        session.setAttribute("loggedInUser", loggedInUser);
//	        System.out.println(session);
	        
	        // 如果驗證成功，返回成功訊息
	        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
	                "status", true,
	                "message", "登入成功",
	                "user", loggedInUser));
	    } catch (Exception ex) {
	        // 如果驗證失敗，返回失敗訊息
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
	                "status", false,
	                "message", "登入失敗",
	                "error", ex.getMessage()));
	    }
	}
	
	
    @GetMapping("/forum/forummembers/{forummemberID}")
    public Forummembers doForummembersByID(@PathVariable(name = "forummemberID") Integer forummemberID) {
    	return forummembersService.findById(forummemberID);
    }
    
    
    @GetMapping("/forum/forummembers/nicknameOf/{forummemberID}")
    public  ResponseEntity<String> doFnicknameByForummemberID(@PathVariable(name = "forummemberID") Integer forummemberID) {
    	Forummembers forummember = forummembersService.findFnicknameByForummemberID(forummemberID);
    	if (forummember != null) {
            return ResponseEntity.ok(forummember.getFnickname());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
//    @PostMapping("/forum/login")
//    public ResponseEntity<Forummembers> login(@RequestBody Forummembers forummembers) {
//        // 從前端接收到的使用者暱稱和密碼
//        String nickname = forummembers.getNickname();
//        String password = forummembers.getPassword();
//        
//        // 可再進行登入驗證，例如驗證暱稱和密碼是否正確
//
//        // 假設驗證成功，回傳一個使用者物件，在這個物件中包含任何想要回傳的使用者資訊
//        User user = new User();
//        user.setNickname(nickname);
//        // 其他相關處理
//
//        return ResponseEntity.ok(user);
//    }
	
}

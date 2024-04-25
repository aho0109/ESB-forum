//package com.esb.forumdemo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.esb.forumdemo.util.JwtUtil;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//
//public class JwtUtilTest {
//
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	/**
//	 * 測試 初始化 JWT Token
//	*/
//	@Test
//	void generateJwtTest() {
//	   Map<String, String> claims = new HashMap<>();
//	   String subject = "IdenttiyId";
//	   String jti = null; // 因為你的 generateToken 方法中有提供默認值為 null 的 jti 參數
//	   String jwtToken = jwtUtil.generateToken(claims, subject, jti);
//	   assertTrue(jwtToken.length() > 0);
//	}
//
//	/**
//	  * 測試 取得單一屬性
//	*/
//	@Test
//	void getClaimTest() {
//	    // 推入假屬性 nmae
//	    Map<String, String> claims = new HashMap<>();
//	    claims.put("name", "Bob");
//
//	    String subject = "10083";
//	    String jti = null; // 因為你的 generateToken 方法中有提供默認值為 null 的 jti 參數
//	    String jwtToken = jwtUtil.generateToken(claims, subject, jti);
////	    String name = (String) jwtUtil.strGetClaim(jwtToken, "name");
////	    assertEquals(claName, name);
//	    String name = jwtUtil.getClaimFromToken(jwtToken, Claims::getSubject); // 假設你要取得的是 subject 而非 name
//	    assertEquals(subject, name);
//	}
//
//
//	/**
//	  * 測試 Token到期是否正常
//	*/
//	@Test
//	void validateTokenTest() throws InterruptedException {
//
//	  String subject = "10083";
//	  String jwtToken = jwtUtil.generateToken(new HashMap<>(), subject);
//
//	  // 測試未過期情況
//	  assertTrue(jwtUtil.validateToken(jwtToken));
//
//	  // 測試到期情況
//	  assertThrows(ExpiredJwtException.class, () -> {
//	      Thread.sleep(5000);
//	      jwtUtil.validateToken(jwtToken);
//	  });
//	}
//	
//}

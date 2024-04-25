package com.esb.forumdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {
//	
//	
//	private final AppUserRepository appUserRepository;
//
//    /**
//     * 用於查找用戶詳細信息
//     * @return userEmail(用戶詳細信息)
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userEmail -> appUserRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
//    }
//
//    /**
//     * 執行身份驗證的關鍵組件
//     */
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    /**
//     * 管理身份驗證的組件
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    /**
//     * 以便將用戶密碼進行安全的雜湊存儲
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}

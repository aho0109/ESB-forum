package com.esb.forumdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.esb.forumdemo.service.ForummembersDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	public ForummembersDetailsService dbUserService;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)  //禁止CSRF（跨站請求偽造）保護。
                .authorizeHttpRequests((authorize) -> authorize //對所有訪問HTTP端點的HttpServletRequest進行限制
                        .requestMatchers(
                                "/**"
                        ).permitAll()   //指定上述匹配規則中的路徑，允許所有用戶訪問，即不需要進行身份驗證。
                        .anyRequest().authenticated()   //其他尚未匹配到的路徑都需要身份驗證
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
          // 添加加解密物件
          provider.setPasswordEncoder(passwordEncoder());
          provider.setUserDetailsService(this.dbUserService);
          return provider;
    }

    // 認證管理器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
    }
}

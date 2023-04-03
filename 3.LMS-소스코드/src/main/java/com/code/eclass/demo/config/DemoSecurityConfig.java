package com.code.eclass.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.eclass.demo.dao.EclassDao;
import com.code.eclass.demo.service.EclassService;


@Configuration  // xml 파일이다.
@EnableWebSecurity // 웹 보안을 활성화 하겠다.
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired  // 보안에서 쓰일 데이터 소스 자동 빈 주입
	private DataSource securityDataSource;
	
	@Autowired
	private EclassService eclassService;
	
	@Override  // 사용자 세부 서비스를 설정하기 위한 오버라이딩
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
//	.passwordEncoder(passwordEncorder())
//	
//	@Bean
//	public PasswordEncoder passwordEncorder() {
//		
//		return new BCryptPasswordEncoder();
//	}
	@Override // 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩
	protected void configure(HttpSecurity http) throws Exception {
		
		
		 http
	        .authorizeRequests()
	        	.antMatchers("/css/**").permitAll()
	        	.antMatchers("/img/**").permitAll()
	        	.antMatchers("/img/home/**").permitAll()
	        	.antMatchers("/img/icon/**").permitAll()
	            .antMatchers("/mypage").authenticated()
	            .antMatchers("/courses/**").authenticated()
	            .anyRequest().permitAll()
	            .and()
	            .formLogin()
	        	.loginPage("/login")
	        	.loginProcessingUrl("/authenticateTheUser")
	        	.successHandler( // 로그인 성공 후 핸들러
	        			  new AuthenticationSuccessHandler() { // 익명 객체 사용
	        			    @Override
	        			    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        			      if(!eclassService.confirmPassword(authentication.getName())) {
	        			    	  
	        			    	  response.sendRedirect("/eclass/");
	        			      }else {
	        			    	  response.sendRedirect("/eclass/passwordChange");
	        			      }
	        			    }
	        			  })
	        	.permitAll()
	        .and()
	        .logout().logoutSuccessUrl("/")
	        .permitAll()
	        .and()
	        .exceptionHandling().accessDeniedPage("/access-denied");
		 	
		 
		 
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
	     filter.setEncoding("UTF-8");
	     filter.setForceEncoding(true);
	     http.addFilterBefore(filter,CsrfFilter.class);
	     
	}
	
	
	
}

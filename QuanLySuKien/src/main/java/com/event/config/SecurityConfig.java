package com.event.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	//Cấu hình phần kiểm tra đăng nhập
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {  //xác thực người dùng
			auth.userDetailsService(userDetailsService) //truyền vào đối tượng nào có hàm đó
			.passwordEncoder(new BCryptPasswordEncoder()); //
			//sau kiểm tra 2 lớp trên(userDetailsService,passwordEncoder), thông tin sẽ lưu vô session
		}
		//Cấu hình phần phân quyền
		@Override
		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
//			http.csrf().disable()
//			.antMatcher("/**") //Kiểm tra với url bắt đầu bằng /
//			.authorizeRequests()
//			
//			.antMatchers("/home/login")
//			.permitAll()
//			.antMatchers("/admin/**")
//			.hasAnyRole("ADMIN")
//			.anyRequest() //mọi request gửi lên từ client
//			.authenticated(); //bắt buộc phải đăng nhập
//			
//			
//			
//			http.formLogin()
//			.loginPage("/home/login")
//			.loginProcessingUrl("/home/login")
//			.usernameParameter("email")
//			.passwordParameter("password")
//			.defaultSuccessUrl("/home")
//			.failureUrl("/auth/login?error=true");
//			
//			http.logout()
//			.logoutUrl("/admin/logout")
//			.logoutSuccessUrl("/home/login");
			
			
//			http.exceptionHandling()
//			.accessDeniedPage("/error/403");
			
			http.csrf().disable().antMatcher("/**")
			.authorizeRequests()
			.antMatchers("/admin/**")
			.hasRole("ADMIN").and().formLogin().loginPage("/home/login")
			.loginProcessingUrl("/home/login").defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password")
//			.and().exceptionHandling()
//			.accessDeniedPage("/error/403")
			.and().logout().logoutUrl("/admin/logout")
			.logoutSuccessUrl("/home/login");
		}
		
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			//Cấu hình bỏ qua, không kiểm tra đăng nhập cho các file tĩnh.
			web.ignoring().antMatchers("/static/**","/css/**","/js/**","/icon/**","/images/**","/fonts/**","/plugins/**","/uploads/**");
		}
		
}

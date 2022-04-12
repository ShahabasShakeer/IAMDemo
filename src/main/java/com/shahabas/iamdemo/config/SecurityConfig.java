package com.shahabas.iamdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("appDataSource")
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication ... oh yeah!!!		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}
	
	
	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/showFormPage").hasAnyRole("APPROVER", "ADMIN")
			.antMatchers("/addContact").hasAnyRole("APPROVER", "ADMIN")
			.antMatchers("/deleteContact").hasRole("ADMIN")
			.antMatchers("/deleteUser").hasRole("ADMIN")
			.antMatchers("/contactList").hasAnyRole("WRITER", "APPROVER", "ADMIN")
			.antMatchers("/userList").hasAnyRole("WRITER", "APPROVER", "ADMIN")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/accessDenied");
		
	}

}

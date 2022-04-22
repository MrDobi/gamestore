package edu.pdobrosz.gamestore.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// TODO load users from api
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("spring").password(encoder.encode("verySecretAndSecure")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/actuator/**", "/", "/game/*", "/VAADIN/**",

				// the standard favicon URI
				"/favicon.ico",

				// the robots exclusion standard
				"/robots.txt",

				// web application manifest
				"/manifest.webmanifest", "/sw.js", "/offline.html",

				// icons and images
				"/icons/**", "/images/**", "/styles/**").permitAll().antMatchers("/**").hasRole("USER").and()
				.formLogin();
	}

}
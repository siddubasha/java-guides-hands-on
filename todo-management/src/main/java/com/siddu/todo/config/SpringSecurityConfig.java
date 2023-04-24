package com.siddu.todo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity // this annotation enables PreAuthorise annotation
public class SpringSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(authorize -> {
		//	authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
		//	authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
		//	authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
		//	authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
		//	authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
		//	authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
			/*
			       Last one is for all public users, to see these rest api's no need of credentials
			       we need to uncomment all method to work last one
			 */
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		return http.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {

		UserDetails user1 = User.builder().username("siddu").password(passwordEncoder().encode("password"))
				.roles("USER").build();

		UserDetails user2 = User.builder().username("basha").password(passwordEncoder().encode("password"))
				.roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user1, user2);

	}
}

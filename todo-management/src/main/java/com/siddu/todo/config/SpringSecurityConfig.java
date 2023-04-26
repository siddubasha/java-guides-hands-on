package com.siddu.todo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity // this annotation enables PreAuthorise annotation
@AllArgsConstructor
public class SpringSecurityConfig {

	private UserDetailsService userDetailsService;
	
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
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		 return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
/* commenting because of using Database authentication instead of In_Memory authentication and also comment 
 * user info(username, password,role) in properties file beacuser their info is exist in database as tables
	@Bean
	UserDetailsService userDetailsService() {

		UserDetails user1 = User.builder().username("siddu").password(passwordEncoder().encode("password"))
				.roles("USER").build();

		UserDetails user2 = User.builder().username("basha").password(passwordEncoder().encode("password"))
				.roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user1, user2);

	}
	*/
}

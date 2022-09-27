package com.mandreatti.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mandreatti.cursomc.security.JWTAutenthicationFilter;
import com.mandreatti.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
    public  UserDetailsService userDetailsService;
	 
	
	@Autowired
	public JWTUtil jwtUtil;
	    
	    
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
	};
		
	private static final String[] PUBLIC_MATCHERS_GET = {
				"/produtos/**",
				"/categorias/**",
				"/clientes/**"
	}; 
		
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
		 
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
		    
		    

	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	    	
		 http
	        .cors().and().csrf().disable()
	        .addFilter(new  JWTAutenthicationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil))
	        .authorizeRequests()
	        .antMatchers("/h2-console/*").permitAll()
	        .antMatchers(PUBLIC_MATCHERS).permitAll()
            .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/test/**").permitAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        

	        return http.build();
	 }
	    
	

	 @Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
		 return (web) -> web.ignoring()
	                .antMatchers("/h2-console/*",
	                        "/configuration/**",
	                        "/swagger-resources/**");
	    }
	 
	 @Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}
	    
}

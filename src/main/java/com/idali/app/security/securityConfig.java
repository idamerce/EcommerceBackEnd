package com.idali.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService; 
	/*
	@Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;
   */
     @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception { 
		httpSecurity.csrf().disable().authorizeRequests()
		//.antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/api/auth/**"
				,"/api/admin/produits/photoProduit/*"
				,"/api/admin/produits/getProductSolds/*"
				,"/api/admin/produits/details/*"
				,"/api/admin/produits/getAll"
				,"/api/admin/produits/getSelected",
				"/api/admin/categories/getCategorieByName/*"
				,"/api/admin/categories/getCategoriesName"
				,"/api/admin/categories/getProductsOfCategorie/*"
				
				//on peut creer une autre controller avec un lien at l inclure ici mais juste pour ne pas refaire le travail j'ai fait comme ça
				
				
				)
		.permitAll().anyRequest().authenticated();
		
       httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
 	}
 
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception { 
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

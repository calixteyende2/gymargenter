package com.sygescom.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
    @Autowired	    
    private DataSource dataSource;
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		/* Authentification sans encryptage du password 
		 * auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
		 * auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER");
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER", "ADMIN");
		 */
		
		
	    PasswordEncoder passwordEncoder = passwordEncoder();
	   
	    System.out.println("=============================================");
	    System.out.println("Mot de passe encoder 1234:");
	    System.out.println(passwordEncoder.encode("1234"));
	    System.out.println("=============================================");
	    
	  
	    System.out.println("=============================================");
	    System.out.println("Mot de passe encoder pour isoumare:");
	    System.out.println(passwordEncoder.encode("isoumare2020"));
	    System.out.println("=============================================");
	   
	    
	    /*
	    System.out.println("=============================================");
	    System.out.println("Mot de passe encoder pour bini:");
	    System.out.println(passwordEncoder.encode("bini"));
	    System.out.println("=============================================");
	    */
	    
		
	    /* Authentification inMemoryAuthentication avec encryptage du password*/
		/*
		 * auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.
		 * encode("1234")).roles("USER");
		 * auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.
		 * encode("1234")).roles("USER");
		 * auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.
		 * encode("1234")).roles("USER", "ADMIN");
		 */
	    
	     /* Authentification jdbc avec encryptage du password dans la bd*/
	   
			
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from user where username=?")
		.authoritiesByUsernameQuery("select username as principal, role as role from user_role where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new BCryptPasswordEncoder());	 
			  
	  }
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS); /* ALWAYS */
		
		
		http.formLogin().loginPage("/login");	 
		http.authorizeRequests().antMatchers( "/admin/**", "/save**/**",  "/delete**/**", "edit**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/utilisateurs**/**").hasAnyRole("USER", "ADMIN");	
		http.authorizeRequests().antMatchers("/userSave**/**", "/userEdit**/**", "/userDelete**/**",  "/userLire**/**" ).hasAnyRole("USER");	
		http.authorizeRequests().antMatchers("/user**/**", "/", "/index**/**", "/login**/**", "/abonnement**/**", "/css**/**", "/img**/**", "/js**/**","/webjars**/**").permitAll();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
		http.authorizeRequests().anyRequest().authenticated();
		//http.csrf().disable(); //http.csrf();  //http.csrf().disabled() si on utilise jwt pour authentification
		
	}
	
	 
	/*
	 * l'annotation @Bean rend disponible PasswordEncoder dans le contexte de
	 * l'appliction et on peut donc l'utiliser dans le controller avec
	 * l'annotation @Autowired
	 */

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*Equivalent du code precedent
	 * PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
	 * hashedPassword = passwordEncoder.encode(yourpassword);	  
	 */
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/multiselect/css",
                        "classpath:/static/multiselect/scss",
                        "classpath:/static/multiselect/js");
    }
	
 
}

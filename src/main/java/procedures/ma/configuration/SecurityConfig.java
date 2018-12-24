package procedures.ma.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	@Autowired
    public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception{
      auth.jdbcAuthentication()
      	.dataSource(dataSource)
      		.usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")
      		.authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username = ?")
      			.rolePrefix("ROLE_");
     
	}
	
     @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 
       http
       	.csrf().disable()
       	.httpBasic()
       	.and()
          .authorizeRequests()
            .antMatchers("/css/**","/static/**","/js/**","/images/**","/input/**").permitAll()
            //.antMatchers("/", "/acceuil").permitAll()
          	.anyRequest()
          		.authenticated()
       				.and()
			       	.formLogin()
			       	.loginPage("/login")
			        .permitAll()
			        .and()
			        .logout()
			        .permitAll()
                	.and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                ;
    }
}

package com.myproject.mailservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.myproject.mailservice.security.RestAuthenticationEntryPoint;
import com.myproject.mailservice.security.TokenAuthenticationFilter;
import com.myproject.mailservice.security.TokenHelper;
import com.myproject.mailservice.service.CustomUserDetailsService;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    //Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Definisemo nacin autentifikacije
    //Svaki
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( jwtUserDetailsService )
                .passwordEncoder( passwordEncoder() );
    }

    @Autowired
    TokenHelper tokenHelper;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                //za neautorizovane zahteve posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
                .authorizeRequests()
                //svim korisnicima dopusti da pristupe putanjama /auth/**
                  .antMatchers("/api/auth/**").permitAll()               
   //               .antMatchers("/mailservice/contacts/**").permitAll()
                  .antMatchers("/mailservice/contacts/getAll").hasAuthority("ADMIN")
                  .antMatchers("/mailservice/contacts/").permitAll()
                  .antMatchers("/mailservice/contacts/user").permitAll()
                  .antMatchers("/mailservice/contacts/addContact").permitAll()
                  .antMatchers("/mailservice/contacts/editContact/").permitAll()
                  .antMatchers("/mailservice/contacts/deleteContact/").permitAll()
                  .antMatchers("/mailservice/contacts/upload_photo").permitAll()
                  .antMatchers("/mailservice/photo/**").permitAll()
                  .antMatchers("/mailservice/accounts/**").permitAll()
                  .antMatchers("/mailservice/users/**").permitAll()
                  .antMatchers("/mailservice/messages/**").permitAll()
                  .antMatchers("/mailservice/attachments/**").permitAll()
                  .antMatchers("/elasticsearch/**").permitAll()
                  .antMatchers("/mailservice/tags/**").permitAll()
	  /*            .antMatchers("/mailservice/tags/**").permitAll()
	              .antMatchers("/elasticsearch/search/contactFuzzy/{fuzzy}").hasAuthority("ADMIN")
	              .antMatchers("/elasticsearch/search/contactPhrase/{phrase}").hasAuthority("ADMIN")
	              .antMatchers("/elasticsearch/search/searchByFirstName/{firstName}").hasAuthority("ADMIN")
	              .antMatchers("/elasticsearch/search/searchByLastName/{lastName}").hasAuthority("ADMIN")
	              .antMatchers("/elasticsearch/search/searchByNote/{note}").hasAuthority("ADMIN")
	              .antMatchers("/elasticsearch/search-contacts").hasAuthority("ADMIN")*/
                //svaki zahtev mora biti autorizovan
                .anyRequest().authenticated().and()
                //presretni svaki zahtev filterom
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class)

        .csrf().disable();
    }



    //Generalna bezbednost aplikacije
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/auth/login"
//                "/ebook/search"
                
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/*.html",
                "/favicon.ico/**",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.pdf",
                "/**/*.txt",
                "/**/*.jpg",
                "/**/*.png",
                "/bootstrap/**"
                
            );
    }

}

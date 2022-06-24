package com.example.exampleproject.Service.Configs;
import com.example.exampleproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


@Component
@EnableAutoConfiguration
public class WebSecConf extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private MyAP aadapter;

    @Autowired
    public WebSecConf(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                        .passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.authenticationProvider(aadapter);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/page.css");
    }
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/feed", "/logout").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/business/", "/buddy/").hasAnyAuthority("ROLE_BUSINESS")
                .antMatchers("/buddy/", "/business/" ).hasAnyAuthority("ROLE_USER")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html",true)
                .failureUrl("/login?error").permitAll()

                .defaultSuccessUrl("/feed").permitAll() //переход на страницу feed
//                .successForwardUrl("/admin").usernameParameter("ROLE_ADMIN")
//                .defaultSuccessUrl("/admin").usernameParameter("ROLE_ADMIN")
//                .defaultSuccessUrl("/business/1/product-create").usernameParameter("ROLE_BUSINESS")

                .and()
//                .httpBasic()
//                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");



    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN")
                .and()
                .withUser("business").password("password").roles("BUSINESS");
    }



}
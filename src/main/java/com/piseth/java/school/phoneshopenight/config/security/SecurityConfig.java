package com.piseth.java.school.phoneshopenight.config.security;

import com.piseth.java.school.phoneshopenight.config.jwt.JwtLoginFilter;
import com.piseth.java.school.phoneshopenight.config.jwt.TokenVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/","index.html","css/**","js/**").permitAll()  //put home page to white list
                //.antMatchers("/models").hasRole(RoleEnum.SALE.name()) //todo can access all GET. POST,DELETE
                //.antMatchers(HttpMethod.POST,"/brands").hasAuthority(BRAND_WRITE.getDescription())
                //.antMatchers(HttpMethod.GET,"/brands").hasAuthority(BRAND_READ.getDescription())
                //.antMatchers("/brands").hasRole("SALE") todo instead of using permission based
                .anyRequest()
                .authenticated();

               /* .and()
                .httpBasic();*/ //todo instead of basic auth by token
    }

   /* @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        //User user1 = new User("dara", passwordEncoder.encode("dara123"), Collections.emptyList());
        UserDetails user1 = User.builder()
                .username("dara")
                .password(passwordEncoder.encode("dara123"))
                //.roles(RoleEnum.SALE.name())// ROLE_SALE
                .authorities(RoleEnum.SALE.getAuthorities())
                .build();
        UserDetails user2 = User.builder()
                .username("thida")
                .password(passwordEncoder.encode("thida123"))
                //.roles("ADMIN")
                .authorities(RoleEnum.ADMIN.getAuthorities())
                .build();
        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1,user2);
        return userDetailsService;
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(getAuthenticationProvider());

    }
    //todo related with userDetailsServices
    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}

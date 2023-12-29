package com.fst.mini_projet_gestion_de_vente.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig  {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }
    //@Bean

    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        String pwd =passwordEncoder.encode("1234");
        InMemoryUserDetailsManager inMemoryUserDetailsManager =new InMemoryUserDetailsManager(
                User.withUsername("user1").password(pwd).roles("USER").build(),
                User.withUsername("user2").password(pwd).roles("USER","ADMIN").build()


        );


        return inMemoryUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity.formLogin();
         httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
         httpSecurity.exceptionHandling().accessDeniedPage("/notAutorized");
         return httpSecurity.build();

    }

    }


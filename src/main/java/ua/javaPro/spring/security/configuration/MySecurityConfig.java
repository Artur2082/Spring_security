package ua.javaPro.spring.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.sql.DataSource;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class MySecurityConfig  {
    @Autowired
    private DataSource dataSource;
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager (){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception{
        http.
                authorizeHttpRequests()
                .requestMatchers(antMatcher("/")).hasAnyRole("HR", "MANAGER", "EMPLOYEE")
                .requestMatchers(antMatcher("/manager_info")).hasAnyRole("HR", "MANAGER")
                .requestMatchers(antMatcher("/hr_info")).hasAnyRole("HR")
                .anyRequest().authenticated()
                .and().formLogin().permitAll();
        return http.build();
    }
}

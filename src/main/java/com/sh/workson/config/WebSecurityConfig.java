package com.sh.workson.config;

import com.sh.workson.auth.handler.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * security가 검증안해도 되는 정적 요소들 등록
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().requestMatchers("/css/**", "js/**", "images/**"));
    }

    /**
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeRequest -> {
            authorizeRequest
                    .requestMatchers("/", "/index.html").permitAll()
                    .requestMatchers("/project/**").authenticated()
                    .requestMatchers("/dashboard/**").authenticated()
                    .requestMatchers("/board/**").authenticated()
                    .requestMatchers("/approval/**").authenticated()
                    .requestMatchers("/schedule/**").authenticated()
                    .requestMatchers("/reservation/**").authenticated()
                    .requestMatchers("/chat/**").authenticated()
                    .requestMatchers("/attend/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    // 민정



                    // 우진



                    // 재준



                    // 민준



                    // 준희



                    // 무진




                    .anyRequest().authenticated();
        }));

        http.formLogin((formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/auth/login.do")
                    .loginProcessingUrl("/auth/login.do")
                    // handler 달 경우 여기다가 작성
                    .permitAll();
        }));

        http.logout(logoutConfigurer -> {
            logoutConfigurer
                    .logoutUrl("/auth/logout.do")
                    .logoutSuccessUrl("/");
        });

        return http.build();
    }

    /**
     * 비밀번호 암호화
     * @return
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 랜덤솔트값을 사용.
    }

}

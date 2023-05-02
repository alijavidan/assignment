package com.internet.engineering.IECA8.config;

import com.internet.engineering.IECA8.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers ("/auth/**").permitAll()
                .antMatchers ("/test/**").permitAll()
                .antMatchers ("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.headers().frameOptions().disable();
    }
}

//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////            http.csrf().disable()
////                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
////                .authorizeRequests()
////                .requestMatchers ("/auth/**").permitAll()
////                //                .requestMatchers ("/test/**").permitAll()
////                //                .requestMatchers ("/error/**").permitAll()
////                //                .requestMatchers ("/api/**").permitAll()
////                .anyRequest().authenticated();
////
////            return http.build();
//
//        return http
//                .csrf().disable()
//                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
////                .addFilterBefore(new SimpleCORSFilter(), ChannelProcessingFilter.class)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
////                        .requestMatchers(new AntPathRequestMatcher("/test/**")).permitAll()
//                        .anyRequest().authenticated())
//
//        .build();
//    }
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("*"));
////        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////        configuration.setAllowedHeaders(Arrays.asList("*"));
//////        configuration.setAllowedHeaders(Arrays.asList("x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN"));
////        configuration.setAllowCredentials(true);
////        configuration.setExposedHeaders(Arrays.asList("*"));
//////        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//}
package com.lawencon.pss_app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.pss_app.filter.AuthorizationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authorizationFilter)
			throws Exception {

		http.cors(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable());

		http.addFilterAt(authorizationFilter, BasicAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public List<RequestMatcher> matchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/login", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/register", HttpMethod.POST.name()));
//		matchers.add(new AntPathRequestMatcher("/PS/createschedule", HttpMethod.POST.name()));
		return matchers; 
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(), 
                        HttpMethod.PUT.name(), 
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name()
                    );
            }
        };
    }
}
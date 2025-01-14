package com.restful.adoptions.user.config;

import com.restful.adoptions.user.config.filter.JwtTokenValidator;
import com.restful.adoptions.user.service.UserDetailServiceImp;
import com.restful.adoptions.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain ( HttpSecurity httpSecurity ) throws Exception {
        // configure security roules
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(  session ->session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests( http -> {

                    // public endpoints
                    http.requestMatchers(HttpMethod.GET, "/api/v1/adoptions").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/locations").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/pets").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/images").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/species").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll();

                    // privated endpoints

                    http.requestMatchers(HttpMethod.POST, "/api/v1/adoptions").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/adoptions/{id}").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/adoptions/delete/{id}").hasAnyRole("USER", "ADMIN","REFUGE");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/locations").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/locations/{id}").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/locations/delete/{id}").hasAnyRole("USER", "ADMIN","REFUGE");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/pets").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/pets/{id}").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/pets/delete/{id}").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/pets/not-available/{id}").hasAnyRole("USER", "ADMIN","REFUGE");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/images").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/images/delete/{id}").hasAnyRole("USER", "ADMIN","REFUGE");

                    http.requestMatchers(HttpMethod.POST, "/api/v1/species").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/species/{id}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PATCH, "/api/v1/species/delete/{id}").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole("USER", "ADMIN","REFUGE");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAnyRole("USER", "ADMIN","REFUGE");

                    // some other http state is denied (or permited)
                    http.anyRequest().permitAll();

                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager ( AuthenticationConfiguration authenticationConfiguration ) throws  Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider (UserDetailServiceImp userDetailServiceImp){

        DaoAuthenticationProvider  provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder( passwordEncoder() );
        provider.setUserDetailsService( userDetailServiceImp );

        return provider;

    }

    @Bean
    public PasswordEncoder passwordEncoder (){

        return new BCryptPasswordEncoder();

    }

}

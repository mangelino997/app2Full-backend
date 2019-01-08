package ar.com.draimo.jitws.security;

import ar.com.draimo.jitws.service.AppUserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuracion de Seguridad Web
 * @author blas
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailsService appUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AppUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* Configura los filtros de autenticacion (rutas autorizadas), los cors y 
     + deshabilita el cross site scripting
     */ 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Configura los cors
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        http.csrf().disable().authorizeRequests()
                .antMatchers("/jitws/socket/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // Configura la gestion de autenticacion y la encriptacion de la contrasenia
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /*
    * Esta configuración permite que la aplicación del cliente acceda a esta API. 
    * Todo el dominio que consuma esta API debe estar incluido en las condiciones permitidas.
    */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    //blas = $2a$04$nVqWqynKnpkiEIh8bsBnhOdYc5S69KAOw2DMohrVSE.zIzdBsxYwi
    
}

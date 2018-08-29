package ar.com.wecoode.jitws.security;

import ar.com.wecoode.jitws.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

/**
 *
 * @author blas
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AppUserDetailsService appUserDetailsService;
    
    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*
    * Este método es para anular el AuthenticationManagerBuilder predeterminado. 
    * Podemos especificar cómo se guardan los detalles del usuario en la aplicación. 
    * Puede estar en una base de datos, LDAP o en la memoria.
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(getPasswordEncoder());
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
        config.addExposedHeader("x-auth-token");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    /*
    * Este método es para anular algunas configuraciones de WebSecurity. 
    * Si desea ignorar algunas solicitudes o patrones de solicitud, 
    * puede especificar que dentro de este método
    */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /*
    * Este método se utiliza para anular HttpSecurity de la aplicación web. 
    * Podemos especificar nuestros criterios de autorización dentro de este método.
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /*String password = "parse"; //$2a$10$0M9cnuURa67AIn7OIxAh6OxNvr89c8qy28lhe7aoTbm.nNFIgvywK
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("*****************************************************: " + hashedPassword);*/
        
        //Configura los cors
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        //Inicia la configuracion de autorizacion
        http.authorizeRequests()
                //Ignora las siguientes urls
                .antMatchers("/jit/login", "/jit/logout").permitAll()
                //Todas las demas urls requieren autenticacion
                .anyRequest().fullyAuthenticated().and()
                //Logout de la aplicacion, limpia cualquier autenticacion
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/jit/logout", "POST"))
                .and()
                //Habilita la autenticacion basica
                .httpBasic().and()
                //Configurar la sesion en el servidor
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                //Deshabilita los ataque de Cross Site Scripting
                .csrf().disable();
    }

}

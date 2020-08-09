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
/* la linea de arriba indica que tipo de seguridad implementaremos, puede ser centralizada o distribuida,
  la recomendada es la distribuida y se define con "prePostEnabled = true "
  este tipo de seguridad verifica el acceso a CADA RECURSO al que se quiere acceder mediante las
  peticiones HTTP
*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailsService appUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder; /* encriptador de claves de spring. tmb se puede usar uno propio*/

    public WebSecurityConfig(AppUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* 
     * Configura los filtros de autenticacion (rutas autorizadas), los cors y 
     * deshabilita el cross site scripting
     */ 
     //Este metodo configura la SEGURIDAD
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Configura los cors
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);

        /* Le indicamos que path no va a usar la autenticacion del token
            csrf().disable() se usa cuando usamos API's
            cuando el navegador accede a una API necesita proteccion y esa la otorga csrf
            mediante sesiones con HttpSession
        */
        http.csrf().disable().authorizeRequests()
                .antMatchers("/jitws/socket/**").permitAll() //en esta direcc accede cualquier persona
                .anyRequest().authenticated() //cualquier otra direccion necesita la atenticacion
                .and() //y que todas esas autenticaciones pasaran por los sig filtros
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager())) //valida el token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
                /* la linea de arriba anula las sesiones, para que no guarde ninguna 
                STATELESS significa sin sesiones
                */

        /* CSRF nos protege de ataques 'Cross Site Scripting', también conocido como XSS */
    }

    // Configura la gestion de autenticacion y la encriptacion de la contrasenia
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        /* le decimos que la seguridad (auth) mi PROPIO servicio de usuarios 'appUserDetailsService' 
        y con que llave tiene que encriptar las claves 'bCryptPasswordEncoder' */
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
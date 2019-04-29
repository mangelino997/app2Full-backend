package ar.com.draimo.jitws;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Define la clase que inicializa el contexto de spring
 * @author blas
 */
public class ServletInitializer extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JitwsApplication.class);
    }
    
}

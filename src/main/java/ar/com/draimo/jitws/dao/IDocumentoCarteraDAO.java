//Paquete al que pertenece la interfaz
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.DocumentoCartera;
import ar.com.draimo.jitws.model.Empresa;
import ar.com.draimo.jitws.model.TipoDocumentoCartera;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DAO DocumentoCartera
 * Define los metodos particulares contra la base de datos
 * @author blas
 */

public interface IDocumentoCarteraDAO extends JpaRepository<DocumentoCartera, Integer> {
    
    //Obtiene el ultimo registro
    public DocumentoCartera findTopByOrderByIdDesc();
    
    //Obtiene una lista por Empresa
    public List<DocumentoCartera> findByEmpresa(Empresa empresa);
    
    //Obtiene una lista por TipoDocumentoCartera
    public List<DocumentoCartera> findByTipoDocumentoCartera(TipoDocumentoCartera tipoDocumentoCartera);
    
}
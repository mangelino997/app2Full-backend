//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IDocumentoCarteraDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoCarteraDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.DocumentoCartera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de DocumentoCartera
 * @author blas
 */

@Service
public class DocumentoCarteraService {

    //Define la referencia al DAO
    @Autowired
    IDocumentoCarteraDAO elementoDAO;

    //Define la referencia al DAO de tipoDocumentoCartera
    @Autowired
    ITipoDocumentoCarteraDAO tipoDocumentoCarteraDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        DocumentoCartera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<DocumentoCartera> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa 
    public List<DocumentoCartera> listarPorEmpresa(int idEmpresa) {
            return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }
    
    //Obtiene una lista por TipoDocumentoCartera
    public List<DocumentoCartera> listarPorTipoDocumentoCartera(int idTipoDocumentoCartera) {
            return elementoDAO.findByTipoDocumentoCartera(
                    tipoDocumentoCarteraDAO.findById(idTipoDocumentoCartera).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public DocumentoCartera agregar(DocumentoCartera elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(DocumentoCartera elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
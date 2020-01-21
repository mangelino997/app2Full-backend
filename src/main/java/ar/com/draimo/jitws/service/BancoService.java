//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBancoDAO;
import ar.com.draimo.jitws.dao.ITipoDocumentoDAO;
import ar.com.draimo.jitws.dto.InitBancoDTO;
import ar.com.draimo.jitws.model.Banco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Banco
 * @author blas
 */

@Service
public class BancoService {
    
    //Define la referencia al dao
    @Autowired
    IBancoDAO elementoDAO;
    
    //Define la referencia al dao tipo doc
    @Autowired
    ITipoDocumentoDAO tipoDocumentoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitBancoDTO inicializar(int idRol, int idsubopcion) {
        InitBancoDTO elemento = new InitBancoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idsubopcion));
        elemento.setTipoDocumentos(tipoDocumentoDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Banco elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<Banco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Banco> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll(): 
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Banco agregar(Banco elemento) {
        elemento = formatearStrings(elemento);
        elemento.setTipoDocumento(tipoDocumentoDAO.findById(8).get());
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Banco elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Banco formatearStrings(Banco elemento) {
        elemento.setNombre(elemento.getNombre().trim().toUpperCase());
        if(elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().toLowerCase().trim());
        }
        return elemento;
    }
    
}
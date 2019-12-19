//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipConceptoSueldoGrupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipConceptoSueldoGrupoDAO;
import ar.com.draimo.jitws.dao.ITipoConceptoSueldoDAO;
import ar.com.draimo.jitws.dto.InitAfipCptoSueldoGrupoDTO;

/**
 * Service AfipConceptoSueldoGrupo
 *
 * @author blas
 */
@Service
public class AfipConceptoSueldoGrupoService {

    //Define el DAO
    @Autowired
    IAfipConceptoSueldoGrupoDAO elementoDAO;

    //Define el DAO
    @Autowired
    ITipoConceptoSueldoDAO tipoConceptoSueldoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitAfipCptoSueldoGrupoDTO inicializar(int idRol, int idSubopcion) {
        InitAfipCptoSueldoGrupoDTO elemento = new InitAfipCptoSueldoGrupoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setTipoConceptoSueldos(tipoConceptoSueldoDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipConceptoSueldoGrupo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipConceptoSueldoGrupo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<AfipConceptoSueldoGrupo> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por TipoConceptoSueldo
    public List<AfipConceptoSueldoGrupo> listarPorTipoConceptoSueldo(int idTipoConceptoSueldo) {
        return elementoDAO.findByTipoConceptoSueldo(tipoConceptoSueldoDAO.findById(idTipoConceptoSueldo).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipConceptoSueldoGrupo agregar(AfipConceptoSueldoGrupo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipConceptoSueldoGrupo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private AfipConceptoSueldoGrupo formatearStrings(AfipConceptoSueldoGrupo elemento) {
        elemento.setNombre(elemento.getNombre().trim().toUpperCase());
        return elemento;
    }

}

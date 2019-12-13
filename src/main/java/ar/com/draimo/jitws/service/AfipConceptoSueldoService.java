//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipConceptoSueldo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipConceptoSueldoDAO;
import ar.com.draimo.jitws.dao.IAfipConceptoSueldoGrupoDAO;

/**
 * Service AfipConceptoSueldo
 *
 * @author blas
 */
@Service
public class AfipConceptoSueldoService {

    //Define el DAO
    @Autowired
    IAfipConceptoSueldoDAO elementoDAO;

    //Define el DAO
    @Autowired
    IAfipConceptoSueldoGrupoDAO sueldoGrupoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipConceptoSueldo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipConceptoSueldo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<AfipConceptoSueldo> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por ConceptoSueldoGrupo
    public List<AfipConceptoSueldo> listarPorConceptoSueldoGrupo(int idConceptoSueldoGrupo) {
        return elementoDAO.findByAfipConceptoSueldoGrupo(sueldoGrupoDAO.findById(idConceptoSueldoGrupo).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipConceptoSueldo agregar(AfipConceptoSueldo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipConceptoSueldo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private AfipConceptoSueldo formatearStrings(AfipConceptoSueldo elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

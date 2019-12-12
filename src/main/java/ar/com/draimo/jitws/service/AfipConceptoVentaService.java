//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipConceptoVenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipConceptoVentaDAO;

/**
 * Service afipConcepto
 *
 * @author blas
 */
@Service
public class AfipConceptoVentaService {

    //Define el DAO
    @Autowired
    IAfipConceptoVentaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipConceptoVenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipConceptoVenta> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<AfipConceptoVenta> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipConceptoVenta agregar(AfipConceptoVenta elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipConceptoVenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private AfipConceptoVenta formatearStrings(AfipConceptoVenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }

}

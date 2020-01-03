//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IMedioPagoDAO;
import ar.com.draimo.jitws.model.MedioPago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de MedioPago
 *
 * @author blas
 */
@Service
public class MedioPagoService {

    //Define el dao
    @Autowired
    IMedioPagoDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MedioPago elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<MedioPago> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Nombre
    public List<MedioPago> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista de halibitados para ingreso
    public List<MedioPago> listarParaIngreso() {
        return elementoDAO.findByEstaActivoIngresoTrue();
    }

    //Obtiene una lista de halibitados para egreso
    public List<MedioPago> listarParaEgreso() {
        return elementoDAO.findByEstaActivoEgresoTrue();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public MedioPago agregar(MedioPago elemento) {
        elemento = formatearString(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(MedioPago elemento) {
        elementoDAO.save(formatearString(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private MedioPago formatearString(MedioPago elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
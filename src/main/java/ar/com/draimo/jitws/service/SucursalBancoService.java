//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBancoDAO;
import ar.com.draimo.jitws.dao.ISucursalBancoDAO;
import ar.com.draimo.jitws.model.SucursalBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Sucursal Banco
 *
 * @author blas
 */
@Service
public class SucursalBancoService {

    //Define la referencia al dao
    @Autowired
    ISucursalBancoDAO elementoDAO;

    //Define la referencia al dao banco
    @Autowired
    IBancoDAO bancoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SucursalBanco elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<SucursalBanco> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<SucursalBanco> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por banco
    public List<SucursalBanco> listarPorBanco(int idBanco) {
        //Retorna los datos
        return elementoDAO.findByBanco(bancoDAO.findById(idBanco));
    }

    //Obtiene una lista por nombre de banco
    public List<SucursalBanco> listarPorNombreBanco(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll() : elementoDAO.findByBanco_NombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SucursalBanco agregar(SucursalBanco elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SucursalBanco elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private SucursalBanco formatearStrings(SucursalBanco elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

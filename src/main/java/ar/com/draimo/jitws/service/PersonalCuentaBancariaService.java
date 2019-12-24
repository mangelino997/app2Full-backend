//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.IPersonalCuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.model.PersonalCuentaBancaria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio PersonalCuentaBancaria
 *
 * @author blas
 */
@Service
public class PersonalCuentaBancariaService {

    //Define la referencia al dao 
    @Autowired
    IPersonalCuentaBancariaDAO elementoDAO;

    //Define la referencia al dao proveedor
    @Autowired
    IPersonalDAO personalDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PersonalCuentaBancaria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<PersonalCuentaBancaria> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Personal
    public List<PersonalCuentaBancaria> listarPorPersonal(int idPersonal) {
        return elementoDAO.findByPersonal(personalDAO.findById(idPersonal).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalCuentaBancaria agregar(PersonalCuentaBancaria elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalCuentaBancaria actualizar(PersonalCuentaBancaria elemento) {
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}
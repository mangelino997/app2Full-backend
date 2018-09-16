package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IVehiculoDAO;
import ar.com.wecoode.jitws.model.Vehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Vehiculo
 * @author blas
 */

@Service
public class VehiculoService {

    //Define la referencia al dao
    @Autowired
    IVehiculoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Vehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Vehiculo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por alias
    public List<Vehiculo> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Obtiene una lista por alias filtrado por remolque
    public List<Vehiculo> listarPorAliasFiltroRemolque(String alias) {
        return elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(alias);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo agregar(Vehiculo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getDominio() + " - " + elemento.getNumeroInterno());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Vehiculo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Vehiculo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Vehiculo formatearStrings(Vehiculo elemento) {
        elemento.setDominio(elemento.getDominio().trim());
        elemento.setNumeroInterno(elemento.getNumeroInterno().trim());
        elemento.setNumeroMotor(elemento.getNumeroMotor().trim());
        elemento.setNumeroChasis(elemento.getNumeroChasis().trim());
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}

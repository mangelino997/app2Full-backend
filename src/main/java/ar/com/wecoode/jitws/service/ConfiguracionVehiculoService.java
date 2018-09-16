package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IConfiguracionVehiculoDAO;
import ar.com.wecoode.jitws.dao.IMarcaVehiculoDAO;
import ar.com.wecoode.jitws.dao.ITipoVehiculoDAO;
import ar.com.wecoode.jitws.model.ConfiguracionVehiculo;
import ar.com.wecoode.jitws.model.MarcaVehiculo;
import ar.com.wecoode.jitws.model.TipoVehiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Configuracion Vehiculo
 * @author blas
 */

@Service
public class ConfiguracionVehiculoService {
    
    //Define la referencia al dao
    @Autowired
    IConfiguracionVehiculoDAO elementoDAO;
    
    //Define la referencia a tipo vehiculo dao
    @Autowired
    ITipoVehiculoDAO tipoVehiculoDAO;
    
    //Define la referencia a marca vehiculo dao
    @Autowired
    IMarcaVehiculoDAO marcaVehiculoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ConfiguracionVehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<ConfiguracionVehiculo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por tipo vehiculo y marca vehiculo
    public List<ConfiguracionVehiculo> listarPorTipoVehiculoMarcaVehiculo(int idTipoVehiculo, int idMarcaVehiculo) {
        //Obtiene el tipo vehiculo por id
        Optional<TipoVehiculo> tipoVehiculo = tipoVehiculoDAO.findById(idTipoVehiculo);
        //Obtiene la marca vehiculo por id
        Optional<MarcaVehiculo> marcaVehiculo = marcaVehiculoDAO.findById(idMarcaVehiculo);
        //Retorna la lista por tipo vehiculo y marca vehiculo
        return elementoDAO.findByTipoVehiculoAndMarcaVehiculo(tipoVehiculo, marcaVehiculo);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ConfiguracionVehiculo agregar(ConfiguracionVehiculo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ConfiguracionVehiculo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Eliminar un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(ConfiguracionVehiculo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private ConfiguracionVehiculo formatearStrings(ConfiguracionVehiculo elemento) {
        elemento.setModelo(Funcion.primerLetraAMayuscula(elemento.getModelo().trim()));
        elemento.setDescripcion(Funcion.primerLetraAMayuscula(elemento.getDescripcion().trim()));
        return elemento;
    }
    
}

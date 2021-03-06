//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IConfiguracionVehiculoDAO;
import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.ITipoVehiculoDAO;
import ar.com.draimo.jitws.dto.InitConfiguracionVehiculoDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.ConfiguracionVehiculo;
import ar.com.draimo.jitws.model.MarcaVehiculo;
import ar.com.draimo.jitws.model.TipoVehiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    //Define la subopcion pestania service
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;

    //Obtiene la lista completa
    public InitConfiguracionVehiculoDTO inicializar(int rol, int subopcion) {
        InitConfiguracionVehiculoDTO p = new InitConfiguracionVehiculoDTO();
         p.setMarcaVehiculos(marcaVehiculoDAO.findAll());
        p.setTipoVehiculos(tipoVehiculoDAO.findAll());
        p.setUltimoId(obtenerSiguienteId());
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(rol, subopcion));
        return p;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ConfiguracionVehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ConfiguracionVehiculo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por tipo vehiculo y marca vehiculo
    public List<ConfiguracionVehiculo> listarPorTipoYMarca(int idTipoVehiculo, int idMarcaVehiculo) {
        //Obtiene el tipo vehiculo por id
        Optional<TipoVehiculo> tipoVehiculo = tipoVehiculoDAO.findById(idTipoVehiculo);
        //Obtiene la marca vehiculo por id
        Optional<MarcaVehiculo> marcaVehiculo = marcaVehiculoDAO.findById(idMarcaVehiculo);
        //Retorna la lista por tipo vehiculo y marca vehiculo
        return elementoDAO.findByTipoVehiculoAndMarcaVehiculo(tipoVehiculo, marcaVehiculo);
    }
    
    //Obtiene una lista por marca de vehiculo
    public List<ConfiguracionVehiculo> listarPorFiltros(int idTipoVehiculo, int idMarcaVehiculo) {
        return elementoDAO.listarPorFiltros(idTipoVehiculo, idMarcaVehiculo);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ConfiguracionVehiculo agregar(ConfiguracionVehiculo elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ConfiguracionVehiculo elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Eliminar un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Controla longitudes de atributos short
    private void controlarLongitud(ConfiguracionVehiculo elemento) {
        //Obtiene longitud de cant. ejes, si supera 2 retorna error
        String ejes = String.valueOf(elemento.getCantidadEjes());
        if (ejes.length()>2) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CANTIDAD EJES");
        }
    }
    
    //Formatea los strings
    private ConfiguracionVehiculo formatearStrings(ConfiguracionVehiculo elemento) {
        elemento.setModelo(elemento.getModelo().trim());
        if(elemento.getDescripcion() != null) {
            elemento.setDescripcion(elemento.getDescripcion().trim());
        }
        return elemento;
    }
    
}
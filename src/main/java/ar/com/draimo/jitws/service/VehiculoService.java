package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IMarcaVehiculoDAO;
import ar.com.draimo.jitws.dao.ITipoVehiculoDAO;
import ar.com.draimo.jitws.dao.IVehiculoDAO;
import ar.com.draimo.jitws.model.Vehiculo;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Vehiculo
 *
 * @author blas
 */
@Service
public class VehiculoService {

    //Define la referencia al dao
    @Autowired
    IVehiculoDAO elementoDAO;

    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao tipo vehiculo
    @Autowired
    ITipoVehiculoDAO tipoVehiculoDAO;

    //Define la referencia al dao marca vehiculo
    @Autowired
    IMarcaVehiculoDAO marcaVehiculoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Vehiculo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Vehiculo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por alias
    public List<Vehiculo> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }

    //Obtiene una lista por alias filtrado por no remolque
    public List<Vehiculo> listarPorAliasYRemolqueFalse(String alias) {
        return elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueFalse(alias);
    }

    //Obtiene una lista por alias filtrado por remolque
    public List<Vehiculo> listarPorAliasYRemolqueTrue(String alias) {
        return elementoDAO.findByAliasContainingAndConfiguracionVehiculo_TipoVehiculo_EsRemolqueTrue(alias);
    }

    //Obtiene una lista por empresa, tipo de vehiculo y marca de vehiculo
    public List<Vehiculo> listarFiltro(int idEmpresa, int idTipoVehiculo, int idMarcaVehiculo) {
        return elementoDAO.listarPorConfig(idEmpresa, idTipoVehiculo, idMarcaVehiculo);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Vehiculo agregar(Vehiculo elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Vehiculo elemento) {
        elemento.setAlias(elemento.getDominio() + " - "
            + elemento.getConfiguracionVehiculo().getTipoVehiculo().getNombre() + " - "
            + elemento.getConfiguracionVehiculo().getMarcaVehiculo().getNombre());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Vehiculo elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento.setAlias(elemento.getDominio() + " - "
            + elemento.getConfiguracionVehiculo().getTipoVehiculo().getNombre() + " - "
            + elemento.getConfiguracionVehiculo().getMarcaVehiculo().getNombre());
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
        if (elemento.getNumeroInterno() != null) {
            elemento.setNumeroInterno(elemento.getNumeroInterno().trim());
        }
        if (elemento.getNumeroMotor() != null) {
            elemento.setNumeroMotor(elemento.getNumeroMotor().trim());
        }
        if (elemento.getNumeroChasis() != null) {
            elemento.setNumeroChasis(elemento.getNumeroChasis().trim());
        }
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}
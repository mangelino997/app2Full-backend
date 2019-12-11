//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeraDAO;
import ar.com.draimo.jitws.dao.ICuentaBancariaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.ITipoChequeraDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.InitChequeraDTO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Chequera;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Chequera
 * @author blas
 */

@Service
public class ChequeraService {

    //Define la referencia al DAO
    @Autowired
    IChequeraDAO elementoDAO;

    //Define la referencia al DAO de CuentaBancaria
    @Autowired
    ICuentaBancariaDAO cuentaBancariaDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    ITipoChequeraDAO tipoChequeraDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitChequeraDTO inicializar(int idEmpresa, int idRol, int idSubopcion) {
        InitChequeraDTO elemento = new InitChequeraDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setTipoChequeras(tipoChequeraDAO.findAll());
        elemento.setCuentaBancariaConCheques(cuentaBancariaDAO.listarConChequerasPorEmpresa(idEmpresa));
        elemento.setCuentaBancariaConsultas(cuentaBancariaDAO.listarPorEmpresa(idEmpresa));
        elemento.setChequeras(elementoDAO.listarPorEmpresa(idEmpresa));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Chequera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Chequera> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa de cuentaBancaria
    public List<Chequera> listarPorEmpresa(int idEmpresa) {
            return elementoDAO.listarPorEmpresa(idEmpresa);
    }
    
    //Obtiene una lista por CuentaBancaria
    public List<Chequera> listarPorCuentaBancaria(int idCuentaBancaria) {
            return elementoDAO.findByCuentaBancaria(cuentaBancariaDAO.findById(idCuentaBancaria).get());
    }
    
    //Realiza valicion para ver si un numero pertenece a la chequera
    public List<Chequera> validarDesdeHasta(int idCuentaBancaria, int idTipoChequera,int desdeHasta) {
            return elementoDAO.listarPorCtaBancariaTipoChequeraDesdeHasta(idCuentaBancaria, idTipoChequera, desdeHasta);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Chequera agregar(Chequera elemento) throws Exception {
        Date fecha = new Date(new java.util.Date().getTime());
        elemento.setFechaAlta(fecha);
        controlarError(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Chequera elemento) throws Exception {
        controlarError(elemento);
        elementoDAO.save(elemento);
    }

    //Controla los errores
    @Transactional(rollbackFor = Exception.class)
    private void controlarError(Chequera elemento) throws Exception {
        if(elemento.getDesde()>elemento.getHasta()) {
            throw new Exception("DESDE " +MensajeRespuesta.ELEMENTO_MENOR +" HASTA");
        }
        List<Chequera> chequerasDesde = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getDesde());
        List<Chequera> chequerasHasta = validarDesdeHasta(
                elemento.getCuentaBancaria().getId(), elemento.getTipoChequera().getId(),
                elemento.getHasta());
        if(!chequerasDesde.isEmpty()) {
            throw new Exception(MensajeRespuesta.EXISTENTE_PARA_CUENTA_BANCARIA + " DESDE");
        }
        if(!chequerasHasta.isEmpty()) {
            throw new Exception(MensajeRespuesta.EXISTENTE_PARA_CUENTA_BANCARIA + " HASTA");
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
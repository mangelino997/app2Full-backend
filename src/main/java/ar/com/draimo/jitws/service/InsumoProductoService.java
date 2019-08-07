package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.InsumoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IInsumoProductoDAO;
import java.math.BigDecimal;

/**
 * Servicio InsumoProducto
 * @author blas
 */

@Service
public class InsumoProductoService {
    
    //Define la referencia al dao
    @Autowired
    IInsumoProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        InsumoProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<InsumoProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<InsumoProducto> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Obtiene una lista de combustibles
    public List<InsumoProducto> listarCombustibles() {
        return elementoDAO.findByRubroProducto_EsCombustibleTrue();
    }
    
    //Obtiene una lista de insumos
    public List<InsumoProducto> listarInsumos() {
        return elementoDAO.findByRubroProducto_EsInsumoTrue();
    }
    
    //Obtiene precio unitario por insumo
    public BigDecimal obtenerPrecioUnitario(int idInsumoProducto) {
        InsumoProducto insumoProducto = elementoDAO.findById(idInsumoProducto).get();
        return insumoProducto.getPrecioUnitarioViaje();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public InsumoProducto agregar(InsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        if(elemento.getPrecioUnitarioVenta()== null){
            elemento.setPrecioUnitarioVenta(new BigDecimal(0));
        }
        if(elemento.getPrecioUnitarioViaje()== null){
            elemento.setPrecioUnitarioViaje(new BigDecimal(0));
        }
        if(elemento.getItcNeto()== null){
            elemento.setItcNeto(new BigDecimal(0));
        }
        if(elemento.getItcPorLitro()== null){
            elemento.setItcPorLitro(new BigDecimal(0));
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(InsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        if(elemento.getPrecioUnitarioVenta()== null){
            elemento.setPrecioUnitarioVenta(new BigDecimal(0));
        }
        if(elemento.getPrecioUnitarioViaje()== null){
            elemento.setPrecioUnitarioViaje(new BigDecimal(0));
        }
        if(elemento.getItcNeto()== null){
            elemento.setItcNeto(new BigDecimal(0));
        }
        if(elemento.getItcPorLitro()== null){
            elemento.setItcPorLitro(new BigDecimal(0));
        }
        establecerAlias(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Establece el alias
    public void establecerAlias(InsumoProducto elemento) {
        elemento.setAlias(elemento.getId() +" - "+ elemento.getNombre() + " - "+
                elemento.getRubroProducto().getNombre() + " - " + 
                elemento.getMarcaProducto().getNombre());
    }
    
    //Formatea los strings
    private InsumoProducto formatearStrings(InsumoProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}

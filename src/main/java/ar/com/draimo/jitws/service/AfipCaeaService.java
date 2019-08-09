package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.model.AfipCaea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCaeaDAO;
import java.util.ArrayList;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Servicio AfipCaea
 * @author blas
 */

@Service
public class AfipCaeaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipCaeaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipCaea elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipCaea> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado de anios
    public List<Short> listarAnios() {
        List<Short> anios = new ArrayList<>();
        short anio = Funcion.anioInicio;
        for (short i = anio; i < anio+15; i++) {
            anios.add((short)i);
        }
        return anios;
    }
    
    //Obtiene un registro por empresa, anio, mes y quincena
    public AfipCaea obtenerPorEmpresaYPeriodoOrden(int idEmpresa, short anio, short mes, short quincena) {
        return elementoDAO.obtenerPorEmpresaYPeriodoOrden(idEmpresa, anio, mes, quincena);
    }
    
    //Obtiene un listado de registros por empresa y anio
    public List<AfipCaea> listarPorEmpresaYAnio(int idEmpresa, short anio) {
        return elementoDAO.listarPorEmpresaYAnio(idEmpresa, anio);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCaea agregar(AfipCaea elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        String quincena = String.valueOf(elemento.getQuincena());
        String mes = String.valueOf(elemento.getMes());
        //Obtiene la longitud del anio, si supera 4 retorna mensaje de error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        //Obtiene la longitud de quincena, si supera 1 retorna mensaje de error
        if (quincena.length()>1) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en QUINCENA");
        }
        //obtiene longitud de mes, si supera dos, retorna mensaje de error
        if (mes.length()>2) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en MES");
        }
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCaea elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String anio = String.valueOf(elemento.getAnio());
        String quincena = String.valueOf(elemento.getQuincena());
        String mes = String.valueOf(elemento.getMes());
        //Obtiene la longitud del anio, si supera 4 retorna mensaje de error
        if (anio.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO");
        }
        //Obtiene la longitud de quincena, si supera 1 retorna mensaje de error
        if (quincena.length()>1) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en QUINCENA");
        }
        //obtiene longitud de mes, si supera dos, retorna mensaje de error
        if (mes.length()>2) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en MES");
        }
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private AfipCaea formatearStrings(AfipCaea elemento) {
        elemento.setNumeroCAEA(elemento.getNumeroCAEA().trim());
        return elemento;
    }
    
}

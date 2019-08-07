package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Empresa
 *
 * @author blas
 */
@Service
public class EmpresaService {

    //Define la referencia al dao
    @Autowired
    IEmpresaDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Empresa elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Empresa> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista de empresas activas
    public List<Empresa> listarActivas() {
        return elementoDAO.findByEstaActivaTrue();
    }

    //Obtiene por razon social
    public List<Empresa> listarPorRazonSocial(String razonSocial) {
        if (razonSocial.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByRazonSocialContaining(razonSocial);
        }
    }

    //Obtiene por razon social
    public List<Empresa> listarPorRazonSocialYCAEAHabilitado(String razonSocial) {
        return elementoDAO.findByRazonSocialContainingAndFeCAEATrue(razonSocial);
    }

    //Obtiene por razon social y esta activo
    public List<Empresa> listarPorRazonSocialYActiva(String razonSocial) {
        return elementoDAO.findByRazonSocialContainingAndEstaActivaTrue(razonSocial);
    }

    //Obtiene por razon social, esta activo y fe
    public List<Empresa> listarPorRazonSocialYActivaYFe(String razonSocial) {
        return elementoDAO.findByRazonSocialContainingAndEstaActivaTrueAndFeTrue(razonSocial);
    }

    //Obtiene una lista de usuarios por empresa
    public List<Empresa> listarEmpresasPorUsuario(int idUsuario) {
        List<Empresa> empresas = elementoDAO.listarPorUsuarioYMostrarTrue(idUsuario);
        return empresas;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Empresa agregar(Empresa elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Empresa elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Empresa formatearStrings(Empresa elemento) {
        elemento.setRazonSocial(elemento.getRazonSocial().trim().toUpperCase());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setCuit(elemento.getCuit().trim());
        if (elemento.getNumeroIIBB() != null) {
            elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        }
        elemento.setAbreviatura(elemento.getAbreviatura().trim());
        return elemento;
    }

}
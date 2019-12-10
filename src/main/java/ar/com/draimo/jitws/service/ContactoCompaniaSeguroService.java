package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.IContactoCompaniaSeguroDAO;
import ar.com.draimo.jitws.dao.ITipoContactoDAO;
import ar.com.draimo.jitws.dto.InitContactoGenericoDTO;
import ar.com.draimo.jitws.model.CompaniaSeguro;
import ar.com.draimo.jitws.model.ContactoCompaniaSeguro;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Compania Seguro
 * @author blas
 */

@Service
public class ContactoCompaniaSeguroService {
    
    //Define la referencia al dao
    @Autowired
    IContactoCompaniaSeguroDAO elementoDAO;
    
    //Define la referencia al dao de compania seguro
    @Autowired
    ICompaniaSeguroDAO companiaSeguroDAO;
    
    //Define la referencia al dao tipoContacto
    @Autowired
    ITipoContactoDAO tipoContactoDAO;

    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitContactoGenericoDTO inicializar(int rol, int subopcion) {
        InitContactoGenericoDTO p = new InitContactoGenericoDTO();
        p.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(rol, subopcion));
        p.setTipoContactos(tipoContactoDAO.findAll());
        p.setUltimoId(obtenerSiguienteId());
        return p;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ContactoCompaniaSeguro elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ContactoCompaniaSeguro> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoCompaniaSeguro> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll() :
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por compania seguro
    public List<ContactoCompaniaSeguro> listarPorCompaniaSeguro(int idCompaniaSeguro) {
        //Obtiene la compania seguro por id
        Optional<CompaniaSeguro> elemento = companiaSeguroDAO.findById(idCompaniaSeguro);
        //Retorna los datos
        return elementoDAO.findByCompaniaSeguro(elemento);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoCompaniaSeguro agregar(ContactoCompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoCompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ContactoCompaniaSeguro formatearStrings(ContactoCompaniaSeguro elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getTelefonoFijo() != null) {
            elemento.setTelefonoFijo(elemento.getTelefonoFijo().trim());
        }
        if(elemento.getTelefonoMovil() != null) {
            elemento.setTelefonoMovil(elemento.getTelefonoMovil().trim());
        }
        if(elemento.getCorreoelectronico() != null) {
            elemento.setCorreoelectronico(elemento.getCorreoelectronico().trim().toLowerCase());
        }
        return elemento;
    }
    
}
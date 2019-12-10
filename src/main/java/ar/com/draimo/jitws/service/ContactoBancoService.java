//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IContactoBancoDAO;
import ar.com.draimo.jitws.dao.ISucursalBancoDAO;
import ar.com.draimo.jitws.dao.ITipoContactoDAO;
import ar.com.draimo.jitws.dto.InitContactoGenericoDTO;
import ar.com.draimo.jitws.model.ContactoBanco;
import ar.com.draimo.jitws.model.SucursalBanco;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Contacto Banco
 * @author blas
 */

@Service
public class ContactoBancoService {
    
    //Define la referencia al dao
    @Autowired
    IContactoBancoDAO elementoDAO;
    
    //Define la referencia a sucursal banco dao
    @Autowired
    ISucursalBancoDAO sucursalBancoDAO;
    
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
        ContactoBanco elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ContactoBanco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<ContactoBanco> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll(): 
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista de contactos por sucursal banco
    public List<ContactoBanco> listarPorSucursalBanco(int idSucursalBanco) {
        //Obtiene la sucursal banco por id
        Optional<SucursalBanco> elemento = sucursalBancoDAO.findById(idSucursalBanco);
        //Retorna los datos
        return elementoDAO.findBySucursalBanco(elemento);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ContactoBanco agregar(ContactoBanco elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ContactoBanco elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ContactoBanco formatearStrings(ContactoBanco elemento) {
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
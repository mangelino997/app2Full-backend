//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBugImagenDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.dao.IModuloDAO;
import ar.com.draimo.jitws.dao.ISoporteDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
import ar.com.draimo.jitws.dto.InitSoporteDTO;
import ar.com.draimo.jitws.model.BugImagen;
import ar.com.draimo.jitws.model.Soporte;
import ar.com.draimo.jitws.model.Subopcion;
import ar.com.draimo.jitws.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio Soporte
 *
 * @author blas
 */
@Service
public class SoporteService {

    //Define la referencia al dao
    @Autowired
    ISoporteDAO elementoDAO;

    //Define la referencia al dao de usuario
    @Autowired
    IUsuarioDAO usuarioDAO;

    //Define la referencia al dao de moduloDAO
    @Autowired
    IModuloDAO moduloDAO;

    //Define la referencia al dao de empresaDAO
    @Autowired
    IEmpresaDAO empresaDAO;

    //Define la referencia al dao de subopcion
    @Autowired
    ISubopcionDAO subopcionDAO;

    //Define la referencia al dao de bugImagen
    @Autowired
    IBugImagenDAO bugImagenDAO;

    //Define la referencial al servicio de bugImagen
    @Autowired
    BugImagenService bugImagenService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitSoporteDTO inicializar() {
        InitSoporteDTO elemento = new InitSoporteDTO();
        elemento.setEmpresas(empresaDAO.findAll());
        elemento.setModulos(moduloDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Soporte elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Soporte> elementos = elementoDAO.findAll();
        return retornarObjeto(elementos, null, true);
    }

    //Obtiene la lista completa
    public Object obtenerPorId(int id) throws IOException {
        Soporte elemento = elementoDAO.findById(id).get();
        if (elemento.getBugImagen() == null) {
            elemento.setBugImagen(new BugImagen());
        }
        return retornarObjeto(null,elemento, false);
    }

    //Obtiene una lista por alias y usuario
    public Object listarPorAliasContainingYUsuario(int idUsuario, String alias) throws IOException {
        Usuario usuario = usuarioDAO.findById(idUsuario).get();
        List<Soporte> elementos = alias.equals("*") ? 
                elementoDAO.listarPorUsuario(usuario.getId(), usuario.getRol().getId())
                : elementoDAO.findByUsuarioAndAliasContaining(usuario, alias);
        return retornarObjeto(elementos, null, true);
    }

    //Obtiene una lista por usuario
    public Object listarPorUsuario(int idUsuario) throws IOException {
        Usuario u = usuarioDAO.findById(idUsuario).get();
        List<Soporte> elementos = elementoDAO.listarPorUsuario(u.getId(), u.getRol().getId());
        return retornarObjeto(elementos,null, true);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Soporte agregar(String soporteString, MultipartFile archivo) throws IOException {
        Soporte elemento = new ObjectMapper().readValue(soporteString, Soporte.class);
        elemento.setFecha(new Timestamp(new java.util.Date().getTime()));
        BugImagen bug = !archivo.getOriginalFilename().equals("") ? bugImagenService.agregar(archivo, false) : null;
        BugImagen u = bug != null ? bugImagenDAO.saveAndFlush(bug) : null;
        elemento.setBugImagen(u);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String soporteString, MultipartFile archivo) throws IOException {
        Soporte elemento = new ObjectMapper().readValue(soporteString, Soporte.class);
        if (archivo.getOriginalFilename().equals("")) {
            if (elemento.getBugImagen().getId() != 0) {
                bugImagenDAO.deleteById(elemento.getBugImagen().getId());
            }
            elemento.setBugImagen(null);
        } else {
            BugImagen bug, f;
            f = elemento.getBugImagen().getId() != 0 ?bugImagenService.actualizar(
                    elemento.getBugImagen().getId(), archivo, false):bugImagenService.agregar(archivo, false);
            bug = elemento.getBugImagen().getId() != 0 ?bugImagenDAO.save(f):
            bugImagenDAO.saveAndFlush(f);
            elemento.setBugImagen(bug);
        }
        establecerAlias(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Retorna un object aplicando filtros
    private Object retornarObjeto(List<Soporte> elementos,Soporte elemento, boolean b) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = b?SimpleBeanPropertyFilter
                .serializeAllExcept("datos"):SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", theFilter);
        String string = mapper.writer(filters).writeValueAsString(b?elementos:elemento);
        return mapper.readValue(string, Object.class);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Soporte elemento) {
        Subopcion s = subopcionDAO.findById(elemento.getSubopcion().getId()).get();
        String submodulo = s.getSubmodulo().getNombre();
        String modulo = s.getSubmodulo().getModulo().getNombre();
        elemento.setAlias(elemento.getId() + " - " + modulo + " - " + submodulo 
                + " - " + s.getNombre());
        elementoDAO.save(elemento);
    }

}
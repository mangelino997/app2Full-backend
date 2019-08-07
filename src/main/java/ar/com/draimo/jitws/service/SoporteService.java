package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBugImagenDAO;
import ar.com.draimo.jitws.dao.ISoporteDAO;
import ar.com.draimo.jitws.dao.ISubopcionDAO;
import ar.com.draimo.jitws.dao.IUsuarioDAO;
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
 *
 * @author blas
 */
@Service
public class SoporteService {

    @Autowired
    ISoporteDAO elementoDAO;

    @Autowired
    IUsuarioDAO usuarioDAO;

    @Autowired
    ISubopcionDAO subopcionDAO;

    @Autowired
    IBugImagenDAO bugImagenDAO;

    @Autowired
    BugImagenService bugImagenService;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Soporte elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<Soporte> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene la lista completa
    public Object obtenerPorId(int id) throws IOException {
        Soporte elemento = elementoDAO.findById(id).get();
        if (elemento.getBugImagen()==null) {
            elemento.setBugImagen(new BugImagen());
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por alias y usuario
    public Object listarPorAliasContainingYUsuario(int idUsuario, String alias) throws IOException {
        Usuario usuario = usuarioDAO.findById(idUsuario).get();
        List<Soporte> elementos;
        if (alias.equals("***")) {
            elementos = elementoDAO.findByUsuario(usuario);
        } else {
            elementos = elementoDAO.findByUsuarioAndAliasContaining(usuario, alias);
        }
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen",
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por usuario
    public Object listarPorUsuario(int idUsuario) throws IOException {
        List<Soporte> elementos = elementoDAO.findByUsuario(usuarioDAO.findById(idUsuario).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Soporte agregar(String soporteString, MultipartFile archivo) throws IOException {
        Soporte elemento = new ObjectMapper().readValue(soporteString, Soporte.class);
        elemento.setFecha(new Timestamp(new java.util.Date().getTime()));
        if (!archivo.getOriginalFilename().equals("")) {
            BugImagen u = bugImagenService.agregar(archivo, false);
            BugImagen bugImagen = bugImagenDAO.saveAndFlush(u);
            elemento.setBugImagen(bugImagen);
        } else {
            elemento.setBugImagen(null);
        }
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(String soporteString, MultipartFile archivo) throws IOException {
        Soporte elemento = new ObjectMapper().readValue(soporteString, Soporte.class);
        if (archivo.getOriginalFilename().equals("")) {
            if (elemento.getBugImagen().getId() != 0) {
                bugImagenDAO.deleteById(elemento.getBugImagen().getId());
                elemento.setBugImagen(null);
            } else {
                elemento.setBugImagen(null);
            }
        } else {
            if (elemento.getBugImagen().getId() != 0) {
                BugImagen f = bugImagenService.actualizar(elemento.getBugImagen().getId(), archivo, false);
                BugImagen bug = bugImagenDAO.save(f);
                elemento.setBugImagen(bug);
            } else {
                BugImagen u = bugImagenService.agregar(archivo, false);
                BugImagen bug = bugImagenDAO.saveAndFlush(u);
                elemento.setBugImagen(bug);
            }
        }
        establecerAlias(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Soporte elemento) {
        Subopcion s = subopcionDAO.findById(elemento.getSubopcion().getId()).get();
        String subopcion = s.getNombre();
        String submodulo = s.getSubmodulo().getNombre();
        String modulo = s.getSubmodulo().getModulo().getNombre();
        elemento.setAlias(elemento.getId() + " - " + modulo + " - " + submodulo + " - " + subopcion);
        elementoDAO.save(elemento);
    }

}

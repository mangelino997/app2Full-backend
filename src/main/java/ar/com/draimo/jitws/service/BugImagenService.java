package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBugImagenDAO;
import ar.com.draimo.jitws.model.BugImagen;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author blas
 */

@Service
public class BugImagenService {

    @Autowired
    IBugImagenDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        BugImagen elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene un registro por id
    public Object obtenerPorId(int id) throws IOException  {
        BugImagen bug = elementoDAO.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroImagen", 
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(bug);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public BugImagen agregar(MultipartFile archivo) throws IOException {
        BugImagen imagen = new BugImagen();
        imagen.setNombre(archivo.getOriginalFilename());
        imagen.setTipo(archivo.getContentType());
        imagen.setTamanio(archivo.getSize());
        imagen.setDatos(archivo.getBytes());
        return elementoDAO.saveAndFlush(imagen);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public BugImagen agregar(MultipartFile archivo, boolean opcion) throws IOException {
        BugImagen element = new BugImagen();
        element.setNombre(archivo.getOriginalFilename());
        element.setTipo(archivo.getContentType());
        element.setTamanio(archivo.getSize());
        element.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(element) : element;
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public BugImagen actualizar(int idFoto, MultipartFile archivo) throws IOException {
        BugImagen element = elementoDAO.findById(idFoto).get();
        element.setNombre(archivo.getOriginalFilename());
        element.setTipo(archivo.getContentType());
        element.setTamanio(archivo.getSize());
        element.setDatos(archivo.getBytes());
        return elementoDAO.save(element);
    }
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public BugImagen actualizar(int idFoto, MultipartFile archivo, boolean opcion) throws IOException {
        BugImagen element = elementoDAO.findById(idFoto).get();
        element.setNombre(archivo.getOriginalFilename());
        element.setTipo(archivo.getContentType());
        element.setTamanio(archivo.getSize());
        element.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.save(element) : element;
    }
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(BugImagen elemento) {
        elementoDAO.delete(elemento);
    }

}

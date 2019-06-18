package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBugImagenDAO;
import ar.com.draimo.jitws.model.BugImagen;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Optional;
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
    public BugImagen obtenerPorId(int id) throws JsonProcessingException, IOException {
        BugImagen imagen = elementoDAO.findById(id).get();
        return  imagen;
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
    public void actualizar(int idFoto, MultipartFile archivo) throws IOException {
        Optional<BugImagen> i = elementoDAO.findById(idFoto);
        BugImagen imagen = i.get();
        imagen.setNombre(archivo.getOriginalFilename());
        imagen.setTipo(archivo.getContentType());
        imagen.setTamanio(archivo.getSize());
        imagen.setDatos(archivo.getBytes());
        elementoDAO.save(imagen);
    }
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(BugImagen elemento) {
        elementoDAO.delete(elemento);
    }

}

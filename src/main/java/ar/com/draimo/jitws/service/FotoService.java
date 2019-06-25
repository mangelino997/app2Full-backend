package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IFotoDAO;
import ar.com.draimo.jitws.model.Foto;
import java.io.IOException;
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
public class FotoService {

    @Autowired
    IFotoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Foto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene por id
    public Foto obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }
    
    //Obtiene una lista por nombre
    public List<Foto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Foto agregar(MultipartFile archivo, boolean opcion) throws IOException {
        Foto foto = new Foto();
        foto.setNombre(archivo.getOriginalFilename());
        foto.setTipo(archivo.getContentType());
        foto.setTamanio(archivo.getSize());
        foto.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(foto) : foto;
    }
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Foto actualizar(int idPdf ,MultipartFile archivo, boolean opcion) throws IOException {
        Foto elemento = elementoDAO.findById(idPdf).get();
        elemento.setNombre(archivo.getOriginalFilename());
        elemento.setTipo(archivo.getContentType());
        elemento.setTamanio(archivo.getSize());
        elemento.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(elemento) : elemento;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Foto agregar(Foto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Foto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Foto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Foto formatearStrings(Foto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

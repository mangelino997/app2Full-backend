package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IFotoDAO;
import ar.com.draimo.jitws.model.Foto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
    public Object obtenerPorId(int id) throws IOException {
        Foto foto= elementoDAO.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroFoto", SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(foto);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<Foto> elementos;
        if(nombre.equals("***")) {
            elementos= elementoDAO.findAll();
        } else {
            elementos= elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", 
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
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

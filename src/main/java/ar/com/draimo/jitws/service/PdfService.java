package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.model.Pdf;
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
public class PdfService {

    @Autowired
    IPdfDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pdf elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene por id
    public Object obtenerPorId(int id) throws IOException {
        Pdf pdf = elementoDAO.findById(id).get();
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", 
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(pdf);
        return mapper.readValue(string, Object.class);
    }
    
    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<Pdf> pdf;
        if(nombre.equals("***")) {
            pdf =  elementoDAO.findAll();
        } else {
            pdf =  elementoDAO.findByNombreContaining(nombre);
        }
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", 
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(pdf);
        return mapper.readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf agregar(MultipartFile archivo, boolean opcion) throws IOException {
        Pdf foto = new Pdf();
        foto.setNombre(archivo.getOriginalFilename());
        foto.setTipo(archivo.getContentType());
        foto.setTamanio(archivo.getSize());
        foto.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(foto) : foto;
    }
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf actualizar(int idPdf ,MultipartFile archivo, boolean opcion) throws IOException {
        Pdf elemento = elementoDAO.findById(idPdf).get();
        elemento.setNombre(archivo.getOriginalFilename());
        elemento.setTipo(archivo.getContentType());
        elemento.setTamanio(archivo.getSize());
        elemento.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(elemento) : elemento;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf agregar(Pdf elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Pdf elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Pdf elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Pdf formatearStrings(Pdf elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}

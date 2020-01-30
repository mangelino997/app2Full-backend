//Paquete al que pertenece el servicio
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
 * Servicio de Foto
 * @author blas
 */

@Service
public class FotoService {

    //Referencia al DAO
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
        List<Foto> elementos= nombre.equals("*")?elementoDAO.findAll(): 
                    elementoDAO.findByNombreContaining(nombre);
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", 
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Foto agregar(MultipartFile archivo, String nombre, boolean opcion) throws IOException {
        Foto foto = new Foto();
        foto.setNombre(nombre);
        foto.setTipo(archivo.getContentType());
        foto.setTamanio(archivo.getSize());
        foto.setDatos(archivo.getBytes());
        return opcion ? elementoDAO.saveAndFlush(foto) : foto;
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Foto actualizar(int idFoto, String tabla, MultipartFile archivo, String nombre, boolean opcion) throws IOException {
        Foto elemento = idFoto == 0 ? new Foto() : elementoDAO.findById(idFoto).get();
        elemento.setNombre(nombre);
        elemento.setTipo(archivo.getContentType());
        elemento.setTamanio(archivo.getSize());
        elemento.setDatos(archivo.getBytes());
        elemento.setTabla(tabla);
        formatearStrings(elemento);
        return opcion ? elementoDAO.save(elemento) : elemento;
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Foto formatearStrings(Foto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
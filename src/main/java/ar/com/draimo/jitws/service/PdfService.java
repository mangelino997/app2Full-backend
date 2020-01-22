//Paquete al que pertenece el servicio
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
 * Servicio de pdf
 *
 * @author blas
 */
@Service
public class PdfService {

    //Referencia al dao
    @Autowired
    IPdfDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pdf elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene por id
    public Object obtenerPorId(int id) throws IOException {
        Pdf pdf = elementoDAO.findById(id).get();
        return aplicarFiltros(pdf, null);
    }

    //Obtiene una lista por nombre
    public Object listarPorNombre(String nombre) throws IOException {
        List<Pdf> pdf = nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
        return aplicarFiltros(null, pdf);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf agregar(MultipartFile archivo, String nombre, boolean opcion) throws IOException {
        Pdf pdf = new Pdf();
        pdf.setNombre(nombre);
        pdf.setTipo(archivo.getContentType());
        pdf.setTamanio(archivo.getSize());
        pdf.setDatos(archivo.getBytes());
        formatearStrings(pdf);
        return opcion ? elementoDAO.saveAndFlush(pdf) : pdf;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf actualizar(int idPdf, String tabla, MultipartFile archivo, String nombre, boolean opcion) throws IOException {
        Pdf elemento = idPdf == 0 ? new Pdf() : elementoDAO.findById(idPdf).get();
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
    private Pdf formatearStrings(Pdf elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

    //retorna un object despues de aplicar los filtros
    private Object aplicarFiltros(Pdf elemento, List<Pdf> elementos) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf",
                        SimpleBeanPropertyFilter.serializeAllExcept());
        String string = mapper.writer(filters).writeValueAsString(elemento!=null? elemento : elementos);
        return mapper.readValue(string, Object.class);
    }
    
}

//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.IPersonalFamiliarDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Personal;
import ar.com.draimo.jitws.model.PersonalFamiliar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase personal familiar
 *
 * @author blas
 */
@Service
public class PersonalFamiliarService {

    //Referencia al dao
    @Autowired
    IPersonalFamiliarDAO elementoDAO;

    //Referencia al dao de personal
    @Autowired
    IPersonalDAO personalDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PersonalFamiliar elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public Object listar() throws IOException {
        List<PersonalFamiliar> elementos = elementoDAO.findAll();
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por nombre
    public Object listarPorAlias(String alias) throws IOException {
        List<PersonalFamiliar> elementos = alias.equals("***") ? elementoDAO.findAll()
                : elementoDAO.findByAliasContaining(alias);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Obtiene una lista por personal
    public Object listarPorPersonal(int idPersonal) throws IOException {
        List<PersonalFamiliar> elementos = elementoDAO.findByPersonal(personalDAO.findById(idPersonal).get());
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elementos);
        return mapper.readValue(string, Object.class);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Object agregar(PersonalFamiliar elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elemento = elementoDAO.saveAndFlush(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    public Object establecerAlias(Object object) throws IOException {
        PersonalFamiliar elemento = PersonalFamiliar.class.cast(object);
        Personal p = personalDAO.findById(elemento.getPersonal().getId()).get();
        elemento.setAlias(String.valueOf(elemento.getId()) + " - "
                + elemento.getApellido() + " " + elemento.getNombre() + " - "
                + p.getNombreCompleto());
        elemento = elementoDAO.save(elemento);
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAllExcept("datos");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filtroPdf", theFilter).addFilter("filtroFoto", theFilter);
        String string = mapper.writer(filters).writeValueAsString(elemento);
        return mapper.readValue(string, Object.class);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PersonalFamiliar elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        establecerAlias(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Controla longitud de los atributos de tipo short
    public void controlarLongitud(PersonalFamiliar elemento) {
        //Obtiene longitud de anioAlta, si es mayor a 4 retorna error
        String anioAlt = String.valueOf(elemento.getAnioAltaImpGan());
        if (anioAlt.length() > 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " AÑO ALTA IMP. GANANCIAS");
        }
        //Obtiene longitud de anioBaja, si es mayor a 4 retorna error
        String anioBaja = String.valueOf(elemento.getAnioBajaImpGan());
        if (anioBaja.length() > 4) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " AÑO BAJA IMP. GANANCIAS");
        }
    }

    //Formatea los strings
    private PersonalFamiliar formatearStrings(PersonalFamiliar elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setApellido(elemento.getApellido().trim());
        return elemento;
    }

}

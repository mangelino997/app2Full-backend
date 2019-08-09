package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPersonalDAO;
import ar.com.draimo.jitws.dao.IPersonalFamiliarDAO;
import ar.com.draimo.jitws.model.PersonalFamiliar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class PersonalFamiliarService {

    @Autowired
    IPersonalFamiliarDAO elementoDAO;
    
    @Autowired
    IPersonalDAO personalDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PersonalFamiliar elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<PersonalFamiliar> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<PersonalFamiliar> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Obtiene una lista por personal
    public List<PersonalFamiliar> listarPorPersonal(int idPersonal) {
        return elementoDAO.findByPersonal(personalDAO.findById(idPersonal).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PersonalFamiliar agregar(PersonalFamiliar elemento) throws Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de anioAlta, si es mayor a 4 retorna error
        String anioAlt = String.valueOf(elemento.getAnioAltaImpGan());
        if (anioAlt.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO ALTA IMP. GANANCIAS");
        }
        //Obtiene longitud de anioBaja, si es mayor a 4 retorna error
        String anioBaja = String.valueOf(elemento.getAnioBajaImpGan());
        if (anioBaja.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO BAJA IMP. GANANCIAS");
        }
        elemento = elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(String.valueOf(elemento.getId()) + " - " + 
                elemento.getApellido() + " " + elemento.getNombre() + " - " + 
                elemento.getPersonal().getNombreCompleto());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PersonalFamiliar elemento) throws Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de anioAlta, si es mayor a 4 retorna error
        String anioAlt = String.valueOf(elemento.getAnioAltaImpGan());
        if (anioAlt.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO ALTA IMP. GANANCIAS");
        }
        //Obtiene longitud de anioBaja, si es mayor a 4 retorna error
        String anioBaja = String.valueOf(elemento.getAnioBajaImpGan());
        if (anioBaja.length()>4) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en AÑO BAJA IMP. GANANCIAS");
        }
        elemento.setAlias(String.valueOf(elemento.getId()) + " - " + 
                elemento.getApellido() + " " + elemento.getNombre() 
                + " - " + elemento.getPersonal().getNombreCompleto());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private PersonalFamiliar formatearStrings(PersonalFamiliar elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setApellido(elemento.getApellido().trim());
        return elemento;
    }

}

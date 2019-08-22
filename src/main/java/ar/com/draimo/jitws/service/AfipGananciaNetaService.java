package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipAlicuotaGananciaDAO;
import ar.com.draimo.jitws.dao.IAfipGananciaNetaDAO;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipGananciaNeta
 *
 * @author blas
 */
@Service
public class AfipGananciaNetaService {

    //Define la referencia al dao
    @Autowired
    IAfipGananciaNetaDAO elementoDAO;

    //Define la referencia al dao de afipTipoBeneficio
    @Autowired
    IAfipAlicuotaGananciaDAO gananciaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipGananciaNeta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipGananciaNeta> listar() {
        List<AfipGananciaNeta> ganancias = elementoDAO.findAllByOrderByImporte();
        if (ganancias.isEmpty()) {
            throw new DataIntegrityViolationException("No se encontraron registros.");
        } else {
            return ganancias;
        }
    }

    //Obtiene una lista por filtros
    public List<AfipGananciaNeta> listarPorFiltros(short anio, int idMes) throws Exception {
        if (idMes > 12) {
            throw new Exception("Mes inexistente");
        } else {
            List<AfipGananciaNeta> ganancias = elementoDAO.listarPorFiltros(anio, idMes);
            if (ganancias.isEmpty()) {
                throw new DataIntegrityViolationException("No se encontraron registros.");
            } else {
                return ganancias;
            }
        }
    }

    //Obtiene una lista por idAlicuotaGanancia
    public List<AfipGananciaNeta> listarPorAfipAlicuotaGanancia(int idAlicuotaGanancia) {

        List<AfipGananciaNeta> ganancias = elementoDAO.findByAfipAlicuotaGananciaOrderByImporte(
                gananciaDAO.findById(idAlicuotaGanancia).get());
        if (ganancias.isEmpty()) {
            throw new DataIntegrityViolationException("No se encontraron registros.");
        } else {
            return ganancias;
        }
    }

    //Obtiene una lista por AnioFiscal
    public List<AfipGananciaNeta> listarPorAnioFiscal(short anioFiscal) {
        String anio = String.valueOf(anioFiscal);
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException("Cantidad caracteres incorrecta en AÑO");
        }
        List<AfipGananciaNeta> ganancias = elementoDAO.findByAnioOrderByImporte(anioFiscal);
        if (ganancias.isEmpty()) {
            throw new DataIntegrityViolationException("No se encontraron registros.");
        } else {
            return ganancias;
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipGananciaNeta agregar(AfipGananciaNeta elemento) throws Exception {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException("Cantidad caracteres incorrecta en AÑO");
        }
        if (elementoDAO.findByAnioAndImporte(elemento.getAnio(), elemento.getImporte()).isEmpty()) {
            return elementoDAO.saveAndFlush(elemento);
        } else {
            throw new DataIntegrityViolationException("Ganancia neta acumulada existente para el año fiscal.");
        }
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipGananciaNeta elemento) throws Exception {
        String anio = String.valueOf(elemento.getAnio());
        //Obtiene longitud de anio, si supera 4 retorna error
        if (anio.length() > 4 || anio.length() < 4) {
            throw new DataIntegrityViolationException("Cantidad caracteres incorrecta en AÑO");
        }
            elementoDAO.save(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}

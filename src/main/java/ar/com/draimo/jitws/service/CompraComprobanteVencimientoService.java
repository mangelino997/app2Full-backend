//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobanteVencimientoDAO;
import ar.com.draimo.jitws.dao.ICondicionCompraDAO;
import ar.com.draimo.jitws.dto.ImportesDTO;
import ar.com.draimo.jitws.model.CompraComprobanteVencimiento;
import ar.com.draimo.jitws.model.CondicionCompra;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Compra comprobante vencimiento
 * @author blas
 */
@Service
public class CompraComprobanteVencimientoService {

    //Referencia al DAO
    @Autowired
    ICompraComprobanteVencimientoDAO elementoDAO;

    //Referencia al DAO de condicionCompra
    @Autowired
    ICondicionCompraDAO condicionCompraDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraComprobanteVencimiento elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<CompraComprobanteVencimiento> listar() {
        return elementoDAO.findAll();
    }
    
    //Controla la diferencia entre el importe y los importes de la tabla generada por listarCuotas
    public BigDecimal obtenerDiferenciaImportes(List<ImportesDTO> listaCuotas) {
        BigDecimal total = new BigDecimal(0);
        for (ImportesDTO listaCuota : listaCuotas) {
             total = total.add(listaCuota.getImporte());
        }
        return total.subtract(listaCuotas.get(0).getImporteTotal());
    }

    //Genera la tabla de cuotas
    public List<CompraComprobanteVencimiento> generarTablaVencimientos(int cantidadCuotas,
            BigDecimal totalImporte, int idCondicionCompra) {
        List<CompraComprobanteVencimiento> vencimientos = new ArrayList<>();
        //obtiene la condicion de compra por id
        CondicionCompra condicion = condicionCompraDAO.findById(idCondicionCompra).get();
        CompraComprobanteVencimiento vencimiento;
        BigDecimal total=new BigDecimal(0);
        BigDecimal dif=new BigDecimal(0);
        //Obtiene la cantidad de dias de condicionCompra
        short cantDias = condicion.getDias();
        //Obtiene la fechaActual
        String fechaString = new Date(new java.util.Date().getTime()).toString();
        BigDecimal cuotas = BigDecimal.valueOf(cantidadCuotas);
        //Obtiene el importe por cuota
        BigDecimal importe = totalImporte.divide(cuotas, 2, RoundingMode.HALF_UP);
        //Establece los datos a vencimiento y lo agrega a la lista
        for (int i = 0; i < cantidadCuotas; i++) {
            vencimiento = new CompraComprobanteVencimiento();
            vencimiento.setImporte(importe);
            fechaString = LocalDate.parse(fechaString).plusDays(cantDias).toString();
            vencimiento.setFecha(Date.valueOf(fechaString));
            vencimientos.add(vencimiento);
            total = total.add(importe);
        }
        //Controla si hay diferencia entre el importe total y la suma de los importe
        //Si hay, se lo establece al ultimo registro
        if(total.compareTo(totalImporte) == 1) {
            dif = total.subtract(totalImporte);
            vencimientos.get(cantidadCuotas-1).setImporte(importe.subtract(dif));
        //Si no se lo resta
        } else if(total.compareTo(totalImporte) == -1){
            dif = totalImporte.subtract(total);
            vencimientos.get(cantidadCuotas-1).setImporte(importe.add(dif));
        }
        return vencimientos;
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobanteVencimiento agregar(CompraComprobanteVencimiento elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobanteVencimiento elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
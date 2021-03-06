package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipDeduccionGeneral;
import ar.com.draimo.jitws.model.AfipDeduccionPersonal;
import ar.com.draimo.jitws.model.AfipTipoBeneficio;
import ar.com.draimo.jitws.model.AfipTipoBeneficioDeduccion;
import ar.com.draimo.jitws.model.Mes;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitDeduccionPersonalTablaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de categoriatipoBeneficioDeduccioness
    private List<AfipTipoBeneficio> tipoBeneficios; 
    
    //Lista de afipDeduccionPersonales
    private List<AfipDeduccionPersonal> afipDeduccionPersonales; 
    
    //Lista de anios
    private List<Short> anios; 
    
    //Lista de meses
    private List<Mes> meses; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<AfipTipoBeneficio> getTipoBeneficios() {
        return tipoBeneficios;
    }

    public void setTipoBeneficios(List<AfipTipoBeneficio> tipoBeneficios) {
        this.tipoBeneficios = tipoBeneficios;
    }

    public List<AfipDeduccionPersonal> getAfipDeduccionPersonales() {
        return afipDeduccionPersonales;
    }

    public void setAfipDeduccionPersonales(List<AfipDeduccionPersonal> afipDeduccionPersonales) {
        this.afipDeduccionPersonales = afipDeduccionPersonales;
    }

    public List<Short> getAnios() {
        return anios;
    }

    public void setAnios(List<Short> anios) {
        this.anios = anios;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}
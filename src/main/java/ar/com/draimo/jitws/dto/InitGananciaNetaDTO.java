package ar.com.draimo.jitws.dto;

import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import ar.com.draimo.jitws.model.Mes;
import ar.com.draimo.jitws.model.Pestania;
import java.util.List;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class InitGananciaNetaDTO {
    
    //Define el ultimo id
    private int ultimoId;
    
    //Lista de alicuotaGanancias
    private List<AfipAlicuotaGanancia> alicuotaGanancias; 
    
    //Lista de mes
    private List<Mes> meses; 
    
    //Lista de anios
    private List<Short> anios; 
    
    //Define las pestanias
    private List<Pestania> pestanias;
    
    //Define Getters y Setters
    public int getUltimoId() {
        return ultimoId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public List<AfipAlicuotaGanancia> getAlicuotaGanancias() {
        return alicuotaGanancias;
    }

    public void setAlicuotaGanancias(List<AfipAlicuotaGanancia> alicuotaGanancias) {
        this.alicuotaGanancias = alicuotaGanancias;
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public List<Short> getAnios() {
        return anios;
    }

    public void setAnios(List<Short> anios) {
        this.anios = anios;
    }

    public List<Pestania> getPestanias() {
        return pestanias;
    }

    public void setPestanias(List<Pestania> pestanias) {
        this.pestanias = pestanias;
    }
    
}
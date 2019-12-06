package ar.com.draimo.jitws.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

/**
 * Data Transfer Object of personal
 * @author blas
 */
public class ChoferDTO {
    
    //Define tipo de chofer
    /* 0 - Todos
    *  1 - Corta Distancia
    *  2 - Larga Distancia */
    private int chofer;
    
    //Define la fecha vtoFisico
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoFisico;
    
    //Define la fecha vtoCurso
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoCurso;
    
    //Define la fecha vtoCargaPeligrosa
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoCargaPeligrosa;
    
    //Define la fecha vtoLicConducir
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoLicConducir;
    
    //Define la fecha vtoLinti
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoLinti;
    
    //Define la fecha vtoLibSanidad
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private Date vtoLibSanidad;

    public int getChofer() {
        return chofer;
    }

    public void setChofer(int chofer) {
        this.chofer = chofer;
    }

    public Date getVtoFisico() {
        return vtoFisico;
    }

    public void setVtoFisico(Date vtoFisico) {
        this.vtoFisico = vtoFisico;
    }

    public Date getVtoCurso() {
        return vtoCurso;
    }

    public void setVtoCurso(Date vtoCurso) {
        this.vtoCurso = vtoCurso;
    }

    public Date getVtoCargaPeligrosa() {
        return vtoCargaPeligrosa;
    }

    public void setVtoCargaPeligrosa(Date vtoCargaPeligrosa) {
        this.vtoCargaPeligrosa = vtoCargaPeligrosa;
    }

    public Date getVtoLicConducir() {
        return vtoLicConducir;
    }

    public void setVtoLicConducir(Date vtoLicConducir) {
        this.vtoLicConducir = vtoLicConducir;
    }

    public Date getVtoLinti() {
        return vtoLinti;
    }

    public void setVtoLinti(Date vtoLinti) {
        this.vtoLinti = vtoLinti;
    }

    public Date getVtoLibSanidad() {
        return vtoLibSanidad;
    }

    public void setVtoLibSanidad(Date vtoLibSanidad) {
        this.vtoLibSanidad = vtoLibSanidad;
    }

}

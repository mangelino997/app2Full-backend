//Paquete al que pertenece la clase
package ar.com.draimo.jitws.dto;

/**
 * Clase SubopcionDTO
 * Define el modelo (columnas) de la base de datos.
 * @author blas
 */

public class SubopcionDTO {
    
    //Define el id
    private int id;
    
    //Define la version
    private int version;
    
    //Define el nombre
    private String nombre;
    
    //Define si es ABM
    private boolean esABM;
    
    //Define estado de mostrar
    private boolean mostrar;
    
    //Getters y Setters de la clase
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEsABM() {
        return esABM;
    }

    public void setEsABM(boolean esABM) {
        this.esABM = esABM;
    }

    public boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
}

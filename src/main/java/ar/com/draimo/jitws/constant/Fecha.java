package ar.com.draimo.jitws.constant;
import java.sql.Date;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Define métodos genericos para trabajar con fechas
 * @author blas
 */
public class Fecha {
    
    //Obtiene el dia de una fecha
    public static int obtenerDia(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        return calendario.get(Calendar.DAY_OF_MONTH);
    }
    
    //OBtiene el stringMes de una fecha
    public static int obtenerMes(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        return calendario.get(Calendar.MONTH) + 1;
    }
    
    //OBtiene el anio de una fecha
    public static int obtenerAnio(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        return calendario.get(Calendar.YEAR);
    }
    
    //Suma x anios a una fecha
    public static Date sumarAnio(Date fecha, int cantidad) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.YEAR, cantidad);
        java.util.Date f = (java.util.Date) calendario.getTime();
        return new Date(f.getTime());
    }
    
    //Suma x meses a una fecha
    public static Date sumarMes(Date fecha, int cantidad) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.MONTH, cantidad);
        java.util.Date f = (java.util.Date) calendario.getTime();
        return new Date(f.getTime());
    }
    
    //Suma x dias a una fecha
    public static Date sumarDia(Date fecha, int cantidad) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_MONTH, cantidad);
        java.util.Date f = (java.util.Date) calendario.getTime();
        return new Date(f.getTime());
    }
    
    //Suma x dias y mes a una fecha
    public static Date sumarDiaYMes(Date fecha, int cantidadDias, int cantidadMes) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_MONTH, cantidadDias);
        calendario.add(Calendar.MONTH, cantidadMes);
        java.util.Date f = (java.util.Date) calendario.getTime();
        return new Date(f.getTime());
    }
    
    public static List<Short> listarAnios() {
        List<Short> anios = new ArrayList<>();
        short anio = Funcion.anioInicio;
        for (short i = anio; i < anio + 15; i++) {
            anios.add((short) i);
        }
        return anios;
    }
    
    //Convierte una fecha String en date
    public static Date convertirFecha(String fecha) {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = sdf.parse(fecha);
            return new Date(date.getTime());
        } catch (ParseException ex) {
            return new Date(new java.util.Date().getTime());
        }
    }
    
    //Define stringMes
    public static String stringMes(int numMes) {
        String mes = "";
        switch(numMes){
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "Octubre";
                break;
            case 11:
                mes = "Noviembre";
                break;
            case 12:
                mes = "Diciembre";
                break;
        }
        return mes;
    }
    
    //Define la hora 
    public static String obtenerHora(Date fecha){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(fecha);
    }
    
    //Obtiene una lista del año actual + x años
    public static List<Integer> listarXAnios(Date fecha, int cantidad) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        int anio = calendario.get(Calendar.YEAR);
        int total = anio + cantidad;
        List<Integer> anios = new ArrayList<>();
        for(int i = anio ; i < total ; i++) {
            anios.add(i);
        }
        return anios;
    }
    
    //Obtiene un listado de los meses (en String)
    public static List<String> listarMesesStrings() {
        String mes = "";
        List<String> meses = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            mes = stringMes(i);
            meses.add(mes);
        }
        return meses;
    }
    
}

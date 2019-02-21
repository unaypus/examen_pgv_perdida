/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 *<pre>
 *
 * 
 * Alumno:     Juan Pons Abraham
 * Curso:      Curso 3º DAM Semipresencial 2018/19
 * Asignatura: PGV
 * Profesor:   Tiburcio
 * 
 * Descripción:
 * 
 * Aplicación
 * 
 * </pre>
 * @author juaponabr
 * 
 */
public class Pais {
    
    private String  nombre  ;
    private int     nPoblacion    ;
    private int     renta   ;
    
    /**
     * 
     * @param nombre
     * @param stok 
     */
    public Pais( String nombre, int pobla, int renta ){
        
        this.nombre = nombre    ;
        this.nPoblacion   = pobla      ;
        this.renta = renta;
        
    }

    /**
     * 
     * @return 
     */
    public String getNombre() { return nombre ; }

    /**
     * 
     * @param nombre 
     */
    public void setNombre( String nombre ) { this.nombre = nombre ; }

    /**
     * 
     * @return 
     */
    public int getnPoblacion() { return nPoblacion ; }

    /**
     * 
     * @param nPoblacion 
     */
    public void setnPoblacion(int nPoblacion) { this.nPoblacion = nPoblacion ; }     
    
    /**
     * 
     * @return 
     */
    public int getRenta() { return renta ; }

    /**
     * 
     * @param nPoblacion 
     */
    public void setRenta(int renta) { this.renta = renta ; }  
    
}

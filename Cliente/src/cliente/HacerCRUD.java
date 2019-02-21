/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import utilidades.Cadenas;

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
public class HacerCRUD {
    
    Interaccion usuario ;
    Cadenas     cadenas ;
    
    /**
     * 
     */
    public HacerCRUD(){
        
        usuario = new Interaccion() ;
        cadenas = new Cadenas()     ;
        
    }
    
    // Métodos CRUD
    
    /**
     * 
     * @return 
     */
    public String altaRegistro() {
    
        
        String sSalida = editaRegistro( "Añadir" ) ;
        
        if( !sSalida.equals( "cancel" ) ){ sSalida = "OP1.añadir...<"   + sSalida ; }
        
        return  sSalida ;
        
    }

    /**
     * 
     * @return 
     */
    public String modificaRegistro() {
        
        String sSalida = editaRegistro( "Modificar") ;
        
        if( !sSalida.equals( "cancel" ) ){ sSalida = "OP2.modificar<"   + sSalida ; }
        
        return  sSalida ;
        
    }

    /**
     * 
     * @return 
     */
    public String borraRegistro() {
        
        // campos del registro con valores de cancelación
        String  campNombre  = "*"   ; // Campo 1
            
        System.out.println("\n\n    /////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                     Borrar Registro"  ) ;
        ///////////////////////////////////////////////////////////////////////
        //
        // Rellenamos el campo de busqueda
        //
        
        ///////////////////////////////
        // Campo 1
        // Rellenar campo Nombre
        campNombre = usuario.introString( "Nombre", Constantes.MAX_TAM_NOMBRE ) ;        
        // Si cancelo salgo
        if( "*".equals( campNombre.substring( 0, 1 ) ) ){ return "cancel" ; }
    
        //////////////////////////////////////////////////
        //
        // Si no cancelo envio el campo de busqueda
        return  "OP3.borrar...<"   +
                campNombre + ">"; 
        
    }

    /**
     * 
     * @return 
     */
    public String listaRegistros() {
        
        return "OP4.listar..." ;
    
    }
    
    public String mostrar(){
        
        // campos del registro con valores de cancelación
        String  campNombre  = "*"   ; // Campo 1
            
        System.out.println("\n\n    /////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                     Buscar Registro"  ) ;
        ///////////////////////////////////////////////////////////////////////
        //
        // Rellenamos el campo de busqueda
        //
        
        ///////////////////////////////
        // Campo 1
        // Rellenar campo Nombre
        campNombre = usuario.introString( "Nombre", Constantes.MAX_TAM_NOMBRE ) ;        
        // Si cancelo salgo
        if( "*".equals( campNombre.substring( 0, 1 ) ) ){ return "cancel" ; }
    
        //////////////////////////////////////////////////
        //
        // Si no cancelo envio el campo de busqueda
        return  "OP5.buscar...<"   +
                campNombre + ">"; 
        
    }
    // Métodos auxiliares CRUD
    
    /**
     * 
     * @param modo
     * @return 
     */
    private String editaRegistro( String modo ){
        
    // campos del registro con valores de cancelación
        String  campNombre  = "*"   ; // Campo 1
        int     campPob    = -1    ; // Campo 2
        int campRenta =-1;
        
        System.out.println("\n\n    /////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                     " + modo + " Registro"  ) ;
        ///////////////////////////////////////////////////////////////////////
        //
        // Rellenamos el registro con dos lineas por campo
        //
        //  a - rellanamos el campo con su correspondiente método
        //  b - si ha sido cancelado salimos, si no pasamos al siguiente campo
        //
        
        ///////////////////////////////
        // Campo 1
        // Rellenar campo Nombre
        campNombre = usuario.introString("Nombre", Constantes.MAX_TAM_NOMBRE ) ;        
        // Si cancelo salgo
        if( "*".equals( campNombre.substring( 0, 1 ) ) ){ return "cancel" ; }
        
        ///////////////////////////////
        // Campo 2
        // Rellenar campo Stok
        campPob = usuario.introInt("Población", Constantes.MAX_VALOR_POBLACION) ;        
        // Si cancelo salgo
        if( campPob < 0 ){ return "cancel" ; }
        
        ///////////////////////////////
        // Campo 3
        // Rellenar campo Stok
        campRenta = usuario.introInt("Renta", Constantes.MAX_VALOR_RENTA) ;        
        // Si cancelo salgo
        if( campPob < 0 ){ return "cancel" ; }
        
        //////////////////////////////////////////////////
        //
        // Si no cancelo ningún campo devuelvo el registro
        return  campNombre + ":" + 
                cadenas.ajustarCadenaNUM( " " + campPob, Constantes.MAX_TAM_POBLACION , false) + ":" + 
                cadenas.ajustarCadenaNUM( " " + campRenta, Constantes.MAX_TAM_RENTA , false) +
                ">"; 
    
        
        
    }
     
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.Scanner;
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
public class Interaccion {
    
    HacerCRUD   crud    ;
    Cadenas     cadenas ;
    
    /**
     * 
     */
    public Interaccion(){  }
    
    /**
     * 
     * @return 
     */
    public int mostrarMenu() {
        
        int seleccion = -1;
        
        do{
            
            Scanner sc = new Scanner( System.in );
            
            System.out.println("\n"  ) ;
            
            /*
            System.out.println("    /////////////////////////////////////////////"  ) ;
            System.out.println("    //                                         //"  ) ;
            System.out.println("    //         Opciones                        //"  ) ;
            System.out.println("    //                                         //"  ) ;
            System.out.println("    /////////////////////////////////////////////"  ) ;
            System.out.println("    //                                         //"  ) ;
            System.out.println("    //         1. - Alta registro              //"  ) ;
            System.out.println("    //         2. - Modificar Registro         //"  ) ;
            System.out.println("    //         3. - Borrar Registro            //"  ) ;
            System.out.println("    //         4. - Listado Completo           //"  ) ;
            System.out.println("    //                                         //"  ) ;
            System.out.println("    //         0. - Cerrar Sesión              //"  ) ;
            System.out.println("    //                                         //"  ) ;
            System.out.println("    /////////////////////////////////////////////"  ) ;
            */
            
            System.out.println("    Opciones del menú"  ) ;
            System.out.println("    1-Alta 2-Modifica 3-Borra 4-Listado 5-Buscar // 0-Salir"  ) ;
            
            System.out.print("\n    Nº de la selección: ");        
        
            try{
                seleccion = sc.nextInt() ;
            }catch (Exception e){
                
                // en caso de error mostrar información 
                mostrarInstruciones();
                seleccion = -1 ;
                
            }
        
        }while( seleccion < 0 );
        
        return seleccion ;
        
    }
    
    /**
     * 
     * @param elResultado
     * @param esperar 
     */
    public void mostrarResultado( String elResultado, boolean esperar ) {
        
        Scanner sc = new Scanner( System.in );
        String seleccion ;
        
        System.out.println("\n"  ) ;
        
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        //System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //         Respuesta del Servidor                                       //"  ) ;
        //System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        
        System.out.println("\n"  ) ;
        
        System.out.println( "   " + elResultado ) ;
        
        //System.out.println("\n\n"  ) ;
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        
        if( esperar ){
            
            System.out.print("\n        --------  Pulse INTRO para continuar :"  ) ;
            seleccion = sc.nextLine() ;
            
        }
        
    }

    /**
     * 
     * @return 
     */
    public String segun() {
        
        int     opcion  = 0         ; //Integer.parseInt( menu )  ;
        String  sSalida = "nada"    ;
        
        crud = new HacerCRUD() ;
        
        do{
            
            opcion  = mostrarMenu() ;
            
            switch( opcion ){
            
                case 0 :
                
                    
                    if( seguroSalir() ){  
                        sSalida = "fin" ; 
                    } else { 
                        opcion = 5 ;  
                    }
                    
                    break ;
                
                case 1 :
                
                    sSalida = crud.altaRegistro();
                    break ;
                
                case 2 :
                
                    sSalida = crud.modificaRegistro();
                    break ;
                
                case 3 :
                
                    sSalida = crud.borraRegistro();
                    break ;
                
                case 4 :
                
                    sSalida = crud.listaRegistros();
                    break ;
                    
                case 5 :
                    
                    sSalida = crud.mostrar();
                
                default :
                
                    mostrarInstruciones();
                    opcion = 6 ;
                
            }
            
            if( !sSalida.equals( "nada" ) ){ opcion = 0 ; }
        
        }while( opcion > 0) ;
        
        return sSalida ;
        
    }

    /**
     * 
     */
    public void mostrarInstruciones() {
        
        System.out.println("\n\n"  ) ;
        
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //    Atención ERROR                                                    //"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //              Usar solo números de una cifra y que se corresponda     //"  ) ;
        System.out.println("    //              con la cifra de alguna de las opciones                  //"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        System.out.println(" "  ) ;
        
    }
    
    /**
     * 
     * @return 
     */
    public String introServer(){
        
        Scanner sc = new Scanner( System.in );
        
        System.out.println("\n\n"  ) ;
        
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //         Atención No se encuentra el servidor                         //"  ) ;
        System.out.println("    //                                                                      //"  ) ;
        System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
        System.out.print("\n    a - Combpruebe la conexión"
                +        "\n    b - Verifique el Nombre o la IP del servidor\n"
                +        "\n    Introduzca el Nombre o IP del Servidor ('fin' para salir ) :");
        
        return sc.nextLine() ;
        
    }
    
    /**
     * 
     * @param titulo
     * @param maximo
     * @return 
     */
    public String introString( String titulo, int maximo){
                
        String  cadena                  ;
        boolean salir   = false         ;
        cadenas         = new Cadenas() ;
        
        do{
        
            Scanner sc = new Scanner( System.in );
        
            //System.out.println("\n\n"  ) ;
        
            System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
            System.out.println("        Introducir cadena MÁXIMO : " + maximo +" caracteres"  ) ;
            System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
            System.out.print("\n    " + titulo +" ('*' para cancelar ) :");
        
            cadena = sc.nextLine() ;
        
            if( !cadena.equals("") ){
            
                salir = true ;
                
                if( !cadena.equals( "*" ) ){
                    
                    cadena = cadenas.ajustarLongCadena( cadena, maximo, " " ) ;                    
                    
                }
                
            }
            
        }while( !salir ) ;       
        
        return cadena ;
        
    }
    
    /**
     * 
     * @param titulo
     * @param maximo
     * @return 
     */
    public int introInt( String titulo, int maximo ){        
        
        int seleccion = -1 ;
        
        do{
        
            Scanner sc = new Scanner( System.in );
            
            //System.out.println("\n\n"  ) ;
        
            System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
            System.out.println("        Introducir número entero MÁXIMO : " + maximo  ) ;
            System.out.println("    //////////////////////////////////////////////////////////////////////////"  ) ;
            System.out.print("\n    " + titulo +" ('-1' para cancelar ) :");
        
            try{
                
                seleccion = sc.nextInt() ;
                if( seleccion > maximo ){ seleccion = -2 ; }
            
            }catch (Exception e){
                
                // en caso de error mostrar información 
                seleccion = -2 ;
                
            }            
          
            
        }while( seleccion < -1 );
        
        return seleccion ;
        
    }
    
    /**
     * 
     * @return 
     */
    public boolean seguroSalir() {
        
        String seleccion = "s" ;
        Scanner sc = new Scanner( System.in );
        
        System.out.println("\n\n"  ) ;
        
        System.out.println("    /////////////////////////////////////////////////////////////"  ) ;
        System.out.println("    //                                                         //"  ) ;
        System.out.println("    //         Atención Pregunta                               //"  ) ;
        System.out.println("    //                                                         //"  ) ;
        System.out.println("    /////////////////////////////////////////////////////////////"  ) ;
        System.out.println(" \n"  ) ;
        System.out.print(  "    ¿ Seguro que quiere desconectarse del servidor? s/n : "  ) ;
        
        seleccion = sc.nextLine() ;
        
        System.out.println(" \n"  ) ;
        
        // forzamos la respuesta a minúsculas
        // en caso de pulsar N tendremos n
        seleccion = seleccion.toLowerCase();
        
        // comprobación de la opción elegida  
        if ( !seleccion.equals( "n" ) ) {            
            // convertimos en s cualquier respuesta diferente a n
            return true ;
	}
        
        // si llegamos aqui es que no
        return false ; 
        
    }    
    
}

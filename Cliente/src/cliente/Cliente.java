/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class Cliente {
    
    static BufferedWriter   bw          ;
    static BufferedReader   br          ;    
    static Socket           sk          ;
    static String           nSesion     ;
    
    static Interaccion      usuario     ;
    static Constantes       constantes  ;
    
    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        
        usuario = new Interaccion() ;
        
        if( conectar(( args.length > 0 ? args[ 0 ] : constantes.HOST )  ) ){
            
            iniciarSocket()     ; 
            conversar() ;
            cerrarSocket()      ;
            
        }
        
        
    }
    
    /**
     * 
     * @param elServer
     * @return 
     */
    private static boolean conectar( String elServer ){
        
        do{
            try {
            
                sk = new Socket( elServer, constantes.PORT ) ;
                return true ;
            
            } catch (IOException ex) {
                
                elServer = usuario.introServer();
                
            }
        
        } while( !elServer.equals( "fin" ) ) ;
        
        return false ;
        
    }
    
    /**
     * 
     */
    private static void iniciarSocket(){
        
        OutputStream    os = null ;
        InputStream     is = null ;
        
        try {
            
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);      
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     */
    private static void conversar() {
        
        String mensaje = "cancel" ;
        
        nSesion = recibir(  " Estado       : Conectado\n   "      +
                            " Servidor     : " + sk.getInetAddress() + "\n   "  +
                            " Nº de sesión : " , false ) ;
        
        while( true ){            
            
            if( !"cancel".equals( mensaje = usuario.segun() )){
                enviar( mensaje ) ;
                if( recibir( " ", false ).equals( "Adios\n" ) ){ break ; }
            }
                
        }
        
    }
    
    /**
     * 
     * @param linea 
     */
    private static void enviar( String linea ){
        
        try {
            
            bw.write( linea + "\nelfin");
            bw.newLine();
            bw.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @param titulo
     * @param esperar
     * @return 
     */
    private static String recibir( String titulo, boolean esperar ){
        
        String unaLinea     = "nada"    ;
        String masLineas    = ""        ;
        
        try {
            
            while( !"elfin".equals( unaLinea = br.readLine() ) ){
                masLineas = masLineas + unaLinea + "\n" ;
            }
            
            usuario.mostrarResultado( titulo  + masLineas, esperar ) ;
            return masLineas ;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unaLinea ;
        
    }

    /**
     * 
     */
    private static void cerrarSocket(){
        
        try {            
            
            br.close();
            bw.close();
            sk.close();
            System.out.println("\n  Finalizada sesión : " + nSesion + "\n") ;
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

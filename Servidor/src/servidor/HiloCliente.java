/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import control.CtrlDatos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
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
public class HiloCliente extends Thread{
    
    Socket          sk              ;
    BufferedWriter  bw              ;
    BufferedReader  br              ;
    
    int             nSocket         ;
    CtrlDatos       controlDatos    ;
    
    String          laIP            ;
    int             elPuerto        ;
    
    /**
     * 
     * @param sk
     * @param nSocket
     * @param controlDatos 
     */
    public HiloCliente( Socket sk, int nSocket, CtrlDatos controlDatos ){
        
        this.sk             = sk            ;
        this.nSocket        = nSocket       ;
        this.controlDatos   = controlDatos  ;
        
    }
    
    /**
     * 
     */
    @Override
    public void run() {
        
        iniciarSocket();
        cargarCliente();  
        conversar();
        cerrarSocket();
        
    }
    
    /**
     * 
     */
    private void cargarCliente(){
        
        Inet4Address ip = ( Inet4Address ) sk.getInetAddress()  ;
        laIP            = ip.getHostAddress()                   ;
        elPuerto        = sk.getPort()                          ; //ip.getHostName();
        
        // cliente entrante
        System.out.println( "Iniciada Sesión : " + nSocket + "\nDesde : " + laIP +" : " + elPuerto ) ; 
        enviar( nSocket + " ") ;
        
    }
    
    /**
     * 
     */
    private void iniciarSocket(){
        
        InputStream     is = null ;
        OutputStream    os = null ;
        
        try {            
            
            is = sk.getInputStream();            
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     */
    private void conversar(){
        
        while( true ){
                                
            String linea = recibir() ; //br.readLine();
                
            if( linea.equals("fin\n") ) {
                    
                enviar ("Adios");
                break;
                    
            } else {
                    
                // operar con los datos y responder
                
                linea = controlDatos.elegirAccion( linea ) ;
                yield() ;
                
                enviar(  linea + "\n    Ok. Saludos desde el servidor" ) ;
                    
            }
                
        }
        
    }

    /**
     * 
     * @param linea 
     */
    private void enviar( String linea ){
        
        try {
            
            bw.write( linea  + "\nelfin" );
            bw.newLine();
            bw.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @return 
     */
    private String recibir(){
        
        String linea = "nada" ;
        String loRecibido = "";
        
        try {
            
            System.out.println("Esperando");
            
            while( !"elfin".equals( linea = br.readLine() ) ){
                loRecibido = loRecibido + linea + "\n" ;
            }
            
            System.out.println("Sesión : " + nSocket + " : " + loRecibido);      
            
            return loRecibido ;
            
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return linea ;
        
    }
    
    /**
     * 
     */
    private void cerrarSocket(){
        
        try {                        
            
            br.close();
            bw.close();
            sk.close();
            System.out.println("Finalizada Sesión : " + nSocket);
            System.out.println("Esperando.");
            
        } catch (IOException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import control.CtrlDatos;
import java.io.IOException;
import java.net.ServerSocket;
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
public class Servidor {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int       PORT            = 40080             ;        
        final CtrlDatos controlDatos    = new CtrlDatos()   ;
    
        try {
            
            ServerSocket sk = new ServerSocket( PORT );
            int nSocket = 0 ;
           
            System.out.println("Iniciado servicio" ) ;
            
            while(true){    
            
                System.out.println("Esperando.." ) ;
                Socket socket = sk.accept();
                
                System.out.println("Recibiendo Conexión Entrate");
                nSocket++;
                
                System.out.println("Asignada Sesión nº : " + nSocket);
                HiloCliente hilo = new HiloCliente( socket, nSocket, controlDatos );
                System.out.println("Iniciando Conversación Sesión nº : " + nSocket);
                hilo.start();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author juaponabr
 */
public class Cadenas {
    
    /**
     * <pre> Método ajustarCadena
     * 
     * comprobamos que sCadena tiene la longitud deseada en nLongCadena
     * si excede recortamos , si no llega añadimos caracter recibido
     * 
     * @param sCadena      cadena a comprobar
     * @param nLongCadena longitud deseada
     * @param llenar
     * @return  la cadena ajustada
     * </pre>
     */    
    public String ajustarLongCadena(String sCadena, int nLongCadena, String llenar){
        
        
        String  sSalida = sCadena           ;
        int     nLCad   = sCadena.length()  ;
        
        if( nLCad > nLongCadena ){
            
            sSalida = sCadena.substring( 0, nLongCadena ) ;
            
        }else{
            
            for( int nContador = nLCad ; nLCad < nLongCadena ; nLCad++ ){
                
                sSalida = sSalida + llenar;
                
            }
            
        }
        
        return sSalida ;
        
    }
    
    /**
     * <pre> Método ajustarCadena
     * 
     * comprobamos que sCadena tiene la longitud deseada en nLongCadena
     * si excede recortamos , si no llega añadimos espacios, por delante o por 
     * detras según indique dDetras
     * 
     * @param sCadena
     * @param nLongCadena
     * @param bDetras
     * @return 
     * </pre>
     */
    public String ajustarCadenaNUM(String sCadena, int nLongCadena, boolean bDetras){
        
        /**
         * ajustarCadena
         * 
         * comprobamos que sCadena tiene la longitud deseada en nLongCadena
         * si excede recortamos , si no llega añadimos espacios
         * 
         */
        
        String  sSalida = sCadena           ;        
        int     nLCad   = sCadena.length()  ;
        
        String  sDetras                     ;
        String  sDelante                    ;
        
        if( nLCad > nLongCadena ){
            
            sSalida = sCadena.substring( 0, nLongCadena ) ;
            
        }else{
            
            if( bDetras ){
            
                sDetras     = " "   ;
                sDelante    = ""    ;
            
            }else{
            
                sDetras     = ""    ;
                sDelante    = " "   ;
            
            }
            
            for( int nContador = nLCad; nLCad < nLongCadena; nLCad++ ){
            
                sSalida = sDelante + sSalida + sDetras;
            
            }
        }
        
        return sSalida ;
        
    }
    
}

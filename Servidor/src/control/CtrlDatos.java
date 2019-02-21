/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Vector;
import pojos.Pais;
import servidor.Constantes;
import utilidades.Cadenas;

/**
 *
 * <pre>
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
 *
 * @author juaponabr
 *
 */
public class CtrlDatos {

    private Cadenas cadenas = new Cadenas();
    private Pais pais;
    private Vector<Pais> elMundo;

    /**
     *
     */
    public CtrlDatos() {
        cargarDatosIniciales();
    }

    /**
     *
     */
    private void cargarDatosIniciales() {

        elMundo = new Vector<Pais>();

        pais = new Pais(cadenas.ajustarLongCadena("Brasil", Constantes.MAX_TAM_NOMBRE, " "), 2564892, 300);
        elMundo.addElement(pais);

        pais = new Pais(cadenas.ajustarLongCadena("Estados Unidos", Constantes.MAX_TAM_NOMBRE, " "), 1595632, 450);
        elMundo.addElement(pais);

        pais = new Pais(cadenas.ajustarLongCadena("Francia", Constantes.MAX_TAM_NOMBRE, " "), 800653, 523);
        elMundo.addElement(pais);

        pais = new Pais(cadenas.ajustarLongCadena("China", Constantes.MAX_TAM_NOMBRE, " "), 63259856, 600);
        elMundo.addElement(pais);

    }

    /////////////////////////////////////////////
    // Métodos controladores del menú
    /**
     *
     * @param laCadena
     * @return
     */
    private synchronized int queOpcion(String laCadena) {

        int laOpcion = Integer.parseInt(laCadena.substring(2, 3));

        return laOpcion;

    }

    /**
     *
     * @param laCadena
     * @return
     */
    public synchronized String elegirAccion(String laCadena) {

        switch (queOpcion(laCadena)) {

            case 1:

                return altaRegistro(laCadena) + "\n   " + listadoRegistros(0);
            //break ;

            case 2:

                return modificaRegistro(laCadena) + "\n   " + listadoRegistros(0);
            //break ;

            case 3:

                return borraRegistro(laCadena) + "\n   " + listadoRegistros(0);
            //break ;

            case 4:

                return listadoRegistros(0);
            //break ;
                
            case 5 :
                return buscarRegistro(laCadena) ;

        }

        return "Error";

    }

    /////////////////////////////////////////////////////////
    // Métodos que realizan el CRUD
    /**
     *
     * @param laCadena
     * @return
     */
    private synchronized String altaRegistro(String laCadena) {

        /*
        .alta.....
        .modificar
        .baja.....
        .listado..
         */
        String sSalida = "  Añadir Registro\n\n";
        int espSeparador = 1;

        //  1º linea opción OP1 e inicio REGISTRO < total 14 caracteres
        int punteroCadena = 14; // desde 0 a 13 catorce caracteres       

        //  las siguientes una por campo SEPARADOR : un caracter
        //  Campo 1 Nombre
        String campNombre = laCadena.substring(punteroCadena, Constantes.MAX_TAM_NOMBRE + punteroCadena);
        punteroCadena = punteroCadena + Constantes.MAX_TAM_NOMBRE + espSeparador;

        if (encuentra(campNombre) > -1) {

            return sSalida = sSalida
                    + "                   ATENCIÓN\n\n"
                    + "    Ya existe este País : " + campNombre + "\n";

        }

        //  Campo 2 poblacion        
        String campPob = laCadena.substring(punteroCadena, Constantes.MAX_TAM_POBLACION + punteroCadena);
        int lapob = Integer.parseInt(campPob.trim());

        punteroCadena = punteroCadena + Constantes.MAX_TAM_POBLACION + espSeparador;
        //  Campo 3 renta        
        String campRenta = laCadena.substring(punteroCadena, Constantes.MAX_TAM_RENTA + punteroCadena);
        int larenta = Integer.parseInt(campRenta.trim());

        // añadir array
        pais = new Pais(campNombre, lapob, larenta);
        elMundo.addElement(pais);

        // Montar cadena salida
        sSalida = sSalida
                + "    País         : " + campNombre + "\n"
                + "    Población    : " + campPob + "\n"
                + "    Renta/Capita : " + campRenta + "\n";

        return sSalida;

    }

    /**
     *
     * @param laCadena
     * @return
     */
    private synchronized String modificaRegistro(String laCadena) {

        String sSalida = "  Modificar Registro\n\n";
        //  1º linea opción OP1 e inicio REGISTRO < total 14 caracteres
        int punteroCadena = 14; // desde 0 a 13 catorce caracteres     
        int espSeparador = 1;
        int posiVector;

        //  las siguientes una por campo SEPARADOR : un caracter
        //  Campo 1 Nombre
        String campNombre = laCadena.substring(punteroCadena, Constantes.MAX_TAM_NOMBRE + punteroCadena);
        punteroCadena = punteroCadena + Constantes.MAX_TAM_NOMBRE + espSeparador;

        if ((posiVector = encuentra(campNombre)) < 0) {

            return sSalida = sSalida
                    + "                   ATENCIÓN\n\n"
                    + "    No se encuentra este País : " + campNombre + "\n";

        }

        //  Campo 2 poblacion        
        String campPob = laCadena.substring(punteroCadena, Constantes.MAX_TAM_POBLACION + punteroCadena);
        int lapob = Integer.parseInt(campPob.trim());
        punteroCadena = punteroCadena + Constantes.MAX_TAM_POBLACION + espSeparador;
        //  Campo 3 renta        
        String campRenta = laCadena.substring(punteroCadena, Constantes.MAX_TAM_RENTA + punteroCadena);
        int larenta = Integer.parseInt(campRenta.trim());

        // modificar elemento del vector
        pais = new Pais(campNombre, lapob, larenta);

        elMundo.setElementAt(pais, posiVector);

        return sSalida = sSalida + "\n"
                + "    País modificado : " + campNombre + "\n";
    }

    /**
     *
     * @param laCadena
     * @return
     */
    private synchronized String borraRegistro(String laCadena) {

        String sSalida = "  Borrar Registro\n\n";
        int espSeparador = 1;

        //  1º linea opción OP1 e inicio REGISTRO < total 14 caracteres
        int punteroCadena = 14; // desde 0 a 13 catorce caracteres           

        //  las siguientes una por campo SEPARADOR : un caracter
        //  Campo 1 Nombre
        String campNombre = laCadena.substring(punteroCadena, Constantes.MAX_TAM_NOMBRE + punteroCadena);

        if (!borra(campNombre)) {

            return sSalida = sSalida
                    + "                   ATENCIÓN\n\n"
                    + "    No se encuentra este País : " + campNombre + "\n";

        }

        return sSalida = sSalida + "\n"
                + "    País borrado : " + campNombre + "\n";

    }

    /**
     *
     * @return
     */
    private synchronized String listadoRegistros(int cuantos) {

        String sSalida = "";

        //////////////////////////////////////////////////////////
        //
        //  preparamos salida de los registros formato tabla
        //////////
        //  CABECERA
        //////////
        //  linea 1 Cantidad total de registros
        //
        sSalida = "  Hay : " + elMundo.size() + " paises\n"
                + "\n";
        //////////
        //  linea 2 Nombres de los campos
        //
        sSalida = sSalida
                + "       "
                + cadenas.ajustarLongCadena("País", Constantes.MAX_TAM_NOMBRE, " ")
                + " "
                + cadenas.ajustarLongCadena("Población", Constantes.MAX_TAM_POBLACION, " ")
                + " "
                + cadenas.ajustarLongCadena("Renta", Constantes.MAX_TAM_RENTA, " ")
                + "\n";
        /////////
        //  linea tres subrayado por campo visualizando tamaño
        //
        sSalida = sSalida
                + "       "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_NOMBRE, "_")
                + " "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_POBLACION, "_")
                + " "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_RENTA, "_")
                + "\n\n";

        //////////////////////
        //  CUERPO
        ///////
        //  1 linea por cada registro hasta el final
        //
        for (Pais articulos : elMundo) {

            sSalida = sSalida
                    + "       "
                    + cadenas.ajustarLongCadena(articulos.getNombre(), Constantes.MAX_TAM_NOMBRE, " ")
                    + " "
                    + cadenas.ajustarCadenaNUM(" " + articulos.getnPoblacion(), Constantes.MAX_TAM_POBLACION, false)
                    + " "
                    + cadenas.ajustarCadenaNUM(" " + articulos.getRenta(), Constantes.MAX_TAM_RENTA, false)
                    + "\n";

        }

        //////////////////////
        //  PIE
        ///////
        //  1 linea subrayado por campo visualizando tamaño
        //
        sSalida = sSalida
                + "       "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_NOMBRE, "_")
                + " "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_POBLACION, "_")
                + " "
                + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_RENTA, "_")
                + "\n";

        return sSalida;

    }

    private synchronized String buscarRegistro(String laCadena) {

        String sSalida = "  Buscar Registro\n\n";
        int espSeparador = 1;
        boolean encontrado = false;

        //  1º linea opción OP1 e inicio REGISTRO < total 14 caracteres
        int punteroCadena = 14; // desde 0 a 13 catorce caracteres           

        //  las siguientes una por campo SEPARADOR : un caracter
        //  Campo 1 Nombre
        String campNombre = laCadena.substring(punteroCadena, Constantes.MAX_TAM_NOMBRE + punteroCadena);

        for (Pais articulos : elMundo) {

            if (articulos.getNombre().equals(campNombre)) {
                //////////
                //  linea 2 Nombres de los campos
                //
                sSalida = sSalida
                        + "       "
                        + cadenas.ajustarLongCadena("País ", Constantes.MAX_TAM_NOMBRE, " ")
                        + " "
                        + cadenas.ajustarLongCadena("Población", Constantes.MAX_TAM_POBLACION, " ")
                        + " "
                        + cadenas.ajustarLongCadena("Renta", Constantes.MAX_TAM_RENTA, " ")
                        + "\n";
                /////////
                //  linea tres subrayado por campo visualizando tamaño
                //
                sSalida = sSalida
                        + "       "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_NOMBRE, "_")
                        + " "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_POBLACION, "_")
                        + " "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_RENTA, "_")
                        + "\n\n";
                sSalida = sSalida
                        + "       "
                        + cadenas.ajustarLongCadena(articulos.getNombre(), Constantes.MAX_TAM_NOMBRE, " ")
                        + " "
                        + cadenas.ajustarCadenaNUM(" " + articulos.getnPoblacion(), Constantes.MAX_TAM_POBLACION, false)
                        + " "
                        + cadenas.ajustarCadenaNUM(" " + articulos.getRenta(), Constantes.MAX_TAM_RENTA, false)
                        + "\n";
                //////////////////////
                //  PIE
                ///////
                //  1 linea subrayado por campo visualizando tamaño
                //
                sSalida = sSalida
                        + "       "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_NOMBRE, "_")
                        + " "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_POBLACION, "_")
                        + " "
                        + cadenas.ajustarLongCadena("_", Constantes.MAX_TAM_RENTA, "_")
                        + "\n";
                encontrado = true;
            }
            

        }

        if(!encontrado){
           
                
                 sSalida = sSalida
                    + "                   ATENCIÓN\n\n"
                    + "    No se encuentra este País : " + campNombre + "\n";
            }
        
        return sSalida;

    }

    ////////////////////////////////////////////////////////
    // Métodos auxiliares del CRUD
    /**
     *
     * @param campNombre
     * @return
     */
    private synchronized int encuentra(String campNombre) {

        for (Pais articulos : elMundo) {

            if (articulos.getNombre().equals(campNombre)) {
                System.out.println("la posición en el vector es : " + elMundo.indexOf(articulos));
                return elMundo.indexOf(articulos);
            }

        }

        return -1;

    }

    /**
     *
     * @param campNombre
     * @return
     */
    private synchronized boolean borra(String campNombre) {

        for (Pais articulos : elMundo) {

            if (articulos.getNombre().equals(campNombre)) {
                elMundo.removeElement(articulos);
                return true;
            }

        }

        return false;

    }

    

}

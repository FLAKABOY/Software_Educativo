/*
 * Esta clase se encarga de llamar a todo lo requerido del software educativo
 * 
 */
package MVC;

import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author FLAKABOY
 */
public class Software_Educativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se usa este metdo para ponerle los estilos a la vista
        FlatLightLaf.setup();
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        //AL COPNTROLADOR SE LE PASA DE PARAMETROS EL MODELO Y LA VISTA PARA QUE TRABAJE CON ELLOS
        Controlador controlador = new Controlador(vista,modelo);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.service;

/**
 *
 * @author UNA-CC
 */
public class DatosAplicacion {
    private static final GestorPrestamos gestor = new GestorPrestamos();
    
    private DatosAplicacion() {
    }

    public static GestorPrestamos getGestor() {
        return gestor;
    }
}

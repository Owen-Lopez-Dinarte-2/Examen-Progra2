package cr.ac.proyecto.model;

import cr.ac.proyecto.exception.EquipoNoDisponibleException;

public interface Prestable {
    void prestar() throws EquipoNoDisponibleException ;
    
    void devolver();
}
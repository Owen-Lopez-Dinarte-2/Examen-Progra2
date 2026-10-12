
package cr.ac.proyecto.service;

import cr.ac.proyecto.model.EstadoEquipo;
import java.time.LocalDate;
import cr.ac.proyecto.exception.EquipoNoDisponibleException;
import cr.ac.proyecto.model.Persona;
import cr.ac.proyecto.model.Equipo;
import cr.ac.proyecto.model.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private List <Persona> personas;
    private List <Equipo> equipos;
    private List <Prestamo> prestamos;
    
    public GestorPrestamos() {
personas=new ArrayList<>();
equipos=new ArrayList<>();
prestamos=new ArrayList<>();
}
    
    public void registrarPersona(Persona persona) {
    if (persona == null) {
        throw new IllegalArgumentException(
                "Debe indicar una persona.");
    }

    if (persona.getIdentificacion() == null
            || persona.getIdentificacion().isBlank()) {
        throw new IllegalArgumentException(
                "La identificación no puede estar vacía.");
    }

    if (persona.getNombre() == null
            || persona.getNombre().isBlank()) {
        throw new IllegalArgumentException(
                "El nombre no puede estar vacío.");
    }

    if (persona.getCorreo() == null
            || persona.getCorreo().isBlank()) {
        throw new IllegalArgumentException(
                "El correo no puede estar vacío.");
    }

    for (Persona registrada : personas) {
        if (registrada.getIdentificacion().trim().equals(
                persona.getIdentificacion().trim())) {
            throw new IllegalArgumentException(
                    "Ya existe una persona con esa identificación.");
        }
    }

    personas.add(persona);
}
    public Persona buscarPersona(String identificacion) {
    if(identificacion==null||identificacion.isBlank()){
    throw new IllegalArgumentException(
                "Debe indicar una identificación.");
    }
     for (Persona persona : personas) {
        if (persona.getIdentificacion().trim().equals(
                identificacion.trim())) {
            return persona;
        }
    }

    return null;
    }
    public List<Persona> listarPersonas(){
    return new ArrayList<>(personas);
    }
    
    public void registrarEquipo(Equipo equipo) {
    if (equipo == null) {
        throw new IllegalArgumentException(
                "Debe indicar un equipo.");
    }

    if (equipo.getCodigo() == null
            || equipo.getCodigo().isBlank()) {
        throw new IllegalArgumentException(
                "El código no puede estar vacío.");
    }

    if (equipo.getTipo() == null
            || equipo.getTipo().isBlank()) {
        throw new IllegalArgumentException(
                "El tipo no puede estar vacío.");
    }

    if (equipo.getMarca() == null
            || equipo.getMarca().isBlank()) {
        throw new IllegalArgumentException(
                "La marca no puede estar vacía.");
    }

    if (equipo.getModelo() == null
            || equipo.getModelo().isBlank()) {
        throw new IllegalArgumentException(
                "El modelo no puede estar vacío.");
    }

    for (Equipo registrado : equipos) {
        if (registrado.getCodigo().trim().equals(
                equipo.getCodigo().trim())) {
            throw new IllegalArgumentException(
                    "Ya existe un equipo con ese código.");
        }
    }

    equipos.add(equipo);
}
    public Equipo buscarEquipo(String codigo) {
     if (codigo == null || codigo.isBlank()) {
        throw new IllegalArgumentException(
                "Debe indicar un código.");
    }

    for (Equipo equipo : equipos) {
        if (equipo.getCodigo().trim().equals(codigo.trim())) {
            return equipo;
        }
    }

    return null;
}

public List<Equipo> listarEquipos() {
    return new ArrayList<>(equipos);
}
public Prestamo registrarPrestamo(String identificacion,
                                 String codigoEquipo,
                                 LocalDate fecha)
        throws EquipoNoDisponibleException {

    Persona persona = buscarPersona(identificacion);
    if (persona == null) {
        throw new IllegalArgumentException(
                "La persona no está registrada.");
    }

    Equipo equipo = buscarEquipo(codigoEquipo);
    if (equipo == null) {
        throw new IllegalArgumentException(
                "El equipo no está registrado.");
    }

    if (fecha == null || fecha.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException(
                "La fecha del préstamo no puede estar vacía ni ser futura.");
    }

    Prestamo prestamo = new Prestamo(persona, equipo, fecha);
    prestamos.add(prestamo);

    return prestamo;
}
public List<Prestamo> listarPrestamos() {
    return new ArrayList<>(prestamos);
}

public List<Prestamo> listarPrestamosActivos() {
    List<Prestamo> activos = new ArrayList<>();

    for (Prestamo prestamo : prestamos) {
        if ("ACTIVO".equals(prestamo.getEstado())) {
            activos.add(prestamo);
        }
    }

    return activos;
}
public void registrarDevolucion(String codigoEquipo,
                               LocalDate fecha) {
    Equipo equipo = buscarEquipo(codigoEquipo);

    if (equipo == null) {
        throw new IllegalArgumentException(
                "El equipo no está registrado.");
    }

    if (fecha == null || fecha.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException(
                "La fecha de devolución no puede estar vacía ni ser futura.");
    }

    for (Prestamo prestamo : prestamos) {
        if (prestamo.getEquipo() == equipo
                && "ACTIVO".equals(prestamo.getEstado())) {

            prestamo.registrarDevolucion(fecha);
            return;
        }
    }

    throw new IllegalStateException(
            "El equipo no tiene un préstamo activo.");
}
public void cambiarEstadoEquipo(String codigo,
                               EstadoEquipo nuevoEstado) {
    Equipo equipo = buscarEquipo(codigo);

    if (equipo == null) {
        throw new IllegalArgumentException(
                "El equipo no está registrado.");
    }

    equipo.cambiarEstado(nuevoEstado);
}
public List<Equipo> listarEquiposDisponibles() {
    List<Equipo> disponibles = new ArrayList<>();

    for (Equipo equipo : equipos) {
        if (equipo.getEstado() == EstadoEquipo.DISPONIBLE) {
            disponibles.add(equipo);
        }
    }

    return disponibles;
}

}


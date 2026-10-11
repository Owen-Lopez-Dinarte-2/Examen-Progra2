package cr.ac.proyecto.model;
import cr.ac.proyecto.exception.EquipoNoDisponibleException;
import java.time.LocalDate;

public class Prestamo {

    private Persona persona;
    private Equipo equipo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private String estado;

    public Prestamo(Persona persona, Equipo equipo,
                LocalDate fechaPrestamo)
        throws EquipoNoDisponibleException {

    if (persona == null || equipo == null || fechaPrestamo == null) {
        throw new IllegalArgumentException(
                "Debe indicar la persona, el equipo y la fecha.");
    }

    this.persona = persona;
    this.equipo = equipo;
    this.fechaPrestamo = fechaPrestamo;

    this.fechaDevolucionEsperada = fechaPrestamo.plusDays(
            persona.calcularDiasMaximos());

    equipo.prestar();
    this.estado = "ACTIVO";
}

    public Persona getPersona() {
        return persona;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public String getEstado() {
        return estado;
    }

    public void registrarDevolucion(LocalDate fecha) {
        if(!"ACTIVO".equals(estado)){
        throw new IllegalStateException(
                "El préstamo ya fue devuelto.");
        }
        if(fecha==null|| fecha.isBefore(fechaPrestamo))
            {
        throw new IllegalArgumentException(
                "La fecha de devolución no es válida.");
    }
        equipo.devolver();
        fechaDevolucionReal = fecha;
    estado = "DEVUELTO";
    }
}
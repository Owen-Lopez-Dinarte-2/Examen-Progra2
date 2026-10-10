package cr.ac.proyecto.model;

import java.time.LocalDate;

public class Prestamo {

    private Persona persona;
    private Equipo equipo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private String estado;

    public Prestamo(Persona persona, Equipo equipo,
                    LocalDate fechaPrestamo,
                    LocalDate fechaDevolucionEsperada) {
        this.persona = persona;
        this.equipo = equipo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
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
        // Se implementará en el corte 2.
    }
}
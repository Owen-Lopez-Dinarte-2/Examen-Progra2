package cr.ac.proyecto;

import cr.ac.proyecto.exception.EquipoNoDisponibleException;
import cr.ac.proyecto.model.*;
import cr.ac.proyecto.service.GestorPrestamos;
import java.time.LocalDate;

public class PruebaModelo {

    public static void main(String[] args) {
        GestorPrestamos gestor = new GestorPrestamos();

        Persona estudiante = new Estudiante(
                "101", "Ana", "ana@ejemplo.com", "E001");

        Persona funcionario = new Funcionario(
                "102", "Luis", "luis@ejemplo.com", "Informática");

        gestor.registrarPersona(estudiante);
        gestor.registrarPersona(funcionario);

        Equipo camara = new Equipo(
                "EQ01", "Cámara", "Canon", "R50");

        gestor.registrarEquipo(camara);

        System.out.println("Personas registradas: "
                + gestor.ListarPersonas().size());

        for (Persona persona : gestor.ListarPersonas()) {
            System.out.println(persona.getNombre() + ": "
                    + persona.calcularDiasMaximos() + " días");
        }

        try {
            Prestamo prestamo = gestor.registrarPrestamo(
                    "101", "EQ01", LocalDate.now());

            System.out.println("Estado del equipo: "
                    + camara.getEstado());

            System.out.println("Devolución esperada: "
                    + prestamo.getFechaDevolucionEsperada());

            try {
                gestor.registrarPrestamo(
                        "102", "EQ01", LocalDate.now());
            } catch (EquipoNoDisponibleException e) {
                System.out.println("Error controlado: "
                        + e.getMessage());
            }

            gestor.registrarDevolucion("EQ01", LocalDate.now());

            System.out.println("Estado tras devolver: "
                    + camara.getEstado());

            System.out.println("Préstamos activos: "
                    + gestor.listarPrestamosActivos().size());

            Prestable equipoDePrueba = new Equipo(
                    "PR01", "Proyector", "Epson", "X49");

            equipoDePrueba.prestar();
            equipoDePrueba.devolver();

            System.out.println("Interfaz Prestable: prueba completada");

        } catch (EquipoNoDisponibleException e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
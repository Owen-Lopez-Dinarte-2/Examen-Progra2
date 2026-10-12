package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.exception.EquipoNoDisponibleException;
import cr.ac.proyecto.model.EstadoEquipo;
import cr.ac.proyecto.model.Prestamo;
import cr.ac.proyecto.service.DatosAplicacion;
import cr.ac.proyecto.service.GestorPrestamos;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GestionController {

    private final GestorPrestamos gestor =
            DatosAplicacion.getGestor();

    @FXML
    private TextField identificacion;

    @FXML
    private TextField codigoPrestamo;

    @FXML
    private DatePicker fechaPrestamo;

    @FXML
    private TextField codigoDevolucion;

    @FXML
    private DatePicker fechaDevolucion;

    @FXML
    private TextField codigoEstado;

    @FXML
    private ComboBox<EstadoEquipo> nuevoEstado;

    @FXML
    private Label mensaje;

    @FXML
    private void initialize() {
        fechaPrestamo.setValue(LocalDate.now());
        fechaDevolucion.setValue(LocalDate.now());

        nuevoEstado.getItems().addAll(
                EstadoEquipo.DISPONIBLE,
                EstadoEquipo.EN_REPARACION);
    }

    @FXML
    private void registrarPrestamo() {
        String id = identificacion.getText().trim();
        String codigo = codigoPrestamo.getText().trim();
        LocalDate fecha = fechaPrestamo.getValue();

        if (id.isBlank() || codigo.isBlank() || fecha == null) {
            mensaje.setText(
                    "Complete la identificación, el código y la fecha.");
            return;
        }

        try {
            Prestamo prestamo = gestor.registrarPrestamo(
                    id, codigo, fecha);

            mensaje.setText("Préstamo registrado. Devolución esperada: "
                    + prestamo.getFechaDevolucionEsperada());

            identificacion.clear();
            codigoPrestamo.clear();

        } catch (EquipoNoDisponibleException e) {
            mensaje.setText(e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            mensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void registrarDevolucion() {
        String codigo = codigoDevolucion.getText().trim();
        LocalDate fecha = fechaDevolucion.getValue();

        if (codigo.isBlank() || fecha == null) {
            mensaje.setText("Complete el código y la fecha de devolución.");
            return;
        }

        try {
            gestor.registrarDevolucion(codigo, fecha);

            mensaje.setText(
                    "Devolución registrada. El equipo está disponible.");

            codigoDevolucion.clear();

        } catch (IllegalArgumentException | IllegalStateException e) {
            mensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        String codigo = codigoEstado.getText().trim();
        EstadoEquipo estado = nuevoEstado.getValue();

        if (codigo.isBlank() || estado == null) {
            mensaje.setText("Indique el código y seleccione un estado.");
            return;
        }

        try {
            gestor.cambiarEstadoEquipo(codigo, estado);

            mensaje.setText("Estado actualizado a: " + estado);
            codigoEstado.clear();

        } catch (IllegalArgumentException | IllegalStateException e) {
            mensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void volver() {
        try {
            App.cambiarVista("principal");
        } catch (IOException e) {
            mensaje.setText("No se pudo volver al menú.");
        }
    }
}
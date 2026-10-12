package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.model.Equipo;
import cr.ac.proyecto.model.Persona;
import cr.ac.proyecto.model.Prestamo;
import cr.ac.proyecto.service.DatosAplicacion;
import cr.ac.proyecto.service.GestorPrestamos;
import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConsultaController {

    private final GestorPrestamos gestor =
            DatosAplicacion.getGestor();

    @FXML
    private TableView<Persona> tablaPersonas;

    @FXML
    private TableColumn<Persona, String> colIdentificacion;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colCorreo;

    @FXML
    private TableColumn<Persona, String> colTipoPersona;

    @FXML
    private TableView<Equipo> tablaEquipos;

    @FXML
    private TableColumn<Equipo, String> colCodigo;

    @FXML
    private TableColumn<Equipo, String> colTipoEquipo;

    @FXML
    private TableColumn<Equipo, String> colMarca;

    @FXML
    private TableColumn<Equipo, String> colModelo;

    @FXML
    private TableColumn<Equipo, String> colEstadoEquipo;

    @FXML
    private TableView<Prestamo> tablaPrestamos;

    @FXML
    private TableColumn<Prestamo, String> colPersonaPrestamo;

    @FXML
    private TableColumn<Prestamo, String> colEquipoPrestamo;

    @FXML
    private TableColumn<Prestamo, String> colFechaPrestamo;

    @FXML
    private TableColumn<Prestamo, String> colFechaEsperada;

    @FXML
    private TableColumn<Prestamo, String> colFechaReal;

    @FXML
    private TableColumn<Prestamo, String> colEstadoPrestamo;

    @FXML
    private Label mensaje;

    @FXML
    private void initialize() {
        colIdentificacion.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getIdentificacion()));

        colNombre.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getNombre()));

        colCorreo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getCorreo()));

        colTipoPersona.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getClass().getSimpleName()));

        colCodigo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getCodigo()));

        colTipoEquipo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getTipo()));

        colMarca.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getMarca()));

        colModelo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getModelo()));

        colEstadoEquipo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getEstado().toString()));

        colPersonaPrestamo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getPersona().getNombre()));

        colEquipoPrestamo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getEquipo().getCodigo()));

        colFechaPrestamo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        mostrarFecha(dato.getValue().getFechaPrestamo())));

        colFechaEsperada.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        mostrarFecha(
                                dato.getValue().getFechaDevolucionEsperada())));

        colFechaReal.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        mostrarFecha(
                                dato.getValue().getFechaDevolucionReal())));

        colEstadoPrestamo.setCellValueFactory(dato ->
                new ReadOnlyStringWrapper(
                        dato.getValue().getEstado()));

        tablaPersonas.setPlaceholder(
                new Label("No hay personas registradas."));

        tablaEquipos.setPlaceholder(
                new Label("No hay equipos registrados."));

        tablaPrestamos.setPlaceholder(
                new Label("No hay préstamos registrados."));

        actualizar();
    }

    private String mostrarFecha(LocalDate fecha) {
        if (fecha == null) {
            return "Pendiente";
        }

        return fecha.toString();
    }

    @FXML
    private void actualizar() {
        tablaPersonas.getItems().setAll(gestor.listarPersonas());
        tablaEquipos.getItems().setAll(gestor.listarEquipos());
        tablaPrestamos.getItems().setAll(gestor.listarPrestamos());

        tablaPersonas.refresh();
        tablaEquipos.refresh();
        tablaPrestamos.refresh();

        mensaje.setText("Información actualizada.");
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
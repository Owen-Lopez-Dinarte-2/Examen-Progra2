package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.model.Equipo;
import cr.ac.proyecto.model.Estudiante;
import cr.ac.proyecto.model.Funcionario;
import cr.ac.proyecto.model.Persona;
import cr.ac.proyecto.service.DatosAplicacion;
import cr.ac.proyecto.service.GestorPrestamos;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistroController {

    private final GestorPrestamos gestor =
            DatosAplicacion.getGestor();

    @FXML
    private ComboBox<String> tipoPersona;

    @FXML
    private TextField identificacion;

    @FXML
    private TextField nombre;

    @FXML
    private TextField correo;

    @FXML
    private TextField datoAdicional;

    @FXML
    private TextField codigoEquipo;

    @FXML
    private ComboBox<String> tipoEquipo;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private Label mensaje;

    @FXML
    private void initialize() {
        tipoPersona.getItems().addAll("Estudiante", "Funcionario");
        tipoEquipo.getItems().addAll(
                "Computadora", "Proyector", "Cámara", "Otro");
    }

    @FXML
    private void registrarPersona() {
        try {
            if (tipoPersona.getValue() == null) {
                mensaje.setText("Seleccione el tipo de persona.");
                return;
            }

            String id = identificacion.getText().trim();
            String nombrePersona = nombre.getText().trim();
            String correoPersona = correo.getText().trim();
            String adicional = datoAdicional.getText().trim();

            if (id.isBlank() || nombrePersona.isBlank()
                    || correoPersona.isBlank() || adicional.isBlank()) {
                mensaje.setText("Complete todos los datos de la persona.");
                return;
            }

            Persona persona;

            if ("Estudiante".equals(tipoPersona.getValue())) {
                persona = new Estudiante(
                        id, nombrePersona, correoPersona, adicional);
            } else {
                persona = new Funcionario(
                        id, nombrePersona, correoPersona, adicional);
            }

            gestor.registrarPersona(persona);

            identificacion.clear();
            nombre.clear();
            correo.clear();
            datoAdicional.clear();

            mensaje.setText("Persona registrada correctamente.");

        } catch (IllegalArgumentException e) {
            mensaje.setText(e.getMessage());
        }
    }

    @FXML
    private void registrarEquipo() {
        try {
            if (tipoEquipo.getValue() == null) {
                mensaje.setText("Seleccione el tipo de equipo.");
                return;
            }

            String codigo = codigoEquipo.getText().trim();
            String marcaEquipo = marca.getText().trim();
            String modeloEquipo = modelo.getText().trim();

            if (codigo.isBlank() || marcaEquipo.isBlank()
                    || modeloEquipo.isBlank()) {
                mensaje.setText("Complete todos los datos del equipo.");
                return;
            }

            Equipo equipo = new Equipo(
                    codigo,
                    tipoEquipo.getValue(),
                    marcaEquipo,
                    modeloEquipo);

            gestor.registrarEquipo(equipo);

            codigoEquipo.clear();
            marca.clear();
            modelo.clear();

            mensaje.setText("Equipo registrado correctamente.");

        } catch (IllegalArgumentException e) {
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
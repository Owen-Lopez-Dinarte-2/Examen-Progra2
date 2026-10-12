package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class PrincipalController {
@FXML
private Label mensaje;

@FXML
private void abrirRegistro(){
    abrirVista("registro");
}

    @FXML
    private void abrirConsulta() {
        abrirVista("consulta");
    }
 @FXML
    private void abrirGestion() {
        abrirVista("gestion");
    }

    private void abrirVista(String nombre) {
        try {
            App.cambiarVista(nombre);
        } catch (IOException e) {
            mensaje.setText("No se pudo abrir la pantalla: " + nombre);
        }
    }
    
}

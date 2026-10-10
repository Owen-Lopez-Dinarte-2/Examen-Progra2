
package cr.ac.proyecto.model;

public class Estudiante extends Persona {

    private String carne;

    public Estudiante(String identificacion, String nombre,
                      String correo, String carne) {
        super(identificacion, nombre, correo);
        this.carne = carne;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    @Override
    public int calcularDiasMaximos() {
        return 3;
    }
}
package cr.ac.proyecto.model;

public class Funcionario extends Persona {

    private String departamento;

    public Funcionario(String identificacion, String nombre,
                       String correo, String departamento) {
        super(identificacion, nombre, correo);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public int calcularDiasMaximos() {
        return 7;
    }
}
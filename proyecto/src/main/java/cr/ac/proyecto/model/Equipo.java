package cr.ac.proyecto.model;

public class Equipo implements Prestable {

    private String codigo;
    private String tipo;
    private String marca;
    private String modelo;
    private EstadoEquipo estado;

    public Equipo(String codigo, String tipo,
                  String marca, String modelo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = EstadoEquipo.DISPONIBLE;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }

    @Override
    public void prestar() {
        // Se implementará en el corte 2.
    }

    @Override
    public void devolver() {
        // Se implementará en el corte 2.
    }

    public void cambiarEstado(EstadoEquipo nuevoEstado) {
        // Se implementará en el corte 2.
    }
}
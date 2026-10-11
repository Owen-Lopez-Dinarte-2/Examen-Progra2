package cr.ac.proyecto.model;
import cr.ac.proyecto.exception.EquipoNoDisponibleException;

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
    public void prestar() throws EquipoNoDisponibleException {
        if(estado!=EstadoEquipo.DISPONIBLE){
        throw new EquipoNoDisponibleException(
        "El Equipo no esta disponible para prestar.");
        }
        estado=EstadoEquipo.PRESTADO;
    }

    @Override
    public void devolver() {
        if(estado!=EstadoEquipo.PRESTADO){
        throw new IllegalStateException(
        "El equipo no está prestado.");
        }
        estado = EstadoEquipo.DISPONIBLE;
    }

    public void cambiarEstado(EstadoEquipo nuevoEstado) {
        if(nuevoEstado== null){
        throw new IllegalArgumentException(
                "Debe indicar un estado.");
        }
        
        if(estado==EstadoEquipo.PRESTADO || nuevoEstado==EstadoEquipo.PRESTADO){
           throw new IllegalArgumentException(
                "Para prestar o devolver use los métodos correspondientes.");
        } 
        estado = nuevoEstado;
        
        }
    }

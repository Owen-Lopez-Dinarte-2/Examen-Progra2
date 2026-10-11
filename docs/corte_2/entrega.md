# Corte 2 — Modelo orientado a objetos funcional

## Cambios al diseño

Se completaron los préstamos, devoluciones y cambios de estado. Se agregó `GestorPrestamos` para organizar los datos y una excepción para equipos no disponibles. La fecha esperada se calcula según el tipo de persona. El UML preliminar todavía no se ha actualizado.

## Clases implementadas

Los paquetes `model`, `service` y `exception` están dentro de `cr.ac.proyecto`.

|Clase|Paquete|Descripción|
|---|---|---|
|Persona|model|Datos comunes de las personas; clase abstracta.|
|Estudiante|model|Agrega carné y permite 3 días de préstamo.|
|Funcionario|model|Agrega departamento y permite 7 días de préstamo.|
|Equipo|model|Datos del equipo y control de su estado.|
|Prestamo|model|Persona, equipo, fechas y devolución.|
|Prestable|model|Interfaz para prestar y devolver.|
|EstadoEquipo|model|Enum con los estados del equipo.|
|GestorPrestamos|service|Registros, búsquedas, listados y operaciones.|
|EquipoNoDisponibleException|exception|Error cuando el equipo no está disponible.|
|PruebaModelo|cr.ac.proyecto|Pruebas desde consola.|

## Colecciones utilizadas

Todas se encuentran en `GestorPrestamos` y usan `ArrayList`.

| Colección   | Tipo                    | Propósito                          |
| ----------- | ----------------------- | ---------------------------------- |
| personas    | List<Persona>           | Guardar personas.                  |
| equipos     | List<Equipo>            | Guardar equipos.                   |
| prestamos   | List<Prestamo>          | Guardar el historial de préstamos. |
| activos     | List<Prestamo>          | Filtrar préstamos activos.         |
| disponibles | List<Equipo>            | Filtrar equipos disponibles.       |
| Colección   | Tipo (`List`, `Map`, …) | Clase donde se encuentra           |


## Ejemplo de herencia

```java
public class Estudiante extends Persona {

    private String carne;

    public Estudiante(String identificacion, String nombre,
                      String correo, String carne) {
        super(identificacion, nombre, correo);
        this.carne = carne;
    }

    @Override
    public int calcularDiasMaximos() {
        return 3;
    }
}
```

## Ejemplo de polimorfismo

```java
for (Persona persona : gestor.listarPersonas()) {
    System.out.println(persona.getNombre() + ": "
            + persona.calcularDiasMaximos() + " días");
}
```

## Interface

```java
public class Equipo implements Prestable {

    private EstadoEquipo estado;

    @Override
    public void prestar() throws EquipoNoDisponibleException {
        if (estado != EstadoEquipo.DISPONIBLE) {
            throw new EquipoNoDisponibleException(
                    "El equipo no está disponible para prestar.");
        }
        estado = EstadoEquipo.PRESTADO;
    }

    @Override
    public void devolver() {
        if (estado != EstadoEquipo.PRESTADO) {
            throw new IllegalStateException(
                    "El equipo no está prestado.");
        }
        estado = EstadoEquipo.DISPONIBLE;
    }
}
```

## Excepción personalizada

```java
public class EquipoNoDisponibleException extends Exception {

    public EquipoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
```

## Reglas implementadas

| Regla | Clase / método | Excepción que se lanza |
|---|---|---|
| | | |

## Evidencia de ejecución de la prueba del modelo

Clase de prueba: `<cr.ac.proyecto.PruebaModelo>`

```text
Personas registradas: 2
Ana: 3 d�as
Luis: 7 d�as
Estado del equipo: PRESTADO
Devoluci�n esperada: 2026-10-13
Error controlado: El Equipo no esta disponible para prestar.
Estado tras devolver: DISPONIBLE
Pr�stamos activos: 0
Interfaz Prestable: prueba completada
```

## Distribución del trabajo

| Integrante | Aportes principales |
| ---------- | ------------------- |
| Owen Lopez | Todo                |

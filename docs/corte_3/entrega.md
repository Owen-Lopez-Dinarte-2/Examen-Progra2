# Corte 3 — Aplicación JavaFX integrada

## Pantallas implementadas

| Vista (FXML)     | Controller            | Propósito                                                   | Captura             |
| ---------------- | --------------------- | ----------------------------------------------------------- | ------------------- |
| `principal.fxml` | `PrincipalController` | Menú de acceso a las demás pantallas.                       | `img/principal.png` |
| `registro.fxml`  | RegistroController    | Registrar estudiantes, funcionarios y equipos.              | `img/registro.png`  |
| `consulta.fxml`  | ConsultaController    | Mostrar personas, equipos y préstamos en tablas.            | `img/consulta.png`  |
| `gestion.fxml`   | GestionController     | Registrar préstamos, devoluciones y cambios por reparación. | `img/gestion.png`   |

## Navegación

Desde el menú principal se puede entrar a Registro, Consulta y Gestión. Cada pantalla tiene un botón para volver al menú. Se utiliza `App.cambiarVista()` para cambiar el contenido de la misma ventana.

## Controllers

- `PrincipalController`: abre las pantallas del menú.
- `RegistroController`: lee los formularios y solicita al gestor registrar personas y equipos.
- `ConsultaController`: llena las tablas y actualiza su información.
- `GestionController`: solicita préstamos, devoluciones y cambios de estado al gestor.

Los campos del FXML se relacionan con Java mediante `fx:id` y `@FXML`. Los botones llaman a los métodos indicados en `onAction`.

## Datos compartidos entre vistas

`DatosAplicacion` contiene una sola instancia de `GestorPrestamos`, compartida mediante `getGestor()`. Así, los registros se conservan al cambiar de pantalla. Los datos están en memoria y se pierden al cerrar la aplicación; no se utiliza base de datos.

## Validaciones

|Vista|Campo o situación|Validación|Mensaje mostrado|
|---|---|---|---|
|Registro|Tipo de persona o equipo|Debe seleccionar una opción.|Seleccione el tipo de persona/equipo.|
|Registro|Datos del formulario|No se permiten campos vacíos.|Complete todos los datos de la persona/del equipo.|
|Registro|Identificación o código repetido|El gestor rechaza duplicados.|Ya existe una persona/un equipo con esa identificación/ese código.|
|Gestión|Préstamo|Exige identificación, código y fecha.|Complete la identificación, el código y la fecha.|
|Gestión|Persona o equipo no registrado|El gestor comprueba que existan.|La persona/El equipo no está registrado.|
|Gestión|Equipo prestado o en reparación|Solo se prestan equipos disponibles.|El Equipo no esta disponible para prestar.|
|Gestión|Fechas|Se rechazan fechas futuras y devoluciones anteriores al préstamo.|Mensaje de fecha inválida generado por el modelo o gestor.|
|Gestión|Reparaciones|Exige código y estado; bloquea cambios de equipos prestados.|Indique el código y seleccione un estado, o mensaje del modelo.|

## Manejo de excepciones

<Ejemplo del flujo Usuario → Controller → Modelo → Excepción → Controller → Mensaje.>

```java
try {
    Prestamo prestamo = gestor.registrarPrestamo(id, codigo, fecha);
    mensaje.setText("Préstamo registrado. Devolución esperada: "
            + prestamo.getFechaDevolucionEsperada());
} catch (EquipoNoDisponibleException e) {
    mensaje.setText(e.getMessage());
} catch (IllegalArgumentException | IllegalStateException e) {
    mensaje.setText(e.getMessage());
}
```

## Relación interfaz–modelo

Las pantallas muestran y reciben información; los Controllers coordinan las acciones y el gestor organiza los datos. Las clases de `model`, `service` y `exception` no importan JavaFX, por lo que también se pueden usar desde consola.

## Decisiones relevantes

- Se utilizaron cuatro vistas FXML con `VBox`, `HBox` y pestañas para organizar los formularios.
- Se usan `TextField`, `ComboBox`, `DatePicker`, `Button`, `Label` y `TableView`.
- Los calendarios permiten seleccionar fechas sin escribirlas manualmente.
- Consulta muestra las fechas y estados de los préstamos; una devolución sin registrar aparece como “Pendiente”.
- `estilos.css` define colores, botones, campos y tablas. `App` carga el archivo para todas las vistas.
- Los límites de préstamo propuestos son 3 días para estudiantes y 7 para funcionarios.

## Aspectos pendientes

- El sistema no incluye eliminación, cancelación ni límite de préstamos activos por persona. No se cargan datos de ejemplo automáticamente.

## UML final

Archivo: `docs/corte_3/uml.png` o `docs/corte_3/uml.pdf`

## Distribución del trabajo

| Integrante | Aportes principales |
| ---------- | ------------------- |
| Owen Lopez | todo                |


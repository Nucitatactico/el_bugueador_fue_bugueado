
## Bug
Atributos:
- ID (String): Generado por la función hash a partitr del método generarID de la misma clase
- titulo (String)
- prioridad(int) **puede tomar los siguientes valores**:
  - 1 Baja
  - 2 Media
  - 3 Alta
- nivelSeguridad(int) **puede tomar los siguientes valores**:
  - 1 Baja
  - 2 Media
  - 3 Alta
- equipo(String) **puede tomar los siguientes valores**:
  - Ciberseguridad
  - IT
  - Diseño
  - Desarrollador
  - Tester
  - NULL
- categoria(String) **puede tomar los siguientes valores**:
  - Interfaz
  - Lógica
  - Rendimiento
  - Seguridad
- estado(int) **puede tomar los siguientes valores**:
  - 1 Registrado
  - 2 En desarrollo
  - 3 En revisión
  - 4 Concluido
  - 5 No se va arreglar
- fecha(LocalDateTime)
--------------

## Ordenamientos
Todos los ordenamientos de está clase son estáticos, reciben y regresan un ArrayList<Bug>
Métodos(Públicos):
- oredenarID
- ordenarTitulo
- ordenarPrioridad
- ordenarNivelSeguridad
- ordenarEquipo
- ordenarCategoría
- ordenarEstado
- ordenarFecha

/**
 * TALLER: ANÁLISIS DE COMPLEJIDAD Y LISTAS
 * Modalidad: Individual
 * Entrega: Archivo .java con documentación completa
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// =========================================================================
// PARTE 1: ANÁLISIS DE COMPLEJIDAD (BIG O) - JUSTIFICACIÓN
// =========================================================================
/*
 * Fragmento 1 (encontrarMaximo): O(n). Recorre el arreglo una vez linealmente.
 * Fragmento 2 (esPrimo): O(sqrt(n)). El ciclo termina al llegar a la raíz cuadrada de n.
 * Fragmento 3 (imprimirMatriz): O(n*m). Dos ciclos anidados para filas y columnas.
 * Fragmento 4 (busquedaBinaria): O(log n). Divide el problema a la mitad en cada paso.
 */

// =========================================================================
// PARTE 2: IMPLEMENTACIÓN PRÁCTICA (SISTEMA DE GESTIÓN DE TAREAS)
// =========================================================================

/**
 * Clase Tarea: Estructura de datos para representar una tarea.
 * Cumple con el [Requisito 1] del taller.
 */
class Tarea {
    private int id;
    private String descripcion;
    private int prioridad;
    private boolean completada;

    public Tarea(int id, String descripcion, int prioridad) {
        this.id = id;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = false;
    }

    // Getters y Setters necesarios para la lógica del sistema
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public int getPrioridad() { return prioridad; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }

    @Override
    public String toString() {
        String estado = completada ? "[COMPLETADA]" : "[PENDIENTE]";
        return String.format("%s ID: %d | Prioridad: %d | %s", estado, id, prioridad, descripcion);
    }
}

/**
 * Clase Principal GestionTareas: Contiene la lógica y los métodos requeridos.
 * Implementa el uso de ArrayList [Requisito 2] y métodos documentados [Requisito 3 y 4].
 */
public class GestionTareas {
    
    // Almacenamiento interno usando la colección ArrayList
    private List<Tarea> listaTareas = new ArrayList<>();

    /**
     * MÉTODO: agregarTarea
     * DESCRIPCIÓN: Añade un objeto Tarea al final de la lista.
     * COMPLEJIDAD: O(1). Insertar al final de un ArrayList es una operación constante.
     * @param tarea Objeto de tipo Tarea a insertar.
     */
    public void agregarTarea(Tarea tarea) {
        listaTareas.add(tarea);
    }

    /**
     * MÉTODO: listarTareas
     * DESCRIPCIÓN: Recorre la lista completa y muestra cada tarea en consola.
     * COMPLEJIDAD: O(n). Depende directamente del número de tareas existentes (n).
     */
    public void listarTareas() {
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            for (Tarea t : listaTareas) {
                System.out.println(t);
            }
        }
    }

    /**
     * MÉTODO: buscarPorId
     * DESCRIPCIÓN: Busca una tarea específica comparando el ID proporcionado.
     * COMPLEJIDAD: O(n). En el peor caso (ID no existe o está al final), recorre toda la lista.
     * @param id Identificador único de la tarea.
     * @return El objeto Tarea si se encuentra, de lo contrario null.
     */
    public Tarea buscarPorId(int id) {
        for (Tarea t : listaTareas) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    /**
     * MÉTODO: marcarCompletada
     * DESCRIPCIÓN: Cambia el estado 'completada' de una tarea a true usando su ID.
     * COMPLEJIDAD: O(n). Internamente llama a buscarPorId, que es una operación lineal.
     * @param id Identificador de la tarea a finalizar.
     */
    public void marcarCompletada(int id) {
        Tarea t = buscarPorId(id);
        if (t != null) {
            t.setCompletada(true);
            System.out.println("Tarea con ID " + id + " actualizada con éxito.");
        } else {
            System.out.println("Error: No se encontró la tarea con ID " + id);
        }
    }

    /**
     * MÉTODO: eliminarTarea
     * DESCRIPCIÓN: Busca y remueve una tarea de la lista mediante su ID.
     * COMPLEJIDAD: O(n). Requiere buscar el elemento y desplazar los elementos restantes.
     * @param id Identificador de la tarea a eliminar.
     */
    public void eliminarTarea(int id) {
        // removeIf recorre la lista comparando el predicado (O(n))
        if (listaTareas.removeIf(t -> t.getId() == id)) {
            System.out.println("Tarea con ID " + id + " eliminada correctamente.");
        } else {
            System.out.println("Error: No se pudo eliminar. ID no encontrado.");
        }
    }

    /**
     * MÉTODO: obtenerTareasPendientes
     * DESCRIPCIÓN: Filtra y retorna una sublista con las tareas que no han sido terminadas.
     * COMPLEJIDAD: O(n). Es necesario evaluar el estado de cada tarea en la lista original.
     * @return Lista de objetos Tarea con completada = false.
     */
    public List<Tarea> obtenerTareasPendientes() {
        return listaTareas.stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    /**
     * MÉTODO: obtenerTareasPorPrioridad
     * DESCRIPCIÓN: Filtra y retorna las tareas que coinciden con un nivel de prioridad.
     * COMPLEJIDAD: O(n). Debe inspeccionar el atributo 'prioridad' de todos los elementos.
     * @param prioridad Valor del 1 al 5.
     * @return Lista de objetos Tarea filtrados.
     */
    public List<Tarea> obtenerTareasPorPrioridad(int prioridad) {
        return listaTareas.stream()
                .filter(t -> t.getPrioridad() == prioridad)
                .collect(Collectors.toList());
    }

    /**
     * MAIN: Casos de Prueba [Requisito 5]
     * Se ejecutan pruebas para validar cada una de las funcionalidades solicitadas.
     */
    public static void main(String[] args) {
        GestionTareas app = new GestionTareas();

        System.out.println("--- PRUEBA 1: AGREGAR TAREAS ---");
        app.agregarTarea(new Tarea(101, "Revisar documentación Big O", 5));
        app.agregarTarea(new Tarea(102, "Configurar repositorio Git", 4));
        app.agregarTarea(new Tarea(103, "Descansar", 1));
        app.listarTareas();

        System.out.println("\n--- PRUEBA 2: MARCAR COMPLETADA (ID 101) ---");
        app.marcarCompletada(101);
        app.listarTareas();

        System.out.println("\n--- PRUEBA 3: MOSTRAR SOLO PENDIENTES ---");
        app.obtenerTareasPendientes().forEach(System.out::println);

        System.out.println("\n--- PRUEBA 4: FILTRAR POR PRIORIDAD 4 ---");
        app.obtenerTareasPorPrioridad(4).forEach(System.out::println);

        System.out.println("\n--- PRUEBA 5: ELIMINAR TAREA (ID 103) ---");
        app.eliminarTarea(103);
        app.listarTareas();
    }
}

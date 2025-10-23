package supermercado;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class Supermercado {
    // Número fijo de cajeras/hilos disponibles
    private static final int NUM_CAJERAS = 2; 
    // Número de clientes a generar
    private static final int NUM_CLIENTES = 8; 

    public static void main(String[] args) {
        
        // 1. Crear la lista de clientes
        List<Cliente> todosLosClientes = new ArrayList<>();
        // Generar 8 clientes con datos aleatorios
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            // Productos: entre 5 y 10 (incluidos)
            int productos = (int)(Math.random() * 6) + 5; 
            // Tiempo por producto: entre 1 y 3 segundos (incluidos)
            int tiempoPorProducto = (int)(Math.random() * 3) + 1; 
            
            todosLosClientes.add(new Cliente("Cliente " + i, productos, tiempoPorProducto));
        }

        System.out.println("🛒 INICIO de la simulación del Supermercado");
        System.out.println("Cajeras disponibles (Hilos): " + NUM_CAJERAS);
        System.out.println("Clientes a atender: " + NUM_CLIENTES + "\n");
        
        long tiempoInicioTotal = System.currentTimeMillis();

        // 2. Crear el FixedThreadPool
        // Esto crea un pool de hilos con un tamaño fijo (NUM_CAJERAS)
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_CAJERAS);

        // 3. Asignar los clientes como tareas (CajeraTask) al pool
        // El pool automáticamente gestiona la cola: los primeros 2 clientes
        // serán asignados a los 2 hilos/cajeras, y los 6 restantes esperarán
        // hasta que un hilo se libere.
        for (int i = 0; i < todosLosClientes.size(); i++) {
            Cliente cliente = todosLosClientes.get(i);
            // El pool asignará el hilo libre (Cajera 1 o Cajera 2) a esta tarea
            // La variable 'i' + 1 se usa aquí como un ID de tarea, no como el nombre real de la cajera
            // En este modelo, el pool reusa los hilos, por lo que el nombre de la cajera es el nombre del Hilo
            String nombreHilo = "Cajera-Hilo-" + (i % NUM_CAJERAS + 1); // Simplemente para dar un nombre
            executorService.submit(new CajeraTask("Cajera-Pool", cliente)); 
        }

        // 4. Detener la aceptación de nuevas tareas
        executorService.shutdown();

        // 5. Esperar a que todas las tareas terminen
        try {
            // Espera como máximo 1 hora a que todas las tareas finalicen
            System.out.println("\nEsperando a que todas las cajeras terminen...");
            boolean tareasFinalizadas = executorService.awaitTermination(1, TimeUnit.HOURS);
            
            if (!tareasFinalizadas) {
                System.err.println("❌ Timeout: Algunas tareas no terminaron a tiempo.");
            }
        } catch (InterruptedException ex) {
            // Restauramos el estado de interrupción del hilo principal
            Thread.currentThread().interrupt();
            System.err.println("❌ Hilo principal interrumpido durante la espera.");
        }

        // 6. Salida de resultados
        long tiempoFinTotal = System.currentTimeMillis();
        long duracionTotalSegundos = (tiempoFinTotal - tiempoInicioTotal) / 1000;

        System.out.println("\n=============================================");
        System.out.println("=== RESUMEN GLOBAL DE LA SIMULACIÓN CON THREAD POOL ===");
        System.out.println("Total clientes atendidos: " + NUM_CLIENTES);
        System.out.println("Total de Cajeras concurrentes: " + NUM_CAJERAS);
        System.out.println("Tiempo total empleado en atender a TODOS los clientes: " + duracionTotalSegundos + " segundos");
        System.out.println("=============================================");
    }
}
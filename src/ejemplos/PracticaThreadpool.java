package ejemplos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PracticaThreadpool {
    public static void main(String[] args){

        // Creamos un pool fijo de 3 hilos
        ExecutorService servicio = Executors.newFixedThreadPool(5);

        // Enviamos 10 tareas al pool usando un bucle
        for (int i = 1; i <= 20; i++) {
            int idPedido = i;
        // Envío de tarea al pool (tarea sin valor de retorno)
            servicio.execute(new Runnable() {
                @Override
                public void run() {
                	// En el método run() se programa el trabajo que hará el hilo del pool.

                	// Obtenemos el nombre del hilo que está ejecutando esta tarea
                    String hilo = Thread.currentThread().getName();
                    System.out.println("Inicio del pedido " + idPedido + " en " + hilo);
                    try {
                        // Simulamos que cada tarea tarda entre 0,5 y 2 segundos
                        TimeUnit.MILLISECONDS.sleep((long) (500 + Math.random() * 1500));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Fin del pedido " + idPedido + " en " + hilo);
                }
            });
        }
        // Cerramos el pool correctamente
        servicio.shutdown();
        try {
            if (!servicio.awaitTermination(30, TimeUnit.SECONDS)) {
                servicio.shutdownNow();
            }
        } catch (InterruptedException e) {
            servicio.shutdownNow();
            Thread.currentThread().interrupt(); 
        }
        System.out.println("Se han enviado todos los pedidos al pool de hilos.");
    }
}

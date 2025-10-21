package ejemplos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjemploThreadpool3Hilos {
    public static void main(String[] args) {

        // Creamos un pool fijo de 3 hilos
        ExecutorService servicio = Executors.newFixedThreadPool(3);

        // Enviamos 10 tareas al pool usando un bucle
        for (int i = 1; i <= 10; i++) {
            int idTarea = i;
        // Envío de tarea al pool (tarea sin valor de retorno)
            servicio.execute(new Runnable() {
                @Override
                public void run() {
                	// En el método run() se programa el trabajo que hará el hilo del pool.

                	// Obtenemos el nombre del hilo que está ejecutando esta tarea
                    String hilo = Thread.currentThread().getName();
                    System.out.println("Inicio de la tarea " + idTarea + " en " + hilo);
                    try {
                        // Simulamos que cada tarea tarda entre 0,5 y 1,5 segundos
                        TimeUnit.MILLISECONDS.sleep((long) (500 + Math.random() * 1000));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Fin de la tarea " + idTarea + " en " + hilo);
                }
            });
        }
        // Cerramos el pool correctamente
        servicio.shutdown();
        System.out.println("Se han enviado todas las tareas al pool de hilos.");
    }
}


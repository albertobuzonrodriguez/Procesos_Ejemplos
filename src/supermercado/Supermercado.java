package supermercado;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



class Cajera implements Runnable {
    private final Cliente[] clientes;
    private final String nombreCajera;

    public Cajera(String nombreCajera, Cliente[] clientes) {
        this.nombreCajera = nombreCajera;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        int tiempoTotal = 0;
        int totalProductos = 0;

        System.out.printf("%s empieza a trabajar con %d clientes\n", nombreCajera, clientes.length);

        for (int i = 0; i < clientes.length; i++) {
            Cliente cliente = clientes[i];
            int tiempoCliente = 0;

            for (int j = 0; j < cliente.productos; j++) {
                try {
                    Thread.sleep(1000 * cliente.tiempo);
                    tiempoCliente = tiempoCliente + cliente.tiempo;
                    tiempoTotal = tiempoTotal + cliente.tiempo;
                    System.out.printf("%s -> %s producto %d procesado\n", nombreCajera, cliente.nombre, j + 1);
                    
                } catch (InterruptedException e) {
                    System.out.printf("Hilo %s interrumpido.\n", cliente.nombre);
                }
            }

            totalProductos += cliente.productos;
            
            System.out.printf("%s termina con %s en el tiempo: %ds\n", nombreCajera, cliente.nombre, tiempoTotal);
        }

        System.out.println("\n=== RESUMEN " + nombreCajera + " ===");
        System.out.println("Total clientes atendidos: " + clientes.length);
        System.out.println("Total productos procesados: " + totalProductos);
        System.out.println("Tiempo total: " + tiempoTotal + " segundos");
        System.out.println("Promedio general: " + ((double)tiempoTotal / (double)totalProductos) + " segundos por producto");
        System.out.println("========================\n");
    }
}


public class Supermercado {
    public static void main(String[] args) {
        Cliente[] clientesCajera1 = {
            new Cliente("cliente 1", ((int)(Math.random() * 2) + 2), ((int)(Math.random() * 5) + 5)),
            new Cliente("cliente 3", ((int)(Math.random() * 2) + 2), ((int)(Math.random() * 5) + 5))
        };
        
        Cliente[] clientesCajera2 = {
            new Cliente("cliente 2", ((int)(Math.random() * 2) + 2), ((int)(Math.random() * 5) + 5)),
            new Cliente("cliente 4", ((int)(Math.random() * 2) + 2), ((int)(Math.random() * 5) + 5))
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new Cajera("Cajera 1", clientesCajera1));
        executor.execute(new Cajera("Cajera 2", clientesCajera2));

        executor.shutdown();
        
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt(); 
        }


        System.out.println("Hilo principal terminado.");
    }   
}
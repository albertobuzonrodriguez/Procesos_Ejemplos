package supermercado;


class CajeraTask implements Runnable {
    private final String nombreCajera;
    private final Cliente cliente;

    // La CajeraTask ahora solo atiende a UN Cliente
    public CajeraTask(String nombreCajera, Cliente cliente) {
        this.nombreCajera = nombreCajera;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        // Marcamos el inicio de la atención por la cajera actual (el hilo del pool)
        long startTime = System.currentTimeMillis();
        System.out.printf("👉 %s COMIENZA a atender a %s. Productos: %d\n", 
                          nombreCajera, cliente.nombre, cliente.productos);

        int tiempoClienteTotal = 0;

        // Simulación de procesamiento de productos
        for (int j = 0; j < cliente.productos; j++) {
            try {
                // Simulación del tiempo de atención por producto
                Thread.sleep(1000 * cliente.tiempo);
                tiempoClienteTotal += cliente.tiempo;
                
                System.out.printf("   %s -> %s producto %d/%d procesado. Tiempo transcurrido: %ds\n", 
                                  nombreCajera, cliente.nombre, j + 1, cliente.productos, tiempoClienteTotal);

            } catch (InterruptedException e) {
                // Restauramos el estado de interrupción del hilo
                Thread.currentThread().interrupt();
                System.out.printf("❌ Hilo de %s interrumpido mientras atendía a %s.\n", nombreCajera, cliente.nombre);
                return; // Salir del bucle y del run
            }
        }

        // Marcamos el fin de la atención
        long endTime = System.currentTimeMillis();
        long duracionTotal = (endTime - startTime) / 1000;

        System.out.printf("✅ %s TERMINA con %s. Duración total del cliente: %ds (Calculado: %ds)\n", 
                          nombreCajera, cliente.nombre, duracionTotal, cliente.productos * cliente.tiempo);
    }
}
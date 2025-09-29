package ejemplos;

import java.util.Random;

class Tarea implements Runnable {
	private final String nombre;
	Tarea(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public void run() {
		System.out.printf("Holaaaa soy el hilo %s \n", this.nombre);
		Random random = new Random();
		int randomNumber = random.nextInt(10 - 5 + 1) + 5;
		try {
			Thread.sleep(randomNumber * 1000);
			System.out.printf("Tiempo de espera de %s es de "+randomNumber+" segundos.\n", this.nombre);
		} catch (InterruptedException e) {
			System.out.printf("Hilo %s interrumpido.\n");
		}
		System.out.printf("Hilo %s terminado.\n", this.nombre);
	}
}

public class PrimerHilo {
	public static void main(String[] args) throws InterruptedException {
		Thread h1 = new Thread(new Tarea("H1"));
	    Thread h2 = new Thread(new Tarea("H2"));
		h1.start();
		h2.start();
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException ex) {
			System.out.println("Hilo principal interrumpido.");
		}
		System.out.println("Hilo principal terminado.");
	}
}

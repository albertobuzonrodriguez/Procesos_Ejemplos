package ejemplos;


class Hilo implements Runnable {

	  private final String nombre;

	  Hilo(String nombre) {
	    this.nombre = nombre;
	  }

	  @Override
	  public void run() {
	    System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
	    
	      int pausa = (int) (Math.random() * 5) + 5;
	      System.out.printf("Hilo: %s hace pausa de %d ms.\n", this.nombre, pausa);
	      try {
	        Thread.sleep(pausa * 1000);
	      } catch (InterruptedException e) {
	        System.out.printf("Hilo %s interrumpido.\n", this.nombre);
	      }
	    
	    System.out.printf("Hilo %s terminado.\n", this.nombre);
	  }

	}

public class Lanzahilosyesperaqueterminen {

	public static void main(String[] args) {

	    Thread h1 = new Thread(new Hilo("H1"));
	    Thread h2 = new Thread(new Hilo("H2"));
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

package ejemplos;

class Bomba implements Runnable {
	private final String nombre;
	
	public Bomba (String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.printf("A %s le quedan "+i+" segundos.\n", this.nombre);
			} catch (InterruptedException e) {
				System.out.printf("Hilo %s interrumpido.\n");
			}
		}
		System.out.printf("%s : BOOM!!!!!!!!!\n", this.nombre);
		
	}
	
}

public class HiloBomba {

	public static void main(String[] args) throws InterruptedException {
		Thread h1 = new Thread(new Bomba("BOMBA 1"));
	    Thread h2 = new Thread(new Bomba("BOMBA 2"));
	    Thread h3 = new Thread(new Bomba("BOMBA 3"));
		h1.start();
		h2.start();
		h3.start();
		try {
			h1.join();
			h2.join();
			h3.join();
		} catch (InterruptedException ex) {
			System.out.println("Hilo principal interrumpido.");
		}
		System.out.println("Hilo principal terminado.");
	}

}

package supermercado;

public class Cliente {
    
    public String nombre;
	public int tiempo;
	public int productos;
	
	public Cliente (String nombre, int tiempo, int productos) {
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.productos = productos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getProductos() {
		return productos;
	}

	public void setProductos(int productos) {
		this.productos = productos;
	}
}

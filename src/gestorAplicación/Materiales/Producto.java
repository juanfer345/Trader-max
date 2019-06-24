package gestorAplicaci�n.Materiales;

import gestorAplicaci�n.Usuarios.Vendedor;
import java.util.HashMap;

public class Producto {

	public static String categorias [] = {"Belleza", "Carros", "Deportes", "Electrodomesticos", "Hogar", "Juegos", "Libros", "Musica", "Tecnologia", "Vestimenta"};
	public HashMap<Integer, Rese�a> Rese�as = new HashMap<>();
	private Vendedor vendedor;
	private String nombreProducto;
	private String categoria;
	private double precio;
	private int codigoProducto; 
	private static int cont;
	private int cantidad;
	
	public Producto() {
	
	}
	
	public Producto(Vendedor vendedor, double precio, int cant, String nombre, String categoria) {
		this.vendedor = vendedor;
		this.precio = precio;
		this.cantidad = cant;
		this.nombreProducto = nombre;
		this.categoria = categoria;
		this.codigoProducto = cont++;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public HashMap<Integer, Rese�a> getRese�as() {
		return Rese�as;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	public static boolean existeCategoria(String nom) {
		boolean aux=false;
		for(int i = 0; i < 10; i++) {		//Recorrer el Array de categorias
			if(nom == Producto.categorias[i]) {
				aux = true;
				break;
			}
		}
		return aux;
	}
	
	@Override
	public String toString() {
		return "Producto [Nombre=" + nombreProducto + ", Categoria=" + categoria + ", Precio=" + precio
				+ ", C�digo=" + codigoProducto + ", Cantidad=" + cantidad + "]";
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}



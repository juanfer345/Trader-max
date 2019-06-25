package uiMain.Funcionalidades;

import java.io.IOException;

import gestorAplicación.Usuarios.Vendedor;
import uiMain.OpcionDeMenu;

public class aumentarCantidad extends OpcionDeMenu{
	//Y si se considera el caso de agregar cantidad por codigo ya que el catalogo es static entonces pueden haber varios -
	//- productos con el mismo nombre (Lo voy a dejar por si algo, igual que la funcion en Vendedor como comentario)
	
/*	public void ejecutar() {
		System.out.println("Ingrese el codigo del producto: ");
		int codigo = scn.nextInt();
		System.out.println("Ingrese la cantidad a agregar: ");
		int cantidad = scn.nextInt();
		Vendedor.AumentarCantidad(codigo, cantidad);
	}
*/
	public void ejecutar() throws IOException {
		System.out.println("Ingrese el nombre del producto: ");
		String nombre = br.readLine().trim();
		System.out.println("Ingrese la cantidad a agregar: ");
		int cantidad = Integer.parseInt(br.readLine().trim());
		Vendedor.aumentarCantidad(nombre, cantidad);
	}

	public String toString() {
		return "Aumentar cantidad de producto";
	}

}

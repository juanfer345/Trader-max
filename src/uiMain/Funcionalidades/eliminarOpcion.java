package uiMain.Funcionalidades;

import java.io.IOException;

import uiMain.OpcionDeMenu;

public class eliminarOpcion extends OpcionDeMenu { // opci gestorAplicación.Usuarios.Vendedoron 12
	@Override
	public void ejecutar() throws NumberFormatException, IOException {
		for (int i = 0; i < opcionesActivas.size(); i++) {
			System.out.println(opcionesActivas.get(i));
		}
		System.out.println("Ingrese el indice de la opcion que quiera eliminar: ");
		int Aeliminar = Integer.parseInt(br.readLine().trim());
		opcionesActivas.remove(Aeliminar);
	}

	@Override
	public String toString() {
		return "Eliminar opción";
	}

}
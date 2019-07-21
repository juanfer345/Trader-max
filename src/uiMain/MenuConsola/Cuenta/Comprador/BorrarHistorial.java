/*	Clase BorrarHistorial (p�blica)        

	Prop�sito: Opci�n de men� del usuario, le permite realizar acciones en el programa 
	           manipulando sus atributos y elementos
 */

package uiMain.MenuConsola.Cuenta.Comprador;

import java.io.IOException;

import gestorAplicacion.InicializacionAplicacion;
import gestorAplicacion.Usuarios.Comprador;
import uiMain.ControlErrorDatos;
import uiMain.OpcionDeMenu;

public class BorrarHistorial extends OpcionDeMenu {

	public void ejecutar() throws IOException {
		/*
		 Prop�sito: Ejecutar el metodo BorrarHistorial() haciendo los respectivos 
		            controles de error del ingreso de datos
		 */

		Comprador comp = (Comprador) InicializacionAplicacion.usuarioActivo;
		
		if (!comp.getHistorial().isEmpty()) {
			sb.append("\nUsted ha elegido la opci�n para borrar su historial de compras. �Que desea hacer?");
			sb.append("\n0. Volver al men� y cancelar el proceso.");
			sb.append("\n1. Continuar con el proceso.\n");
			
			// Ingreso del dato por parte del usuario
			ControlErrorDatos.controlByte((byte) 1, (byte) 1, sb.toString() + "Ingrese su eleccion", "El dato que ingres� es inv�lido, vuelva a intentarlo");
			if (controlError) {System.out.println(); return;}
		
			//Ejecuci�n del m�todo
			System.out.println(comp.borrarHistorial());
			
		} else {
			System.out.println("El historial ya esta vacio.\n");
		}
	}

	public String toString() {
		return "Borrar Historial";
	}
}
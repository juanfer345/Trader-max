/*
	Clase LecturaBD (p�blica)
	
	Prop�sito: Leer la base de datos seleccionada e inicializar las estructuras
	           de datos correspondientes donde se van a guardar todos los objetos
	           contenidos en ella.
*/
package baseDatos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import gestorAplicaci�n.InicializacionAplicacion;
import gestorAplicaci�n.Materiales.CarritoDeCompras;
import gestorAplicaci�n.Materiales.CuentaBancaria;
import gestorAplicaci�n.Materiales.Producto;
import gestorAplicaci�n.Materiales.Rese�a;
import gestorAplicaci�n.Usuarios.Administrador;
import gestorAplicaci�n.Usuarios.Comprador;
import gestorAplicaci�n.Usuarios.Vendedor;

public class LecturaBD {
    
	public static void PrincipalLecturaBD(String BDComp, String BDVend, String BDAdm, String BDCuentBanc, String BDCarr, 
										  String BDCat, String BDProd, String BDRes) throws IOException {
		/*
	  		M�todo PrincipalLecturaBD (p�blico)
		   	
	   		Prop�sito: Ejecutar el proceso de lectura de todas las bases de datos utilizadas en el programa.
	   		
	   		Variables de Entrada:
	   		
	   		- BDComp: Nombre de la base de datos de los compradores.
	   		- BDVend: Nombre de la base de datos de los vendedores.
	   		- BDAdm: Nombre de la base de datos de los administradores.
	   		- BDCuentBanc: Nombre de la base de datos de las cuentas bancarias.
	   		- BDCarr: Nombre de la base de datos de los carritos de compras.
	   		- BDCat: Nombre de la base de datos del cat�logo.
	   		- BDProd: Nombre de la base de datos de los productos.
	   		- BDRes: Nombre de la base de datos de las rese�as.
	   	*/
		Deque <Integer> auxComp = new LinkedList <>();
		Deque <Integer> auxVend = new LinkedList <>();
		Deque <Integer> auxCarr = new LinkedList <>();
		Deque <Integer> auxCat = new LinkedList <>();
		Deque <Integer> auxProd = new LinkedList <>();
        
		//Lectura de las cuentas
		lecturaCompradores(BDComp, InicializacionAplicacion.getBDCompradores(), auxComp);
		lecturaVendedores(BDVend, InicializacionAplicacion.getBDVendedores(), auxVend);
		lecturaAdministradores(BDAdm, InicializacionAplicacion.getBDAdministradores());
		
		//Lectura de las cuentas bancarias
		lecturaCuentasBancarias(BDCuentBanc, InicializacionAplicacion.getBDCuentasBancarias());

		//Lectura de los carritos de compras
		lecturaCarritos(BDCarr, InicializacionAplicacion.getBDCarritos(), auxCarr);
		
		//Lectura del cat�logo
		lecturaCatalogo(BDCat, auxCat);
		
		//Lectura de los productos
		lecturaProductos(BDProd, InicializacionAplicacion.getBDProductos(), auxProd);
		
		//Lectura de las rese�as
		lecturaRese�as(BDRes, InicializacionAplicacion.getBDRese�as());
		
		//Asignando los elementos restantes utilizando las colas auxiliares
		complementoLectura(InicializacionAplicacion.getBDCompradores(), InicializacionAplicacion.getBDVendedores(),
				  		   InicializacionAplicacion.getBDAdministradores(), Vendedor.catalogo, 
				  		   InicializacionAplicacion.getBDCuentasBancarias(), InicializacionAplicacion.getBDCarritos(), 
				  		   InicializacionAplicacion.getBDProductos(), InicializacionAplicacion.getBDRese�as(), 
				  		   auxComp, auxVend, auxCarr, auxCat, auxProd);
	}

	private static void lecturaCompradores(String NombreBD, HashMap <Integer, Comprador> HM, Deque <Integer> aux) throws IOException {
		
		Comprador val;
	    BufferedReader Br = null;
	    String [] Dat, auxS;

		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));

    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new Comprador();
    	    	val.setId(Integer.parseInt(Dat[0]));					//Identificador �nico de comprador
    	    	val.setNombre(Dat[1]);									//Nombre comprador
    	    	val.setCorreo(Dat[2]);									//Correo comprador
    	    	val.setPassword(Dat[3]);								//Contrase�a comprador
    	    	val.setCedula(Integer.parseInt(Dat[4]));				//C�dula comprador
    	    	aux.add(Integer.parseInt(Dat[5]));						//Apuntador a cuenta bancaria de comprador
    	    	aux.add(Integer.parseInt(Dat[6]));						//Apuntador a carrito de compras
    	    	auxS = Dat[7].split(",");								//Apuntadores a productos del historial del comprador
    	    	
    	    	//Condicional para el caso donde no existe historial
    	    	if (!auxS[0].equals("##")) {
	    	    	for (int i = 0; i < auxS.length; i++) {
	    	    		aux.add(Integer.parseInt(auxS[i]));				//Asignaci�n de apuntadores a productos del historial del comprador	
	    	    	}
    	    	}
    	        HM.put(val.getId(), val);								//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
            
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaVendedores(String NombreBD, HashMap <Integer, Vendedor> HM, Deque <Integer> aux) throws IOException {
		
		Vendedor val;
	    BufferedReader Br = null;
	    String [] Dat;

		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));

    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new Vendedor();
    	    	val.setId(Integer.parseInt(Dat[0]));					//Identificador �nico de vendedor
    	    	val.setNombre(Dat[1]);									//Nombre vendedor
    	    	val.setCorreo(Dat[2]);									//Correo vendedor
    	    	val.setPassword(Dat[3]);								//Contrase�a vendedor
	    		val.setCedula(Integer.parseInt(Dat[4]));				//C�dula vendedor
    	    	aux.add(Integer.parseInt(Dat[5]));						//Apuntador a cuenta bancaria del vendedor
    	        HM.put(val.getId(), val);								//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
            
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaAdministradores(String NombreBD, HashMap <Integer, Administrador> HM) throws IOException {
		
		Administrador val;
	    BufferedReader Br = null;
	    String [] Dat;
	    
		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));
            
    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new Administrador();
    	    	val.setId(Integer.parseInt(Dat[0])); 					//Identificador �nico de administrador
    	    	val.setNombre(Dat[1]);									//Nombre administrador
    	    	val.setCorreo(Dat[2]);									//Correo administrador
    	    	val.setPassword(Dat[3]);								//Contrase�a administrador
    	    	val.setCedula(Integer.parseInt(Dat[4]));				//C�dula administrador
    	        HM.put(val.getId(), val);								//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
            
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaCuentasBancarias(String NombreBD, HashMap <Integer, CuentaBancaria> HM) throws NumberFormatException, IOException {
		
		CuentaBancaria val;
	    BufferedReader Br = null;
	    String [] Dat;
	    
		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));
            
    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new CuentaBancaria();
    	    	val.setId(Integer.parseInt(Dat[0]));					//Identificador �nico de la cuenta bancaria
    	    	val.setPropietario(Dat[1]);								//Titular de la cuenta bancaria
    	    	val.setSaldo(Double.parseDouble(Dat[2]));				//Saldo de la cuenta bancaria
    	    }
    	    
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaCarritos(String NombreBD, HashMap <Integer, CarritoDeCompras> HM, Deque <Integer> aux) throws IOException {
		
		CarritoDeCompras val;
	    BufferedReader Br = null;
	    String [] Dat, auxS;

		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));

    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new CarritoDeCompras();
    	    	val.setId(Integer.parseInt(Dat[0])); 					//Identificador �nico del carrito
    	    	val.setTotalproductos(Integer.parseInt(Dat[1]));		//Total de productos del carrito
    	    	val.setPrecioTotal(Double.parseDouble(Dat[2]));			//Precio total de los productos en el carrito
    	    	auxS = Dat[3].split(",");								//Apuntadores a productos del carrito
    	    	
    	    	//Condicional para el caso donde el carrito no tiene productos
    	    	if (!auxS[0].equals("##")) {
	    	    	for (int i = 0; i < auxS.length; i++) {
	    	    		aux.add(Integer.parseInt(auxS[i]));				//Asignaci�n de apuntadores a productos del carrito
	    	    	}
    	    	}
    	        HM.put(val.getId(), val);								//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
            
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaCatalogo(String NombreBD, Deque <Integer> aux) throws IOException {

	    BufferedReader Br = null;
	    String [] Dat;
	    
		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));
            
    	    //Ciclo para obtener la informaci�n
            for (int i = 0; i < (Dat = Br.readLine().split(",")).length; i++) {
            	aux.add(Integer.parseInt(Dat[i]));
            }
    	    
    		//Mensaje de confirmaci�n
            if (!aux.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaProductos(String NombreBD, HashMap <Integer, Producto> HM, Deque <Integer> aux) throws NumberFormatException, IOException {
		
		Producto val;
	    BufferedReader Br = null;
	    String [] Dat, auxS;

		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));

    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new Producto();
    	    	val.setCodigoProducto(Integer.parseInt(Dat[0]));	//Identificador �nico del producto
    	    	val.setNombreProducto(Dat[1]);						//Nombre del producto
    	    	aux.add(Integer.parseInt(Dat[2]));					//Apuntador al vendedor del producto
    	    	val.setPrecio(Double.parseDouble(Dat[3]));			//Precio total del producto
    	    	val.setCategoria(Dat[4]);							//Categor�a del producto
    	    	val.setCantidad(Integer.parseInt(Dat[5]));			//Cantidad del producto 
    	    	auxS = Dat[6].split(",");							//Apuntadores de rese�as del producto
    	    	
    	    	//Condicional para el caso donde el producto no tiene rese�as
    	    	if (!auxS[0].equals("##")) {
	    	    	for (int i = 0; i < auxS.length; i++) {
	    	    		aux.add(Integer.parseInt(auxS[i]));			//Asignaci�n de apuntadores de rese�as del producto	
	    	    	}
    	    	}
    	        HM.put(val.getCodigoProducto(), val);				//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
    	    
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }
	}
	
	private static void lecturaRese�as(String NombreBD, HashMap <Integer, Rese�a> HM) throws NumberFormatException, IOException {
		
		Rese�a val;
	    BufferedReader Br = null;
	    String [] Dat;
	    
		//Intento de apertura y lectura de la base de datos
        try {
            Br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\baseDatos\\temp\\" + NombreBD + ".txt"));
            
    	    //Ciclo para obtener la informaci�n
            while (!(Dat = Br.readLine().split(";"))[0].equals("#")) {
    	    	val = new Rese�a();
    	    	val.setId(Integer.parseInt(Dat[0])); 					//Identificador �nico de la rese�a
    	    	val.setEstrellas(Integer.parseInt(Dat[1]));				//Nombre administrador
    	    	val.setComentario(Dat[2]);								////Comentario de la rese�a
    	        HM.put(val.getId(), val);								//Asignaci�n del objeto a la estructura de datos correspondiente
    	    }
    	    
    		//Mensaje de confirmaci�n
            if (!HM.isEmpty()) {
            	//Caso A: La base de datos se carg� correctamente
            	System.out.println("Base de datos " + NombreBD + ".txt" + "cargada exitosamente");
            	
            } else {
            	//Caso B: La base de datos se encontraba vac�a
            	System.out.println("Advertencia: la base de datos " + NombreBD + ".txt" + "se encuentra vac�a");
            }
    		//Cerrado de la base de datos
    	    Br.close();
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage() + " Error al intentar abrir la base de datos" + NombreBD + ".txt");
            System.exit(0);
        }   
	}
	
	private static void complementoLectura(HashMap <Integer, Comprador> BDCompradores, HashMap <Integer, Vendedor> BDVendedores, 
										   HashMap <Integer, Administrador> BDAdministradores, HashMap <Integer, Producto> catalogo, 
										   HashMap <Integer, CuentaBancaria> BDCuentasBancarias, HashMap <Integer, CarritoDeCompras> bdCarritos, 
										   HashMap <Integer, Producto> BDProductos, HashMap <Integer, Rese�a> BDRese�as, 
										   Deque <Integer> auxComp, Deque <Integer> auxVend, Deque <Integer> auxCarr, Deque <Integer> auxCat, 
										   Deque <Integer> auxProd) {
		int i, j;
		
		//Completando la informaci�n de los compradores
        for (Map.Entry <Integer, Comprador> entry : BDCompradores.entrySet()) {
            entry.getValue().setCuentaBancaria(BDCuentasBancarias.get(auxComp.poll())); //Asignaci�n de cuenta bancaria
            entry.getValue().setCarrito(bdCarritos.get(auxComp.poll()));				//Asignaci�n de carrito
            
            //Asignaci�n de productos al historial del comprador
            for (i = 0; i < auxComp.size(); i++) {
            	j = auxComp.poll();
            	entry.getValue().getHistorial().put(j, BDProductos.get(j));
            }
        }

		//Completando la informaci�n de los vendedores
        for (Map.Entry <Integer, Vendedor> entry : BDVendedores.entrySet()) {
            entry.getValue().setCuentaBancaria(BDCuentasBancarias.get(auxVend.poll())); //Asignaci�n de cuenta bancaria
        }

		//Completando la informaci�n de los carritos
        for (Map.Entry <Integer, CarritoDeCompras> entry : bdCarritos.entrySet()) {
            //Asignaci�n de productos al carrito
            for (i = 0; i < auxCarr.size(); i++) {
            	entry.getValue().productos.add(BDProductos.get(auxCarr.poll()));
            }
        }
        
		//Completando la informaci�n de los productos
        for (Map.Entry <Integer, Producto> entry : BDProductos.entrySet()) {
        	entry.getValue().setVendedor(BDVendedores.get(auxProd.poll()));
            //Asignaci�n de productos al carrito
            for (i = 0; i < auxProd.size(); i++) {
            	j = auxProd.poll();
            	entry.getValue().getRese�as().put(j, BDRese�as.get(j));
            }
        }
	}
}
package gestorAplicación.Usuarios;

import gestorAplicación.Materiales.CuentaBancaria;

public abstract class Cuenta {
    private CuentaBancaria cuentaBancaria; //otra variable de # usuarios registrados para usar el satatic?
    private String nombre, correo, password; 
    public int id;
    private String cedula; 
    static protected int contador = 0;
    
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}
	
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}



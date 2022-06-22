package sec.comisiones.mapeos;


import java.io.Serializable;
import java.sql.Date;



@SuppressWarnings("serial")
public class Usuarios implements Serializable {

	private int id;
	private String nombre;
	private String password;
	private int idEmp;
	private int creo;
	private int modifico;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Boolean eliminado;

	private String nombreRol;
	private String nombreEmp;
	private String apellidoEmp;
	private int dniEmp;
	

	public Usuarios() {
	}
	
	public Usuarios(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Usuarios(int id, String nombre, String password, Roles rol, int idEmp, String nomEmp, String apellidoEmp, int dniEmp) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.nombreRol = rol.getNombre();
		this.idEmp = idEmp;
		this.nombreEmp = nomEmp;
		this.apellidoEmp = apellidoEmp;
		this.dniEmp = dniEmp;
	}
	
	public Usuarios(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuarios(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}

	public Usuarios(int id, String nombre, String password,	int idPersonal, Boolean eliminado) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.idEmp = idPersonal;
		this.eliminado = eliminado;

	}
	
	public Usuarios(int id, String nombre, String password,	int idPersonal, Boolean eliminado, String nomEmp, String apellidoEmp, int dniEmp) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.idEmp = idPersonal;
		this.eliminado = eliminado;
		this.nombreEmp = nomEmp;
		this.apellidoEmp = apellidoEmp;
		this.dniEmp = dniEmp;

	}
	
	
	public Usuarios(int id, String nombre, String password,	int idPersonal, int creo,
			int modifico, Date fechaCreacion, Date fechaModificacion, Boolean eliminado) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.idEmp = idPersonal;
		this.creo = creo;
		this.modifico = modifico;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.eliminado = eliminado;
	}

	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdEmp() {
		return this.idEmp;
	}

	public void setIdEmp(int empleado) {
		this.idEmp = empleado;
	}
	public int getCreo() {
		return creo;
	}
	public void setCreo(int creo) {
		this.creo = creo;
	}
	public int getModifico() {
		return modifico;
	}
	public void setModifico(int modifico) {
		this.modifico = modifico;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}


	public String getNombreRol() {
		return this.nombreRol;
	}
	
	public String getNombreEmp() {
		return this.nombreEmp;
	}
	
	public String getApellidoEmp() {
		return this.apellidoEmp;
	}
	
	public int getDniEmp() {
		return this.dniEmp;
	}
	
}
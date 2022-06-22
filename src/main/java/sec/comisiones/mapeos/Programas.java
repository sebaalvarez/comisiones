package sec.comisiones.mapeos;

public class Programas {
	private int id;
	private String nombre;
	private String descripcion;
	private int idArea;
	private int idEmpleado;
	
	private String descArea;
	private String descEmpleado;

	
	public Programas() {}
	
	public Programas(int id, String nombre, String descripcion, int idArea, int idEmpleado, String descArea, String descEmpleado){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.idArea=idArea;
		this.idEmpleado = idEmpleado;
		this.descArea=descArea;
		this.descEmpleado=descEmpleado;

	}
	
	public Programas(int id, String nombre, String descripcion, Areas Area){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.idArea=Area.getId();
		this.descArea= Area.getId() +" - "+Area.getNombre();

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getIdArea() {
		return idArea;
	}
	
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	
	public String getDescArea() {
		return descArea;
	}
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getDescEmpleado() {
		return descEmpleado;
	}
	
	
}

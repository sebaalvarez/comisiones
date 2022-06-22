package sec.comisiones.mapeos;

@SuppressWarnings("unused")
public class Especialidades 	{
	private int id;
	private String nombre;
	private String descripcion;
	private String descEspecialidad;
	
	public Especialidades() {}
	
	public Especialidades(int id, String nombre, String descripcion){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		
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
	
	public String getDescEspecialidad() {
		return nombre +"-"+descripcion;
	}
}

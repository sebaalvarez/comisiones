package sec.comisiones.mapeos;

public class Provincias {
	private int id;
	private String nombre;
	private String descripcion;
	private boolean local;
	
	public Provincias() {}
	
	public Provincias(int id, String nombre, String descripcion, boolean local){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.local=local;
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

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}
	
	
	
	
}

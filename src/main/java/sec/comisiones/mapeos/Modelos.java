package sec.comisiones.mapeos;

public class Modelos {
	private int id;
	private String nombre;
	private String descripcion;
	private int idMarca;
	
	private String descMarca;

	
	public Modelos() {}
	
	public Modelos(int id, String nombre, String descripcion, int idMarca){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.idMarca=idMarca;

	}
	
	public Modelos(int id, String nombre, String descripcion, Marcas Marca){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.idMarca=Marca.getId();
		this.descMarca= Marca.getId() +" - "+Marca.getNombre();

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
	
	public int getIdMarca() {
		return idMarca;
	}
	
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	
	public String getDescMarca() {
		return descMarca;
	}
	
}

package sec.comisiones.mapeos;

public class Categorias {

	private int id;
	private String nombre;
	private String descripcion;
	private float montoProvincial;
	private float montoNacional;
	private float montoInternacional;
	@SuppressWarnings("unused")
	private String descCategoria;
	
	
	public Categorias() {}
	
	public Categorias(int id, String nombre, String descripcion, float montoProv, float montoNacional, float montoInternacional){
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.montoProvincial=montoProv;
		this.montoNacional=montoNacional;
		this.montoInternacional=montoInternacional;
		
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
	
	public float getMontoProvincial() {
		return montoProvincial;
	}

	public void setMontoProvincial(float montoProvincial) {
		this.montoProvincial = montoProvincial;
	}
	

	public float getMontoNacional() {
		return montoNacional;
	}

	public void setMontoNacional(float montoNacional) {
		this.montoNacional = montoNacional;
	}

	public float getMontoInternacional() {
		return montoInternacional;
	}

	public void setMontoInternacional(float montoInternacional) {
		this.montoInternacional = montoInternacional;
	}

	public String getDescCategoria() {
		return nombre +"-"+descripcion;
	}
	
}

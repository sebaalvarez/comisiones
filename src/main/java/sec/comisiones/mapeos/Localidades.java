package sec.comisiones.mapeos;

public class Localidades {
	private int id;
	private String nombre;
	private int idProvincia;
	private float kmDesdeOrigen;
	
	private String descProvincia;

	
	public Localidades() {}
	
	public Localidades(int id, String nombre, float kmDesdeOrigen, int idProvincia){
		this.id=id;
		this.nombre=nombre;
		this.kmDesdeOrigen=kmDesdeOrigen;
		this.idProvincia=idProvincia;

	}
	
	public Localidades(int id, String nombre, float kmDesdeOrigen, int idProvincia, String descProvincia){
		this.id=id;
		this.nombre=nombre;
		this.kmDesdeOrigen=kmDesdeOrigen;
		this.idProvincia=idProvincia;
		this.descProvincia= descProvincia;

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
	
	public float getKmDesdeOrigen() {
		return kmDesdeOrigen;
	}
	
	public void setKmDesdeOrigen(float kmDesdeOrigen) {
		this.kmDesdeOrigen = kmDesdeOrigen;
	}
	
	public int getIdProvincia() {
		return idProvincia;
	}
	
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	
	public String getDescProvincia() {
		return descProvincia;
	}
	
}

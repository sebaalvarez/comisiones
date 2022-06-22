package sec.comisiones.mapeos;

public class DetalleDestinos {

	private int id;
	private int idComision;
	private int idLocalidad;
	private int idProvincia;
	
	private String descLocalidad;
	private String descProvincia;
	
	
	
	public DetalleDestinos(){
		
	}
	
	public DetalleDestinos(int id, int idComision, int idLocalidad, int idProvincia, String descLocalidad, String descProvincia) {
		this.id = id;
		this.idComision = idComision;
		this.idLocalidad = idLocalidad;
		this.idProvincia = idProvincia;
		this.descLocalidad = descLocalidad;
		this.descProvincia = descProvincia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdComision() {
		return idComision;
	}

	public void setIdComision(int idComision) {
		this.idComision = idComision;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	public String getDescLocalidad() {
		return descLocalidad;
	}

	public void setDescLocalidad(String descLocalidad) {
		this.descLocalidad = descLocalidad;
	}

	public String getDescProvincia() {
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}
	
	
	
	
	
}

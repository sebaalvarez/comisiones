package sec.comisiones.mapeos;

public class Vehiculos {
	private int id;
	private String dominio;
	private int idMarca;
	private int idModelo;
	private int idTipo;
	private int anio;
	private boolean activo;
	
	private String descMarca;
	private String descModelo;
	private String descTipo;

	
	public Vehiculos() {}
	
	public Vehiculos(int id, String dominio, int anio, int idMarca, int idModelo, int idTipo){
		this.id=id;
		this.dominio=dominio;
		this.anio=anio;
		this.idMarca=idMarca;
		this.idModelo=idModelo;
		this.idTipo=idTipo;
	}
	
	public Vehiculos(int id, String dominio, int anio, Marcas marca, Modelos modelo, TiposVehiculos tipo){
		this.id=id;
		this.dominio=dominio;
		this.anio=anio;
		this.idMarca=marca.getId();
		this.descMarca= marca.getId() +" - "+marca.getNombre();
		this.idModelo=modelo.getId();
		this.descModelo= modelo.getId() +" - "+modelo.getNombre();
		this.idTipo=tipo.getId();
		this.descTipo= tipo.getId() +" - "+tipo.getNombre();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDominio() {
		return dominio;
	}
	
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	
	public int getIdMarca() {
		return idMarca;
	}
	
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	

	
	public int getIdModelo() {
		return idModelo;
	}
	
	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}
	

	
	public int getIdTipo() {
		return idTipo;
	}
	
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	

	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	public String getDescMarca() {
		return descMarca;
	}
	
	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	
	public String getDescModelo() {
		return descModelo;
	}
	
	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}
	

	
	public String getDescTipo() {
		return descTipo;
	}
	
	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}
	
	
}

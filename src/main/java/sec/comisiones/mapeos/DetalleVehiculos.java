package sec.comisiones.mapeos;

import java.util.Date;

@SuppressWarnings("unused")
public class DetalleVehiculos {

    private int id;
	private int idComision;
	private int userAutorizacion;
	private Date fechaAutorizacion;
	private int idChofer;
	private int idVehiculo;
	private float cantEstKmRecorrer;
	private float cantEstLitrosComb;
	private float impEstTarjetaComb;
	private float impEstExceso;
	private String observaciones;
	
	private int idMarca;
	private int idModelo;
	
	private String descAutorizador;
	private String descChofer;
	private String descMarca;
	private String descModelo;
	private String descDominio;
	
	
	public DetalleVehiculos(){
		
	}
	
	public DetalleVehiculos(int id, int idComision, int userAutorizacion,Date fechaAutorizacion, int idChofer, int idVehiculo,
			float cantEstKmRecorrer, float cantEstLitrosComb,float impEstTarjetaComb, float impEstExceso, String observaciones,
			String descAutorizador, String descChofer, int idMarca,int idModelo, String descDominio) {
		this.id = id;
		this.idComision = idComision;
		this.userAutorizacion = userAutorizacion;
		this.fechaAutorizacion = fechaAutorizacion;
		this.idChofer = idChofer;
		this.idVehiculo = idVehiculo;
		this.cantEstKmRecorrer = cantEstKmRecorrer;
		this.cantEstLitrosComb = cantEstLitrosComb;
		this.impEstTarjetaComb = impEstTarjetaComb;
		this.impEstExceso = impEstExceso;
		this.observaciones = observaciones;
		this.descAutorizador = descAutorizador;
		this.descChofer = descChofer;
		this.idMarca = idMarca;
		this.idModelo = idModelo;
		this.descDominio = descDominio;
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

	public int getUserAutorizacion() {
		return userAutorizacion;
	}

	public void setUserAutorizacion(int userAutorizacion) {
		this.userAutorizacion = userAutorizacion;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public int getIdChofer() {
		return idChofer;
	}

	public void setIdChofer(int idChofer) {
		this.idChofer = idChofer;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public float getCantEstKmRecorrer() {
		return cantEstKmRecorrer;
	}

	public void setCantEstKmRecorrer(float cantEstKmRecorrer) {
		this.cantEstKmRecorrer = cantEstKmRecorrer;
	}

	public float getCantEstLitrosComb() {
		return cantEstLitrosComb;
	}

	public void setCantEstLitrosComb(float cantEstLitrosComb) {
		this.cantEstLitrosComb = cantEstLitrosComb;
	}

	public float getImpEstTarjetaComb() {
		return impEstTarjetaComb;
	}

	public void setImpEstTarjetaComb(float impEstTarjetaComb) {
		this.impEstTarjetaComb = impEstTarjetaComb;
	}

	public float getImpEstExceso() {
		return impEstExceso;
	}

	public void setImpEstExceso(float impEstExceso) {
		this.impEstExceso = impEstExceso;
	}

	public String getObservaciones() {
			return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDescAutorizador() {
		return descAutorizador;
	}

	public void setDescAutorizador(String descAutorizador) {
		this.descAutorizador = descAutorizador;
	}

	public String getDescChofer() {
		return descChofer;
	}

	public void setDescChofer(String descChofer) {
		this.descChofer = descChofer;
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

	public String getDescDominio() {
		return descDominio;
	}

	public void setDescDominio(String descDominio) {
		this.descDominio = descDominio;
	}
	
	
	
	
	
	
}

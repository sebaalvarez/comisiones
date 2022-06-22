package sec.comisiones.mapeos;

import java.sql.Timestamp;
import java.util.Date;

public class Reservas {
	private int id;
	private Timestamp fechaHoraSalida;
	private Timestamp fechaHoraRegreso;
	private int userAutorizacion;
	private Date fechaAutorizacion;
	private int idVehiculo;
	private int idChofer;
	private String observaciones;
	private boolean Chofer;
	private boolean Vehiculo;
	
	private int descMarca;
	private int descModelo;
	private int descDominio;
	
	private Date fecSalida;
	private Date fecRegreso;



	public Reservas() {
	}
	
	
	public Reservas(int id, Timestamp fechaHoraSalida, Timestamp fechaHoraRegreso, int userAutorizacion,
			Timestamp fechaAutorizacion, int idVehiculo, int idChofer, String observaciones, boolean chofer,
			boolean vehiculo) {
		this.id = id;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraRegreso = fechaHoraRegreso;
		this.userAutorizacion = userAutorizacion;
		this.fechaAutorizacion = fechaAutorizacion;
		this.idVehiculo = idVehiculo;
		this.idChofer = idChofer;
		this.observaciones = observaciones;
		Chofer = chofer;
		Vehiculo = vehiculo;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getFechaHoraSalida() {
		return fechaHoraSalida;
	}
	public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}
	public Timestamp getFechaHoraRegreso() {
		return fechaHoraRegreso;
	}
	public void setFechaHoraRegreso(Timestamp fechaHoraRegreso) {
		this.fechaHoraRegreso = fechaHoraRegreso;
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
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public int getIdChofer() {
		return idChofer;
	}
	public void setIdChofer(int idChofer) {
		this.idChofer = idChofer;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isChofer() {
		return Chofer;
	}
	public void setChofer(boolean chofer) {
		Chofer = chofer;
	}
	public boolean isVehiculo() {
		return Vehiculo;
	}
	public void setVehiculo(boolean vehiculo) {
		Vehiculo = vehiculo;
	}
	
	
	
	
	public int getDescMarca() {
		return descMarca;
	}


	public void setDescMarca(int descMarca) {
		this.descMarca = descMarca;
	}


	public int getDescModelo() {
		return descModelo;
	}


	public void setDescModelo(int descModelo) {
		this.descModelo = descModelo;
	}


	public int getDescDominio() {
		return descDominio;
	}


	public void setDescDominio(int descDominio) {
		this.descDominio = descDominio;
	}


	public Date getFecSalida() {
		return fecSalida;
	}


	public void setFecSalida(Date fecSalida) {
		this.fecSalida = fecSalida;
	}


	public Date getFecRegreso() {
		return fecRegreso;
	}


	public void setFecRegreso(Date fecRegreso) {
		this.fecRegreso = fecRegreso;
	}
	
	
	
	
	

}

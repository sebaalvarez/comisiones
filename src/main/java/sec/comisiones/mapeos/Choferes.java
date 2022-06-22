package sec.comisiones.mapeos;

import java.util.Date;

public class Choferes {
	private int id;
	private int idEmpleado;
	private long numCarnet;
	private Date fechaVencimiento;
	private int idPrograma;
	private int idCategoria;
	
	private String descChofer;
	private String descPrograma;
	private String descCategoria;
	
	public Choferes() {
	}
	
	public Choferes(int id, int idEmpleado, String descChofer, long numCarnet, Date fechaVencimiento) {
		this.id = id;
		this.idEmpleado = idEmpleado;
		this.numCarnet = numCarnet;
		this.fechaVencimiento = fechaVencimiento;
		this.descChofer = descChofer;

	}


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public long getNumCarnet() {
		return numCarnet;
	}


	public void setNumCarnet(long numCarnet) {
		this.numCarnet = numCarnet;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	
	public String getDescChofer() {
		return descChofer;
	}

	public void setDescChofer(String descChofer) {
		this.descChofer = descChofer;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescPrograma() {
		return descPrograma;
	}

	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}
	
	
	
	
	
	
}

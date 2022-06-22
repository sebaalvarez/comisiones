package sec.comisiones.mapeos;

import java.util.Date;

public class DetalleGastosComisionados {
	private int id;
	private int idComision;
	private int idComisionado;
	private Date fecha;
	private String concepto;
	private float importe;
	
	private String fechaString;
	
	public DetalleGastosComisionados() {
	}
	
	public DetalleGastosComisionados(int id, int idComision, int idComisionado,
			Date fecha, String concepto, float importe) {
		super();
		this.id = id;
		this.idComision = idComision;
		this.idComisionado = idComisionado;
		this.fecha = fecha;
		this.concepto = concepto;
		this.importe = importe;
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

	public int getIdComisionado() {
		return idComisionado;
	}

	public void setIdComisionado(int idComisionado) {
		this.idComisionado = idComisionado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha =  date;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	
	
	
	
	
	
}

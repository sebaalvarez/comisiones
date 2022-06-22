package sec.comisiones.mapeos;

import java.sql.Timestamp;
import java.util.Date;

public class Rendiciones {
	private int id;
	private int idComision;
	private String nExpediente;
	private int idComisionado;
	private Date fecha;
	private Date fechaHoraSalida;
	private Date fechaHoraRegreso;
	private float duracion;
	private float anticipo;
	private float viaticos;
	private float gastos;
	private float gastosTotal;
	private float saldo;
	private String aFavorDe;
	private int idBanco;
	private String nCuenta;
	private String nDeposito;
	
	private String descComisionado;
	private String descBanco;

	
	public Rendiciones() {
	}
	
	public Rendiciones(int id, int idComision, String nExpediente,int idComisionado, Date fecha, Date fechaHoraSalida,
			Timestamp fechaHoraRegreso, float duracion, float anticipo,float viaticos, float gastos, float gastosTotal, float saldo,
			String aFavorDe, int idBanco, String nCuenta, String nDeposito,String descComisionado, String descBanco) {
		this.id = id;
		this.idComision = idComision;
		this.nExpediente = nExpediente;
		this.idComisionado = idComisionado;
		this.fecha = fecha;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraRegreso = fechaHoraRegreso;
		this.duracion = duracion;
		this.anticipo = anticipo;
		this.viaticos = viaticos;
		this.gastos = gastos;
		this.gastosTotal = gastosTotal;
		this.saldo = saldo;
		this.aFavorDe = aFavorDe;
		this.idBanco = idBanco;
		this.nCuenta = nCuenta;
		this.nDeposito = nDeposito;
		this.descComisionado = descComisionado;
		this.descBanco = descBanco;
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

	public String getnExpediente() {
		return nExpediente;
	}

	public void setnExpediente(String nExpediente) {
		this.nExpediente = nExpediente;
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

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public Date getFechaHoraSalida() {
		return fechaHoraSalida;
	}

//	public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
//		this.fechaHoraSalida = fechaHoraSalida;
//	}

	public void setFechaHoraSalida(Date fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}
	
	public Date getFechaHoraRegreso() {
		return fechaHoraRegreso;
	}

//	public void setFechaHoraRegreso(Timestamp fechaHoraRegreso) {
//		this.fechaHoraRegreso = fechaHoraRegreso;
//	}

	public void setFechaHoraRegreso(Date fechaHoraRegreso) {
		this.fechaHoraRegreso = fechaHoraRegreso;
	}
	
	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public float getAnticipo() {
		return anticipo;
	}

	public void setAnticipo(float anticipo) {
		this.anticipo = anticipo;
	}

	public float getViaticos() {
		return viaticos;
	}

	public void setViaticos(float viaticos) {
		this.viaticos = viaticos;
	}

	public float getGastos() {
		return gastos;
	}

	public void setGastos(float gastos) {
		this.gastos = gastos;
	}

	public float getGastosTotal() {
		return gastosTotal;
	}

	public void setGastosTotal(float gastosTotal) {
		this.gastosTotal = gastosTotal;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getaFavorDe() {
		return aFavorDe;
	}

	public void setaFavorDe(String aFavorDe) {
		this.aFavorDe = aFavorDe;
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public String getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(String nCuenta) {
		this.nCuenta = nCuenta;
	}

	public String getnDeposito() {
		return nDeposito;
	}

	public void setnDeposito(String nDeposito) {
		this.nDeposito = nDeposito;
	}

	public String getDescComisionado() {
		return descComisionado;
	}

	public void setDescComisionado(String descComisionado) {
		this.descComisionado = descComisionado;
	}

	public String getDescBanco() {
		return descBanco;
	}

	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}
	
	
	
	
	
}

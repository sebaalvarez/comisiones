package sec.comisiones.mapeos;

import java.sql.Timestamp;
import java.util.Date;


public class LogAuditoria {
	private int id;
	private int idRol;
	private int idUsuario;
	private Timestamp fecha;
	private String pc;
	private String accion;
	private String sql; 
	private String estatus;
	private String comentario;
	private String infoSistema;
	private String descripcion;
	private String descRol;
	private String descUsuario;
	
	
	public LogAuditoria() {
	}
	
	public LogAuditoria(int id, int idRol, int idUsuario, Timestamp fecha, String pc, String accion, 
			String sql, String estatus, String comentario, String descRol, String descUsuario) {
		this.id = id;
		this.idRol = idRol;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.pc = pc;
		this.accion = accion;
		this.sql = sql;
		this.estatus = estatus;
		this.comentario = comentario;
		this.descRol = descRol;
		this.descUsuario = descUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDescRol() {
		return descRol;
	}

	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

	public String getDescUsuario() {
		return descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}

	public String getInfoSistema() {
		return infoSistema;
	}

	public void setInfoSistema(String infoSistema) {
		this.infoSistema = infoSistema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}

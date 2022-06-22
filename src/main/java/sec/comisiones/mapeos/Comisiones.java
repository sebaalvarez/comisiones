package sec.comisiones.mapeos;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class Comisiones {
	private int id;
	private Date fecha;
	private int idEstado;
	private int idArea;
	private int idPrograma;
	private int idSolicitante;
	private Timestamp fechaSalida;
	private Timestamp fechaRegreso;
	private float duracion;
	private String motivo;
	private boolean reqVehiculo;
	private boolean autorizada;
	private int idAutorizador;
	private Date fechaAutorizacion;
	private String observaciones;
	private String nExpediente;
	
	private String descEstado;
	private String descArea;
	private String descPrograma;
	private String descSolicitante;
	private String descAutorizador;
	private String descUsuario;
	
	private Date fecSalida;
	private Date fecRegreso;
	private int idComisionado;
	private float viatico;
	
	private int flag;
	
	
	public Comisiones() {
	}
	
	
	public Comisiones(int id, Date fecha, int idEstado, int idArea,int idPrograma, int idSolicitante, Timestamp fechaSalida,
			Timestamp fechaRegreso, float duracion, String motivo, String observaciones, boolean reqVehiculo, boolean autorizada, int idAutorizador, Date fechaAutorizacion, 
			String descEstado,String descArea, String descPrograma, String descSolicitante,String descAutorizador,String descUsuario, String nExpediente) {

		this.id = id;
		this.fecha = fecha;
		this.idEstado = idEstado;
		this.idArea = idArea;
		this.idPrograma = idPrograma;
		this.idSolicitante = idSolicitante;
		this.fechaSalida = fechaSalida;
		this.fechaRegreso = fechaRegreso;
		this.duracion = duracion;
		this.motivo = motivo;
		this.observaciones=observaciones;
		this.reqVehiculo = reqVehiculo;
		this.autorizada=autorizada;
		this.idAutorizador = idAutorizador;
		this.fechaAutorizacion = fechaAutorizacion;
		this.descEstado = descEstado;
		this.descArea = descArea;
		this.descPrograma = descPrograma;
		this.descSolicitante = descSolicitante;
		this.descAutorizador = descAutorizador;
		this.descUsuario = descUsuario;
		this.nExpediente=nExpediente;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public int getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public Timestamp getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Timestamp getFechaRegreso() {
		return fechaRegreso;
	}

	public void setFechaRegreso(Timestamp fechaRegreso) {
		this.fechaRegreso = fechaRegreso;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isReqVehiculo() {
		return reqVehiculo;
	}


	public void setReqVehiculo(boolean reqVehiculo) {
		this.reqVehiculo = reqVehiculo;
	}
	
	public boolean isAutorizada() {
		return autorizada;
	}


	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}


	public int getIdAutorizador() {
		return idAutorizador;
	}

	public void setIdAutorizador(int userAutorizacion) {
		this.idAutorizador = userAutorizacion;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}


	public String getDescEstado() {
		return descEstado;
	}


	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}


	public String getDescArea() {
		return descArea;
	}


	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}


	public String getDescPrograma() {
		return descPrograma;
	}


	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}


	public String getDescSolicitante() {
		return descSolicitante;
	}


	public void setDescSolicitante(String descSolicitante) {
		this.descSolicitante = descSolicitante;
	}


	public String getDescAutorizador() {
		return descAutorizador;
	}


	public void setDescAutorizador(String descAutorizador) {
		this.descAutorizador = descAutorizador;
	}

	public String getDescUsuario() {
		return descUsuario;
	}

	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
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


	public int getIdComisionado() {
		return idComisionado;
	}


	public void setIdComisionado(int idComisionado) {
		this.idComisionado = idComisionado;
	}


	public float getViatico() {
		return viatico;
	}


	public void setViatico(float viatico) {
		this.viatico = viatico;
	}
	
	
	
	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
	
	
	

	public String getnExpediente() {
		return nExpediente;
	}


	public void setnExpediente(String nExpediente) {
		this.nExpediente = nExpediente;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public float calculoDuracionComision(Date fechaSalida, Date fechaRegreso){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		float duracion=0;
		
		try {
			int hs = 24-fechaSalida.getHours();
			int hr = fechaRegreso.getHours();
			
			Date fechaInicial=dateFormat.parse(dateFormat.format(fechaSalida));
			Date fechaFinal = dateFormat.parse(dateFormat.format(fechaRegreso));
			
			// 84600000 milisegundos son 24hs
			int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
		
			float durSalida=calculoDuracion(hs);
			float durRegreso=calculoDuracion(hr);

			
			if (dias<1){
				int h=fechaRegreso.getHours()-fechaSalida.getHours();
				duracion = calculoDuracion(h);
			} else if(dias==1){
				duracion = durSalida+durRegreso;
			} else if (dias>1){
				duracion = durSalida+durRegreso+(dias-1.0f);
			}
			
//			Notification.show("","hs salida: "+ hs +" // Dur Saldida: "+ durSalida 
//					+" // hs regreso: "+ hr + " // Dur Regreso: "+ durRegreso 
//					+" // dias: "+ dias +" // diaSalida: "+ fechaInicial +" // diaRegreso: "+ fechaFinal
//					+" //**// Formato: "+ dateFormat.format(fechaSalida) 
//					+" // duraci�n: " + duracion, Type.ERROR_MESSAGE );
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Notification.show("ERROR ","No se pudo calcular la duraci�n debido a un error en el formato de las fechas",Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
		

		return duracion;
	}
	
	
	private float calculoDuracion(int hora){
		float h=0;
		
		if (hora>=16){
			h=1.0f;
		} else if(hora>=8 && hora<=15){
			h=0.666f;
		} else if(hora<8){
			h=0.333f;
		}
		
		
		return h;
	}
	
	
	
	
	
	

}

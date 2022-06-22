package sec.comisiones.mapeos;

public class DetalleComisionados {
	private int id;
	private int idComision;
	private int idPersonalPorPrograma;
	private int idEmpleado;
	private int idArea;
	private int idPrograma;
	private int idCategoria;
	private int idEspecialidad;
	private int idRendicion;
	private int idTipoComisionado;
	private float montoCategoria;
	private float tiempoDuracion;
	private float viaticos;
	
	private String descPersonal;
	private String descPrograma;
	private String descArea;
	private String descCategoria;
	private String descEspecialidad;

	

		    		  
	public DetalleComisionados() {
	}
	
	
	public DetalleComisionados(int id, int idComision, int idEmpleado, int idArea, int idPrograma, int idTipoComisionado,
			String descPersonal, String descPrograma, String descArea) {

		this.id = id;
		this.idComision = idComision;
		this.idEmpleado=idEmpleado;
		this.idArea=idArea;
		this.idPrograma=idPrograma;
		this.idTipoComisionado=idTipoComisionado;
		this.descPersonal = descPersonal;
		this.descPrograma = descPrograma;
		this.descArea = descArea;
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


	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getDescPersonal() {
		return descPersonal;
	}


	public void setDescPersonal(String descPersonal) {
		this.descPersonal = descPersonal;
	}


	public String getDescPrograma() {
		return descPrograma;
	}


	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}


	public String getDescArea() {
		return descArea;
	}


	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}

	public int getIdPersonalPorPrograma() {
		return idPersonalPorPrograma;
	}

	public void setIdPersonalPorPrograma(int idPersonalPorPrograma) {
		this.idPersonalPorPrograma = idPersonalPorPrograma;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public int getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(int idRendicion) {
		this.idRendicion = idRendicion;
	}

	public float getMontoCategoria() {
		return montoCategoria;
	}

	public void setMontoCategoria(float montoCategoria) {
		this.montoCategoria = montoCategoria;
	}

	public float getTiempoDuracion() {
		return tiempoDuracion;
	}

	public void setTiempoDuracion(float tiempoDuracion) {
		this.tiempoDuracion = tiempoDuracion;
	}

	public float getViaticos() {
		return viaticos;
	}

	public void setViaticos(float viaticos) {
		this.viaticos = viaticos;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	public String getDescEspecialidad() {
		return descEspecialidad;
	}

	public void setDescEspecialidad(String descEspecialidad) {
		this.descEspecialidad = descEspecialidad;
	}
	
	
	
	
	public int getIdTipoComisionado() {
		return idTipoComisionado;
	}


	public void setIdTipoComisionado(int idTipoComisionado) {
		this.idTipoComisionado = idTipoComisionado;
	}


	public float calculoViaticos(float monto, float duracion){
		float calc=0.0f;
		calc=monto*duracion;
		return calc;
	}
	
	
	
	
}

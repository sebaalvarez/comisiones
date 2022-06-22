package sec.comisiones.mapeos;

public class EmpleadosPorPrograma {

	private int id;
	private int idEmpleado;
	private int idPrograma;
	private int idCategoria;
	private int idEspecialidad;
	
	private String descEmpleado;
	private String descPrograma;
	private String descCategoria;
	private String descEspecialidad;
	private String descArea;
	
	public EmpleadosPorPrograma(){
	}
	
	public EmpleadosPorPrograma(Programas p){
		this.idPrograma = p.getId();
		this.descPrograma = p.getNombre();
		this.descArea = p.getDescArea();
	}
	
	
	public EmpleadosPorPrograma(int id, int idEmpleado, int idPrograma,int idCategoria, int idEspecialidad, String descEmpleado,
			String descPrograma, String descCategoria, String descEspecialidad,String descArea) {
		this.id = id;
		this.idEmpleado = idEmpleado;
		this.idPrograma = idPrograma;
		this.idCategoria = idCategoria;
		this.idEspecialidad = idEspecialidad;
		this.descEmpleado = descEmpleado;
		this.descPrograma = descPrograma;
		this.descCategoria = descCategoria;
		this.descEspecialidad = descEspecialidad;
		this.descArea = descArea;
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


	public void setIdEmpleado(int idEpleado) {
		this.idEmpleado = idEpleado;
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


	public int getIdEspecialidad() {
		return idEspecialidad;
	}


	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}


	public String getDescEmpleado() {
		return descEmpleado;
	}


	public void setDescEmpleado(String descEmpleado) {
		this.descEmpleado = descEmpleado;
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


	public String getDescEspecialidad() {
		return descEspecialidad;
	}


	public void setDescEspecialidad(String descEspecialidad) {
		this.descEspecialidad = descEspecialidad;
	}


	public String getDescArea() {
		return descArea;
	}


	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}
	
	
	
	
	
	
	
}

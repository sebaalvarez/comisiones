package sec.comisiones.mapeos;


import java.io.Serializable;


@SuppressWarnings("serial")
public class MenuGeneral implements Serializable {
	private int id;
	private int pk;
	private String nPadre;
	private String nHijo;
	private String padre;
	private String hijo;
	private int activo;
	private int orden;
	
	public MenuGeneral(int id, String padre, String hijo, int orden) {
		this.id = id;
		this.padre = padre;
		this.hijo = hijo;
		this.orden = orden;
	}

	
	public MenuGeneral(int id, int pk,  String nPadre, String nHijo, String padre,
			String hijo, int activo) {
		this.id = id;
		this.pk = pk;
		this.nPadre = nPadre;
		this.nHijo = nHijo;
		this.padre = padre;
		this.hijo = hijo;
		this.activo = activo;
	}


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPk() {
		return pk;
	}


	public void setPk(int pk) {
		this.pk = pk;
	}
	
	public String getnPadre() {
		return nPadre;
	}


	public void setnPadre(String nPadre) {
		this.nPadre = nPadre;
	}


	public String getnHijo() {
		return nHijo;
	}


	public void setnHijo(String nHijo) {
		this.nHijo = nHijo;
	}


	public String getPadre() {
		return padre;
	}


	public void setPadre(String padre) {
		this.padre = padre;
	}


	public String getHijo() {
		return hijo;
	}


	public void setHijo(String hijo) {
		this.hijo = hijo;
	}


	public int getActivo() {
		return activo;
	}


	public void setActivo(int activo) {
		this.activo = activo;
	}

	
	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}
	

	@Override
	public String toString() {
		return "MenuGeneral [id=" + id + ", nPadre=" + nPadre + ", nHijo="
				+ nHijo + ", padre=" + padre + ", hijo=" + hijo + ", activo="
				+ activo + "]";
	}

	
	
	
	
	
	

}

package sec.comisiones.manager;

import sec.comisiones.dao.EstadosDAO;
import sec.comisiones.mapeos.Estados;


import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

public class EstadosManager {
	private EstadosDAO c = new EstadosDAO();
	private BeanContainer<Integer, Estados> sc = null;
	

	public Table cargaTabla(){
		
		sc=c.getAllEstadosContainer();
		
	   Table table = new Table(null,sc);//  new Table("This is my Table");
	   
	   //Establecemos el tama�o de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selecci�n de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selecci�n de m�ltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selecci�n immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		table.setVisibleColumns(new Object[] {"id", "nombre", "descripcion"});
		table.setColumnHeaders(new String[]  {"Id", "Código", "Descripción"});

	    return table;
	}
}

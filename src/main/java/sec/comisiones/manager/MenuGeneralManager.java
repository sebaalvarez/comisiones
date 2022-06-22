package sec.comisiones.manager;

import sec.comisiones.dao.MenuGeneralDAO;
import sec.comisiones.mapeos.MenuGeneral;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

public class MenuGeneralManager {

	private MenuGeneralDAO c = new MenuGeneralDAO();
	private BeanContainer<String, MenuGeneral> sc = null;


	public Table cargaTablaPorRol(int rol){
		
		sc=c.getAllMenu(rol);
		
	   Table table = new Table(null,sc);//  new Table("This is my Table");
	   
	   //Establecemos el tamaño de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selección de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selección de múltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selección immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		table.setVisibleColumns(new Object[] {"nPadre", "nHijo","activo"});
		table.setColumnHeaders(new String[]  {"Padre", "Hijo", "Activo"});

		table.setColumnAlignment( "activo", Align.CENTER);
		
	    return table;
	}


	
	public Table cargaTablaMenuGeneral(){
		
		sc=c.getAllMenuGeneral();
		
	   Table table = new Table(null,sc);//  new Table("This is my Table");
	   
	   //Establecemos el tamaño de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.
	
	    //Opciones en la selección de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selección de múltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selección immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		table.setVisibleColumns(new Object[] {"id", "padre", "hijo", "orden"});
		table.setColumnHeaders(new String[]  {"Id", "Padre", "Hijo", "Orden"});
	
		table.setColumnAlignment( "orden", Align.CENTER);
		
	    return table;
	}
}


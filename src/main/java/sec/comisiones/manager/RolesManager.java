package sec.comisiones.manager;

import sec.comisiones.dao.RolesDAO;
import sec.comisiones.dao.RolesPorUsuariosDAO;
import sec.comisiones.mapeos.Roles;
import sec.comisiones.mapeos.RolesPorUsuarios;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

public class RolesManager {

	private RolesDAO c = new RolesDAO();
	private BeanContainer<Integer, Roles> sc = null;
	
	private RolesPorUsuariosDAO c1 = new RolesPorUsuariosDAO();
	private BeanContainer<Integer, RolesPorUsuarios> sc1 = null;

	public Table cargaTabla(){
		
		sc=c.getAllRolesContainer();
		
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
	
	public Table cargaTablaRolesUsuario(String nomUsr){
		
		sc1=c1.getRolesDeUsuarioContainer(nomUsr);
		
	   Table table = new Table(null,sc1);//  new Table("This is my Table");
	   
	   //Establecemos el tama�o de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selecci�n de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selecci�n de m�ltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selecci�n immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    table.setPageLength(6);
	    
		table.setVisibleColumns(new Object[] {"nombreRol"});
		table.setColumnHeaders(new String[]  {"Rol"});

	    return table;
	}
}

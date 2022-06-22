package sec.comisiones.manager;

import sec.comisiones.dao.EmpleadosDAO;
import sec.comisiones.dao.EmpleadosPorProgramaDAO;
import sec.comisiones.mapeos.Empleados;



import sec.comisiones.mapeos.EmpleadosPorPrograma;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Table;

public class EmpleadosManager {
	private EmpleadosDAO c = new EmpleadosDAO();
	private BeanContainer<Integer, Empleados> sc = null;
	private EmpleadosPorProgramaDAO c1 = new EmpleadosPorProgramaDAO();
	private BeanContainer<Integer, EmpleadosPorPrograma> sc1 = null;
	
	public Table cargaTabla(){
		
		sc=c.getAllEmpleadosContainer();
		
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
	    
		table.setVisibleColumns(new Object[] {"id", "dni", "nombre", "apellido"});
		table.setColumnHeaders(new String[]  {"Id", "DNI", "Nombre", "Apellido"});

	    return table;
	}
	
	
	public Table cargaTabla(String nombre){
		
		sc=c.getAllEmpleadosContainer(nombre);
		
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
	    
		table.setVisibleColumns(new Object[] {"id", "dni", "nombre", "apellido"});
		table.setColumnHeaders(new String[]  {"Id", "DNI", "Nombre", "Apellido"});

	    return table;
	}
	
	
	public Table cargaTablaEmpleadosPorPrograma(int idPrograma){
		
		sc1=c1.getAllEmpleadosPorProgramaContainer(idPrograma);
		
	   Table table = new Table(null,sc1);//  new Table("This is my Table");
	   
	   //Establecemos el tamaño de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight(6, Unit.CM); //Altura del Grid.

	    //Opciones en la selección de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selección de múltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selección immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		table.setVisibleColumns(new Object[] {"descEmpleado"});
		table.setColumnHeaders(new String[]  {"Nombre"});

	    return table;
	}

}

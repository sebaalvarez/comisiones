package sec.comisiones.manager;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sec.comisiones.dao.ChoferesDAO;
import sec.comisiones.mapeos.Choferes;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;


public class ChoferesManager {
	private ChoferesDAO c = new ChoferesDAO();
	private BeanContainer<Integer, Choferes> sc = null;
	
	
	public Table cargaTabla(){
		
		sc=c.getAllChoferesContainer();
		
		Table table = new Table(null,sc){//  new Table("This is my Table");
			   
			   @Override
	           protected String formatPropertyValue(Object rowId,Object colId, Property property) {          
	               if (property.getType() == Date.class) {
	                   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	                   return df.format((Date)property.getValue());
	               }else if(property.getType() == Timestamp.class) {
	            	   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	                   return df.format((Timestamp)property.getValue());  
		           } else if (property.getType() == Float.class) {
		               DecimalFormat df = new DecimalFormat("#0.00");
		               return df.format((Float) property.getValue());   
		           } 
	               return super.formatPropertyValue(rowId, colId, property);
		       }
		    };
	   
	   //Establecemos el tama�o de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selecci�n de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selecci�n de m�ltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selecci�n immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		table.setVisibleColumns(new Object[] {"id", "numCarnet", "fechaVencimiento", "descChofer","descPrograma","descCategoria"});
		table.setColumnHeaders(new String[]  {"Id", "Nº Registro", "Vencimiento", "Descripción","Programa","Categoría"});

		table.setColumnAlignment("numCarnet", Align.CENTER);
		table.setColumnAlignment("fechaVencimiento", Align.CENTER);
		table.setColumnAlignment("descChofer", Align.LEFT);
		
	    return table;
	}
	
	

	
	

}

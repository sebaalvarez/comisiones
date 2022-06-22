package sec.comisiones.manager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sec.comisiones.dao.DetalleComisionadosDAO;
import sec.comisiones.mapeos.DetalleComisionados;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

public class DetalleComisionadosManager {
	private DetalleComisionadosDAO c = new DetalleComisionadosDAO();
	private BeanContainer<Integer, DetalleComisionados> sc = null;

	public Table cargaTabla(int idComision){
		
		sc=c.getAllDetalleComisionadosContainer(idComision);
		
		Table table = new Table(null,sc){//  new Table("This is my Table");
		   
		   @Override
           protected String formatPropertyValue(Object rowId,Object colId, Property property) {          
               if (property.getType() == Date.class) {
                   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                   return df.format((Date)property.getValue());
	           } else if (property.getType() == Float.class) {
	               DecimalFormat df = new DecimalFormat("#0.00");
	               return df.format((Float) property.getValue());   
	           } 
               return super.formatPropertyValue(rowId, colId, property);
	       }
	    };
	   
	   
	   
	   //Establecemos el tamaño de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selección de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selección de múltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selección immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	    
		//int id, Date fecha, int idEstado, int idArea,int idPrograma, int idSolicitante, Date fechaSalida,
		//Date fechaRegreso, float duracion, String motivo,boolean autorizada, int idAutorizador, Date fechaAutorizacion, 
		//String descEstado,String descArea, String descPrograma, String descSolicitante,String descAutorizador
	    
	    table.setVisibleColumns(new Object[] {"descPersonal"});
		table.setColumnHeaders(new String[]  {"Comisionado"});

	//	table.setColumnAlignment("fecha", Align.CENTER);
	//	table.setColumnAlignment("descEstado", Align.CENTER);
	//	table.setColumnAlignment("fechaSalida", Align.CENTER);
	//	table.setColumnAlignment("fechaRegreso", Align.CENTER);
		
	    return table;
	}
	
}

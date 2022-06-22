package sec.comisiones.manager;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.components.calendar.event.BasicEvent;

import sec.comisiones.dao.EventosDAO;
import sec.comisiones.mapeos.Usuarios;



public class ReservasManager {
	private EventosDAO c = new EventosDAO();
	private BeanItemContainer<BasicEvent> sc = null;


	
	public Table cargaTabla(String tipo, String numResolucion, String fec1, String fec2, boolean vencimiento, Usuarios usr){
		
		sc=c.getAllEventosContainer(tipo,"","",fec1,fec2,vencimiento);

		/*
		if (tipo=="Comision"){
			sc=c.getAllComisionesContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="Chofer"){
			sc=c.getAllComisionesSolicitanteContainer(tipo,numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="Vehiculo"){
			sc=c.getAllComisionesPendienteVehiculoContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento);
		} 
		*/
		Table table = new Table(null,sc){//  new Table("This is my Table");
		   
		   @Override
           protected String formatPropertyValue(Object rowId,Object colId, Property property) {          
               if (property.getType() == Date.class) {
                   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
	    
		//int id, Date fecha, int idEstado, int idArea,int idPrograma, int idSolicitante, Date fechaSalida,
		//Date fechaRegreso, float duracion, String motivo,boolean autorizada, int idAutorizador, Date fechaAutorizacion, 
		//String descEstado,String descArea, String descPrograma, String descSolicitante,String descAutorizador
	    
	    table.setVisibleColumns(new Object[] {"caption", "start", "end"});
		table.setColumnHeaders(new String[]  {"Descripción", "Fecha Salida", "Fecha Regreso"});

//		table.setColumnAlignment("fecha", Align.CENTER);
//		table.setColumnAlignment("descEstado", Align.CENTER);
//		table.setColumnAlignment("fechaSalida", Align.CENTER);
//		table.setColumnAlignment("fechaRegreso", Align.CENTER);
		
	    return table;
	}
	
	
}

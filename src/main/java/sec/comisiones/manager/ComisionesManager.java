package sec.comisiones.manager;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sec.comisiones.dao.ComisionesDAO;
import sec.comisiones.mapeos.Comisiones;
import sec.comisiones.mapeos.Usuarios;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;



public class ComisionesManager {
	private ComisionesDAO c = new ComisionesDAO();
	private BeanContainer<Integer, Comisiones> sc = null;


	
	public Table cargaTabla(String tipo, String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2, boolean vencimiento, Usuarios usr){
		
		if (tipo=="TODAS" || tipo==""){
			sc=c.getAllComisionesContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="CARGA" || tipo=="AUTORIZADAS" || tipo=="TODASGENERADAS"){
			sc=c.getAllComisionesSolicitanteContainer(tipo,numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="PENDVEHICULO"){
			sc=c.getAllComisionesPendienteVehiculoContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento);
		} else if (tipo=="PENDAUTORIZACION"){
			sc=c.getAllComisionesPendienteAutorizacionContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento);
		} else if (tipo=="PENDREND"){
			sc=c.getAllComisionesPendienteRendicionContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="PENDEXPEDIENTE"){
			sc=c.getAllComisionesPendienteExpedienteContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		} else if (tipo=="PENDIMPRESION"){
			sc=c.getAllComisionesPendienteImpresionContainer(numResolucion,fec,area,programa,estado,fec1,fec2,vencimiento,usr);
		}
		
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
	    
		//int id, Date fecha, int idEstado, int idArea,int idPrograma, int idSolicitante, Date fechaSalida,
		//Date fechaRegreso, float duracion, String motivo,boolean autorizada, int idAutorizador, Date fechaAutorizacion, 
		//String descEstado,String descArea, String descPrograma, String descSolicitante,String descAutorizador
	    
	    table.setVisibleColumns(new Object[] {"id", "fecha", "descArea", "descPrograma", "descEstado", "fechaSalida", "fechaRegreso","descSolicitante","descUsuario"});
		table.setColumnHeaders(new String[]  {"Nº", "Fecha", "Area", "Programa", "Estado", "Fecha Salida", "Fecha Regreso","Solicitante","Generó"});

		table.setColumnAlignment("fecha", Align.CENTER);
		table.setColumnAlignment("descEstado", Align.CENTER);
		table.setColumnAlignment("fechaSalida", Align.CENTER);
		table.setColumnAlignment("fechaRegreso", Align.CENTER);
		
	    return table;
	}
	
	
}

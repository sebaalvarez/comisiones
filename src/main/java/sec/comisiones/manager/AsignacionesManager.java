package sec.comisiones.manager;


import com.agpro.controles.UtilUI;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;

import sec.comisiones.dao.AsignacionesDAO;
import sec.comisiones.mapeos.Asignaciones;
import sec.comisiones.mapeos.Usuarios;




public class AsignacionesManager {
	private AsignacionesDAO c = new AsignacionesDAO();
	private BeanContainer<Integer, Asignaciones> sc = null;
	private Asignaciones as = new Asignaciones();

	public Table cargaTabla(String fec1, Usuarios usr){	
		sc=c.getAllAsignacionesContainer(fec1,usr);
	    
	    as=c.DevuelveAsignacion(usr);
	    
	    Table table =new UtilUI().buildTabla(sc,"dd/MM/yyyy HH:mm","");
	    
	    table.setVisibleColumns(new Object[] {"dato", "d1", "d2", "d3", "d4", "d5", "d6", "d7"});
		table.setColumnHeaders(new String[]  {"Vehículo", as.getD1().getValue(), as.getD2().getValue(), as.getD3().getValue(), as.getD4().getValue(), 
				as.getD5().getValue(), as.getD6().getValue(), as.getD7().getValue()});
		
		table.setColumnAlignment("dato", Align.CENTER);
		table.setColumnAlignment("d1", Align.CENTER);
		table.setColumnAlignment("d2", Align.CENTER);
		table.setColumnAlignment("d3", Align.CENTER);
		table.setColumnAlignment("d4", Align.CENTER);
		table.setColumnAlignment("d5", Align.CENTER);
		table.setColumnAlignment("d6", Align.CENTER);
		table.setColumnAlignment("d7", Align.CENTER);
				
	    return table;
	}
	
	
}

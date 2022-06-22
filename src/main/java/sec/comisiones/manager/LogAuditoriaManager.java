package sec.comisiones.manager;


import sec.comisiones.dao.LogAuditoriaDAO;
import sec.comisiones.mapeos.LogAuditoria;

import com.agpro.controles.UtilUI;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

public class LogAuditoriaManager {

	private LogAuditoriaDAO c = new LogAuditoriaDAO();
	private BeanContainer<Integer, LogAuditoria> sc = null;

	
	public Table cargaTabla(String par1, String par2, String par3, String par4, String fec1, String fec2){
		
		sc=c.getAllLogAuditoriaContainer(par1,par2,par3,par4,fec1,fec2);
		
		Table table =UtilUI.buildTabla(sc,"dd/MM/yyyy HH:mm:ss","");
	    

	    table.setVisibleColumns(new Object[] {"id","fecha","descRol","descUsuario","pc","accion","estatus","comentario",
	    		"sql","infoSistema","descripcion"});
		table.setColumnHeaders(new String[]  {"Id", "Fecha", "Rol", "Usuario", "PC", "Acción", "Status", "Coment",
				"SQL","Info Sistema","Descripción"});

//		table.setColumnAlignment("catastro", Align.CENTER);
//		table.setColumnAlignment("catastroOrigen", Align.CENTER);
//		table.setColumnAlignment("plano", Align.CENTER);
//		table.setColumnAlignment("mensura", Align.CENTER);
//		table.setColumnAlignment("superficie", Align.RIGHT);
//		table.setColumnAlignment("numeroCerfiticado", Align.CENTER);
		table.setSortAscending(false);
		table.setSortContainerPropertyId("id");
		
		
	    return table;
	}
	
}

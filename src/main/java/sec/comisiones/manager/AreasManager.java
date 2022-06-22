package sec.comisiones.manager;

import sec.comisiones.dao.AreasDAO;
import sec.comisiones.mapeos.Areas;

import com.agpro.controles.UtilUI;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

public class AreasManager 	{
	private AreasDAO c = new AreasDAO();
	private BeanContainer<Integer, Areas> sc = null;


	public Table cargaTabla(){
	
		sc=c.getAllAreasContainer();
		
		Table table =new UtilUI().buildTabla(sc,"","");
		
		table.setVisibleColumns(new Object[] {"id", "nombre", "descripcion"});
		table.setColumnHeaders(new String[]  {"Id", "Código", "Descripción"});
	
	    return table;
	}
}



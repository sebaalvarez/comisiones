package sec.comisiones.manager;

import sec.comisiones.dao.CategoriasDAO;
import sec.comisiones.mapeos.Categorias;

import com.agpro.controles.UtilUI;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;

public class CategoriasManager {
	private CategoriasDAO c = new CategoriasDAO();
	private BeanContainer<Integer, Categorias> sc = null;
	

	public Table cargaTabla(){	
		sc=c.getAllCategoriasContainer();
		
		Table table =new UtilUI().buildTabla(sc,"","");
		
		table.setVisibleColumns(new Object[] {"id", "nombre", "descripcion","montoProvincial","montoNacional","montoInternacional"});
		table.setColumnHeaders(new String[]  {"Id", "Código", "Descripción","Monto Provincial $","Monto Nacional $","Monto Internacional $"});

	    return table;
	}
}
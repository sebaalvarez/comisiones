package sec.comisiones.vistas;

import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.dao.MenuGeneralDAO;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Tree;

public class MenuCreaMenu {

	private Tree tree_1= new Tree();

	
	public Tree getMenuArbol(String rol){
		this.crearMenuArbol(rol);
		tree_1.setCaption("Menú General");
		tree_1.setImmediate(true);
	//	tree_1.setWidth("200px");
		tree_1.setWidth(100,Unit.PERCENTAGE);
		tree_1.setHeight("300px");
		return tree_1;
	}
	
	
	private void crearMenuArbol(String r){
		String rol=r;
		MenuGeneralDAO menuPadre = new MenuGeneralDAO();
		MenuGeneralDAO menuHijo = new MenuGeneralDAO();
		
		ResultSet rsPadre = menuPadre.getMenuRolPadre(rol);
		
		 try {
			rsPadre.last();
		    int numRowsPadre = rsPadre.getRow(); 
		    rsPadre.beforeFirst();
		    
			 while(rsPadre.next()) {
				    String padre = rsPadre.getString("padre");
				    tree_1.addItem(padre);

				    ResultSet rsHijo = menuHijo.getMenuRolHijo(rol, padre);

				    if (numRowsPadre == 1) {
				        tree_1.setChildrenAllowed(padre, false);
				    	} else {
					        while(rsHijo.next()) {
					        	 String hijo = rsHijo.getString("hijo");
						            tree_1.addItem(hijo);
						            tree_1.setParent(hijo, padre); // establece la relaci�n padre hijo. 
						            tree_1.setChildrenAllowed(hijo, false); // Marca al hijo como �ltimo nivel.
						    }
				    	}
				}
			 tree_1.addItem("Salir");
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}


	
	
//	MenuBar barmenu = new MenuBar();
//	barmenu.addStyleName("mybarmenu");
//	layout.addComponent(barmenu);
//	        
//	// A feedback component
//	final Label selection = new Label("-");
//	layout.addComponent(selection);
//
//	// Define a common menu command for all the menu items
//	MenuBar.Command mycommand = new MenuBar.Command() {
//	    MenuItem previous = null;
//
//	    public void menuSelected(MenuItem selectedItem) {
//	        selection.setValue("Ordered a " +
//	                selectedItem.getText() +
//	                " from menu.");
//
//	        if (previous != null)
//	            previous.setStyleName(null);
//	        selectedItem.setStyleName("highlight");
//	        previous = selectedItem;
//	    }  
//	};
//	        
//	// Put some items in the menu
//	barmenu.addItem("Beverages", null, mycommand);
//	barmenu.addItem("Snacks", null, mycommand);
//	barmenu.addItem("Services", null, mycommand);
	
	
}
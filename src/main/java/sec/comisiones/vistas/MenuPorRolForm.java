package sec.comisiones.vistas;

import java.util.function.Consumer;

import sec.comisiones.dao.MenuGeneralDAO;
import sec.comisiones.dao.RolesDAO;
import sec.comisiones.manager.MenuGeneralManager;
import sec.comisiones.mapeos.Usuarios;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class MenuPorRolForm extends CustomComponent {


	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	final MenuGeneralManager tblMenu = new MenuGeneralManager();
	private Table tb;
	Usuarios usrLog = new Usuarios();
	VentanaMenu ventMenu=null;
	
	String rol="";
	
	public MenuPorRolForm() {
		ini(rol);  
	}
	
	public MenuPorRolForm(Usuarios usr) {
		usrLog= usr;
		ini(rol);   
	}

///////////////////////////////////////////////	
///////////////////////////////////////////////	
	private void ini(String rolNom){
		buildMainLayout();
		setCompositionRoot(mainLayout);	

	    ComboBox cmbRol = new ComboBox("Rol");

	    rolNom="1";
    	RolesDAO c = new RolesDAO();
    	cmbRol.setContainerDataSource(c.getAllRolesContainer());
    	cmbRol.setItemCaptionPropertyId("nombre");
		cmbRol.setValue(rolNom);
		cmbRol.setTextInputAllowed(false);
		

		
		int idRol;
		if (cmbRol.getValue()!=null){
			idRol = (int) cmbRol.getValue();
		} else{
			idRol=0;
		}
		
		
		
    	tb = tblMenu.cargaTablaPorRol(idRol);
		HorizontalLayout hl_1= new HorizontalLayout(cmbRol);
		verticalLayout_1.removeAllComponents();
		verticalLayout_1.addComponent(hl_1);
		verticalLayout_1.addComponent(tb);
		verticalLayout_1.setExpandRatio(tb, 1.0f);
		verticalLayout_1.setSpacing(true);

		hl_1.setSpacing(true);
		

		

		
		
		cmbRol.addValueChangeListener(new ValueChangeListener() {
			 public void valueChange(ValueChangeEvent event) {
				 
					verticalLayout_1.removeComponent(tb);
					int rol1;
					if (cmbRol.getValue()==null) { 
						rol1 = 0; 
					} else {
						rol1 = (int) cmbRol.getValue();
					}
					
					tb = tblMenu.cargaTablaPorRol(rol1);
						
					
					verticalLayout_1.addComponent(tb);
					verticalLayout_1.setExpandRatio(tb, 1.0f);
					
					
					tb.addValueChangeListener(new ValueChangeListener() {
					    public void valueChange(ValueChangeEvent event) {
					    	Object selectedItemId = event.getProperty().getValue();
						    if (selectedItemId != null && !cmbRol.isEmpty()) {
					//	    	Notification.show("item selected: "+  tb.getValue(), Notification.Type.HUMANIZED_MESSAGE ); 

	 							int idMenu = (int) tb.getItem(tb.getValue()).getItemProperty("id").getValue();
	 							int idMenuRol = (int) tb.getItem(tb.getValue()).getItemProperty("pk").getValue();
						    	String padre = (String) tb.getItem(tb.getValue()).getItemProperty("padre").getValue();
						    	String hijo = (String) tb.getItem(tb.getValue()).getItemProperty("hijo").getValue();
						    	
					        	UI.getCurrent().addWindow(ventMenu=new VentanaMenu(idMenuRol,idMenu,padre,hijo,rol1, (newValue) -> {
						                }));

						    	}
						    }
					});
				 
			 }	
			 
		});
		
		
		

		tb.addValueChangeListener(new ValueChangeListener() {
		    public void valueChange(ValueChangeEvent event) {
		    	Object selectedItemId = event.getProperty().getValue();
			    if (selectedItemId != null && !cmbRol.isEmpty()) {
			    	int idMenu = (int) tb.getItem(tb.getValue()).getItemProperty("id").getValue();
					int idMenuRol = (int) tb.getItem(tb.getValue()).getItemProperty("pk").getValue();
			    	String padre = (String) tb.getItem(tb.getValue()).getItemProperty("padre").getValue();
			    	String hijo = (String) tb.getItem(tb.getValue()).getItemProperty("hijo").getValue();
			    	
		        	UI.getCurrent().addWindow(ventMenu=new VentanaMenu(idMenuRol,idMenu,padre,hijo,idRol, (newValue) -> {
			                }));
	
			    	}
			    }
		});


	}
	
	
	
	
	class VentanaMenu extends Window {

		private VerticalLayout content;
		private VerticalLayout verticalLayout_1;

		
	    public VentanaMenu(int idMenuRol0, int idMenu0, String padre0, String hijo0, int rol0, Consumer<String> save) {
	        super("Modifica "); // Set window caption
	        center();
	        
	        setModal(true);
	        //	setClosable(false);
	        setResizable(false);
	        this.setWidth(40, Unit.PERCENTAGE);
	        this.setHeight(20, Unit.PERCENTAGE);

	        content = new VerticalLayout();
	        verticalLayout_1= new VerticalLayout();
	        verticalLayout_1.setSpacing(true);
	        content.setMargin(true);
	        content.setSpacing(true);
	        MenuGeneralDAO u = new MenuGeneralDAO();
	        
    	    TextField id0 = new TextField("IdMenuRol");
    	    TextField id1 = new TextField("IdMenu");
	    	TextField padre = new TextField("Padre");
	    	TextField hijo = new TextField("Hijo");
	    	TextField rol = new TextField("Rol");
	    	
    	    NativeButton btnModifica = new NativeButton("Graba");
    		NativeButton btnCancela = new NativeButton("Cancela");
    		
			id0.setEnabled(false); 
			id1.setEnabled(false); 
			padre.setEnabled(false);  
			hijo.setEnabled(false);
			rol.setEnabled(false);
			
			id0.setColumns(4);
			id1.setColumns(4);
			padre.setColumns(10);
			hijo.setColumns(17);
			rol.setColumns(5);
			
			id0.setValue(String.valueOf(idMenuRol0));
			id1.setValue(String.valueOf(idMenu0));
			padre.setValue(String.valueOf(padre0));
			hijo.setValue(String.valueOf(hijo0));
			rol.setValue(String.valueOf(rol0));
			
			if (id0.getValue().equals("0")) {
				btnModifica.setCaption("Agregar Men??");
			} else {
				btnModifica.setCaption("Quitar Men??");
			}
				
	    	HorizontalLayout hl_2= new HorizontalLayout(id0,id1,padre,hijo,rol);
	    	hl_2.setSpacing(true); 
	    	
	    	HorizontalLayout hl_1= new HorizontalLayout(btnModifica,btnCancela);
	    	verticalLayout_1.addComponents(hl_2,hl_1);
	    	
	    	
	    	
	    	
			btnModifica.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
					if (id0.getValue().equals("0")) {
						
						u.agregaARol(Integer.parseInt(id1.getValue()),Integer.parseInt(rol.getValue()), usrLog.getId());

					}else{
						u.eliminaARol(Integer.parseInt(id0.getValue()), usrLog.getId()); 
					}
					close();
					ini(String.valueOf(rol0));	
		}
	});
	    	
	    	
			btnCancela.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) { 		
					close();
					ini(String.valueOf(rol0));
		}
	});
	
			
			
			content.addComponents(verticalLayout_1);
			setContent(content);
			
			

	    }
	}
	
///////////////////////////////////////////////	
///////////////////////////////////////////////

	
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setSizeFull();
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		//  verticalSplitPanel_1
	    contenedorPanel = buildContenedorPanel();
		mainLayout.addComponent(contenedorPanel);
 
		return mainLayout;
	}

	private Panel buildContenedorPanel() {
		// common part: create layout
		contenedorPanel = new Panel();
		contenedorPanel.setImmediate(false);
		contenedorPanel.setWidth("100.0%");
		contenedorPanel.setHeight("100.0%");
		contenedorPanel.setSizeFull();
		
		// verticalLayout_1
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(true);
		contenedorPanel.setContent(verticalLayout_1);
		
		return contenedorPanel;
	}


}


package sec.comisiones.vistas;

import java.util.function.Consumer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import sec.comisiones.mapeos.Usuarios;




public class VehiculosAsignadosMenuForm  extends CustomComponent {

	private static final long serialVersionUID = -5494654607167262871L;
	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	
	Usuarios usrLog = new Usuarios();
	String flag="";
	int bandera=0;
	
	public VehiculosAsignadosMenuForm() {
		ini(bandera);  
	}
	
	public VehiculosAsignadosMenuForm(Usuarios usr) {
		usrLog= usr;
		ini(bandera);   
	}

	public VehiculosAsignadosMenuForm(Usuarios usr, String flag) {
		usrLog= usr;
		this.flag=flag;
		ini(bandera);   
	}
	
	
	
///////////////////////////////////////////////	
///////////////////////////////////////////////	
	private void ini(int bandera){
		buildMainLayout();
		setCompositionRoot(mainLayout);	
		NativeButton btnCierre = new NativeButton("cierre");


		UI.getCurrent().addWindow(new MySub("0",btnCierre,(newValue) -> { }));

	
	
	}
	

	@SuppressWarnings("serial")
	class MySub extends Window {
	    public MySub(String planificacion,NativeButton btnCierre, Consumer<String> save) {
	
	    	super("Detalle Reservas");
	    	center();
	        setModal(true);
	//        setClosable(false);
	        setResizable(true);
	        this.setWidth(120, Unit.EM);
//	        this.setHeight(95, Unit.PERCENTAGE);
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        content.setSpacing(true);
	        
	        btnCierre.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
					close();
					ini(1);
				}
			});
	        
	        VehiculosAsignadosForm vusuarios6=new VehiculosAsignadosForm(usrLog,"","todos");
	        content.addComponents(vusuarios6);

	        setContent(content);
	    }
	}

	
	
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
		verticalLayout_1.setHeight("98.0%");
		verticalLayout_1.setMargin(true);
		contenedorPanel.setContent(verticalLayout_1);
		
		return contenedorPanel;
	}


	
}
	
	


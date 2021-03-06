package sec.comisiones.vistas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

import com.agpro.controles.UtilUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import sec.comisiones.manager.AsignacionesManager;
import sec.comisiones.mapeos.Usuarios;




public class VehiculosAsignadosForm extends CustomComponent {

	private static final long serialVersionUID = -8604976880270284869L;
	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	AsignacionesManager tblPlanificaciones = new AsignacionesManager();
	private Table tb;

	
	private TextField txtNumCom;
	private DateField fecha1;
    
	private NativeButton btnConsulta;
	private NativeButton btnCalendario;
	private NativeButton btnCalendario1;
	private NativeButton btnCalendario2;
	    
	Usuarios usrLog = new Usuarios();
	String flag="";
	int bandera=0;
    String tipo="";
    String numResolucion="";
	String area="";
	String programa="";
	String estado="";
	String fec="";
	String fec1="";
	String fec2="";
	boolean vencimiento=false;

	NativeButton btnCierre = new NativeButton("Salir");
	
	
	public VehiculosAsignadosForm() {
		ini(bandera);  
	}
	
	public VehiculosAsignadosForm(Usuarios usr) {
		usrLog= usr;
		ini(bandera);   
	}

	public VehiculosAsignadosForm(Usuarios usr, String flag, String flag1 ) {
		usrLog= usr;
		this.flag=flag;
		this.tipo=flag1;
		ini(bandera);   
	}
	    
	    


///////////////////////////////////////////////	
///////////////////////////////////////////////	
	private void ini(int bandera){
		buildMainLayout();
		setCompositionRoot(mainLayout);	

		
		txtNumCom= new TextField("N?? Comisi??n");
	    fecha1 = new DateField();


	    
		btnConsulta = new NativeButton("Filtrar");
	    btnCalendario = new NativeButton("Agenda General");
	    btnCalendario1 = new NativeButton("Agenda Veh??culos");
	    btnCalendario2 = new NativeButton("Agenda Choferes");
		
		
    		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    	
		
		fecha1.setValue(new Date());
		fecha1.setDateFormat("dd/MM/yyyy");
		fecha1.setResolution(Resolution.DAY);
		fecha1.setLenient(true);
		

		
		fecha1.setWidth(9f, Unit.EM);
		txtNumCom.setWidth(9f, Unit.EM);

		

		
		
		tb = tblPlanificaciones.cargaTabla(dateFormat.format(fecha1.getValue()),usrLog);
		tb.setHeight(UtilUI.devuelveAltoCuerpo()-5,Unit.PIXELS);
		tb.setSelectable(false);
		
		
		HorizontalLayout hl_1= new HorizontalLayout(btnCalendario,btnCalendario1,btnCalendario2,fecha1,btnConsulta);	
		verticalLayout_1.removeAllComponents();
		verticalLayout_1.addComponents(hl_1);
		verticalLayout_1.addComponent(tb);
		verticalLayout_1.setExpandRatio(tb, 1.0f);
		verticalLayout_1.setStyleName("panelNoCaption");
		verticalLayout_1.setSpacing(true);
		hl_1.setComponentAlignment(btnConsulta, Alignment.BOTTOM_RIGHT);
		hl_1.setComponentAlignment(btnCalendario, Alignment.BOTTOM_RIGHT);
		hl_1.setComponentAlignment(btnCalendario1, Alignment.BOTTOM_RIGHT);
		hl_1.setComponentAlignment(btnCalendario2, Alignment.BOTTOM_RIGHT);
		hl_1.setSpacing(true);
	
		
		teclado();
		
		
	    btnConsulta.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				if (fecha1.isEmpty()) {
					Notification.show("Debe ingresar una fecha para consultar");
					
				} else {
					fec1 = dateFormat.format(fecha1.getValue());
					verticalLayout_1.removeComponent(tb);
					tb = tblPlanificaciones.cargaTabla(fec1,usrLog);
					tb.setHeight(UtilUI.devuelveAltoCuerpo()-5,Unit.PIXELS);
					tb.setSelectable(false);
					
					verticalLayout_1.addComponent(tb);
					verticalLayout_1.setExpandRatio(tb, 1.0f);
					
				}


				selectTabla();
				
			}
		});	
		
	
	    
		
	    btnCalendario.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new ventanaCalendario(tipo,btnCierre,(newValue) -> { }));
				
			}});
	    
	    btnCalendario1.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new ventanaCalendario("vehiculos",btnCierre,(newValue) -> { }));
				
			}});
	    
	    btnCalendario2.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(new ventanaCalendario("choferes",btnCierre,(newValue) -> { }));
				
			}});

	    selectTabla();
		
	
	}

	
	public void selectTabla(){		
		tb.addValueChangeListener(new ValueChangeListener() {
		    public void valueChange(ValueChangeEvent event) {
		    	Object selectedItemId = event.getProperty().getValue();
		    	if (selectedItemId != null) {
		    		String id0=String.valueOf(tb.getItem(selectedItemId).getItemProperty("caption").getValue()).substring(6, 12);
		    		String id1=String.valueOf(tb.getItem(selectedItemId).getItemProperty("caption").getValue()).substring(0, 4);
		    		UI.getCurrent().addWindow(new MySub(id0,id1,btnCierre,(newValue) -> { }));
		    		
		    	}
		   	}
		});
		
		
		
	} 


	private void teclado() {
		this.addShortcutListener(new ShortcutListener("ENTER",
				KeyCode.ENTER, new int[] {}) {
	
			private static final long serialVersionUID = 1L;
	
			@Override
			public void handleAction(Object sender, Object target) {
				if (target.equals(fecha1)) {
					btnConsulta.click();
				}
	
			}
		});	
	}
	
//////////////////////////////////////////////////
//////////////////////////////////////////////////
	@SuppressWarnings("serial")
	class MySub extends Window {
	    public MySub(String idComision, String comRes, NativeButton btnCierre, Consumer<String> save) {
	
	    	super("");
	    	center();
	        setModal(true);
	//        setClosable(false);
	        setResizable(true);
	        this.setWidth(90, Unit.PERCENTAGE);
	        this.setHeight(98, Unit.PERCENTAGE);
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        content.setSpacing(true);
	        
	        btnCierre.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
					close();
					ini(1);
				}
			});

	        if(comRes.equals("Com.")){
	        	this.setCaption("Detalle Comisi??n");
	        	ComisionesForm vusuarios6=new ComisionesForm(usrLog,idComision,flag,btnCierre);
	        	content.addComponents(vusuarios6);

	        }else if(comRes.equals("Res.")) {
	        	this.setCaption("Reserva Libre");
	        	this.setWidth(60, Unit.PERCENTAGE);
		        this.setHeight(70, Unit.PERCENTAGE);
	        	ReservasForm vusuarios6=new ReservasForm(usrLog,idComision,flag,btnCierre);
	        	content.addComponents(vusuarios6);
	        	
	        }
	        

	        setContent(content);
	    }
	}

	
//////////////////////////////////////////////////
//////////////////////////////////////////////////
@SuppressWarnings("serial")
	class ventanaCalendario extends Window {
	    public ventanaCalendario(String tipoEvento, NativeButton btnCierre, Consumer<String> save) {
	
	    	super("Calendario Comisiones");
	    	center();
	        setModal(true);
	//        setClosable(false);
	        setResizable(true);
	        this.setWidth(90, Unit.PERCENTAGE);
	        this.setHeight(98, Unit.PERCENTAGE);
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        content.setSpacing(true);
	        
//	        btnCierre.addClickListener(new ClickListener(){
//				public void buttonClick(ClickEvent event) {
//					close();
//					ini(1);
//				}
//			});
	        

	        
	        CalendarForm vusuarios6 = new CalendarForm(tipoEvento);
	        content.addComponents(vusuarios6);

	        

	        setContent(content);
	    }
	}

	
//////////////////////////////////////////////////
//////////////////////////////////////////////////
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

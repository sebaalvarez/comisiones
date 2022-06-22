package sec.comisiones.vistas;

import java.sql.SQLException;
import java.util.function.Consumer;

import sec.comisiones.dao.EmpleadosDAO;
import sec.comisiones.manager.EmpleadosManager;
import sec.comisiones.mapeos.Empleados;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;



public class ListadoEmpleadosForm extends Window {

	private static final long serialVersionUID = 3482059022186398071L;
	private Table tbCert;
	private String filNombre="";

	private EmpleadosManager tbCertMng = new EmpleadosManager();
	private Empleados per = new Empleados();
	private EmpleadosDAO perDAO = new EmpleadosDAO();
	
	private TextField txtNombre =new TextField("Nombre o apellido"); 
	private Button btnFiltro = new Button("Filtrar");
	
	
	
	public ListadoEmpleadosForm(Consumer<Empleados> Objeto) {
		super("Listado de Empleados"); 
		center();
		setModal(true);
		//  setClosable(false);
		setResizable(true);
		this.setWidth(75, Unit.EM);
		this.setHeight(50, Unit.EM); 
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);
		
		
		
		tbCert = tbCertMng.cargaTabla("");
		
		HorizontalLayout hl_0= new HorizontalLayout();
		hl_0.addComponents(txtNombre,btnFiltro);
		hl_0.setSpacing(true);
		hl_0.setComponentAlignment(btnFiltro, Alignment.BOTTOM_CENTER);		
		
		//verticalLayout_1.removeAllComponents();
		//verticalLayout_1.addComponents(valueEditor,saveButton);
		content.addComponents(hl_0,tbCert);
		content.setExpandRatio(tbCert, 1.0f);
		
		
		devuelveSeleccion(Objeto);
		teclado();
		
		btnFiltro.addClickListener(new ClickListener(){

			private static final long serialVersionUID = -5646699324823385518L;

			public void buttonClick(ClickEvent event) {
			
				if (txtNombre.isEmpty()) {
					filNombre = "";
				} else { 
					filNombre = txtNombre.getValue(); 
				}
				
				content.removeComponent(tbCert);
				tbCert = tbCertMng.cargaTabla(filNombre);
				content.addComponent(tbCert);
				content.setExpandRatio(tbCert, 1.0f);
				
				devuelveSeleccion(Objeto);
				
			}
		});
		
		
		
	}
	
	
	private void devuelveSeleccion(Consumer<Empleados> Objeto){
		tbCert.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = -6315651750619659083L;

			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				Object selectedItemId = event.getProperty().getValue();
				if (selectedItemId != null) {
					try {
						per= perDAO.DevuelveEmpleado((int) tbCert.getItem(selectedItemId).getItemProperty("dni").getValue());
					} catch (NumberFormatException|SQLException e) {
						e.printStackTrace();
					} 
					
					Objeto.accept(per);
					//save.accept((String) tb.getItem(selectedItemId)..getItemProperty("nombre").getValue());
					close();
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
				if (target.equals(txtNombre)) {
					btnFiltro.click();
				}
	
			}
		});	
	}

	
}

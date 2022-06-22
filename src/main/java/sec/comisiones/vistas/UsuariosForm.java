package sec.comisiones.vistas;

import java.sql.SQLException;
import java.util.function.Consumer;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import sec.comisiones.dao.EmpleadosDAO;
import sec.comisiones.dao.UsuariosDAO;
import sec.comisiones.manager.UsuariosManager;
import sec.comisiones.mapeos.Usuarios;



@SuppressWarnings("serial")
public class UsuariosForm extends CustomComponent {

	
	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	final UsuariosManager tblUser = new UsuariosManager();
	private Table tb;
	NativeButton btnNew=new NativeButton();
	Usuarios usrLog = new Usuarios();
	EmpleadosDAO empleadosDao = new EmpleadosDAO();
	
	public UsuariosForm() {
		ini();
	}
	
	public UsuariosForm(Usuarios usr) {
		usrLog= usr;
		ini();
	}
	
///////////////////////////////////////////////	
///////////////////////////////////////////////
	private void ini(){
		buildMainLayout();
		setCompositionRoot(mainLayout);	
		

		btnNew.setCaptionAsHtml(true);
		btnNew.setCaption(FontAwesome.PLUS.getHtml() + " Nuevo" );
		
		
		tb = tblUser.cargaTabla();
		verticalLayout_1.removeAllComponents();
		verticalLayout_1.addComponent(btnNew);
		verticalLayout_1.addComponent(tb);
		verticalLayout_1.setExpandRatio(tb, 1.0f);

		
		final MyForm myform = new MyForm(usrLog);
		myform.setVisible(true);
		verticalLayout_1.addComponent(myform);
		

		tb.setSelectable(true);
		btnNew.setEnabled(true);
		
		
		// Handle selection change.
		tb.addValueChangeListener(new ValueChangeListener() {
		    public void valueChange(ValueChangeEvent event) {
		    	Object selectedItemId = event.getProperty().getValue();
			    if (selectedItemId != null) {
			//    	Notification.show("item selected: "+  tb.getValue(), Notification.Type.HUMANIZED_MESSAGE ); 
		            myform.setItemDataSource(tb.getItem(selectedItemId));
		            if (! myform.isVisible()) {
		                tb.setCurrentPageFirstItemId(selectedItemId);
		                myform.setVisible(true);}
			    } else {
		   //    	Notification.show("Nothing selected ", Notification.Type.HUMANIZED_MESSAGE );
			    	ini();
			//    	myform.setVisible(false);
			    }}
		});
		
		
		btnNew.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				
				UsuariosDAO ud=new UsuariosDAO();
				Item item = ud.getItem();
							
	//		    Notification.show("item selected: "+ item.getItemPropertyIds(), Notification.Type.HUMANIZED_MESSAGE ); 
			    	
	            myform.setItemDataSource(item);
    // 			if (! myform.isVisible()) {
		                myform.setVisible(true);
	 //          }
				}
		});

	}
	
	
	
///////////////////////////////////////////////	
///////////////////////////////////////////////
	// Show item details here
	class MyForm extends FormLayout {
		
		TextField id = new TextField("Id");
	    TextField nombre = new TextField("Nombre");
	    PasswordField password = new PasswordField("Password");
	    CheckBox eliminado = new CheckBox("Bloquedo");
	    NativeButton btnRoles = new NativeButton("Roles Asignados");
//	    TextField idEmp = new TextField("Id Empleado");
	    ComboBox idEmp = new ComboBox("Empleado");

	    HorizontalLayout hl_0= new HorizontalLayout(id,nombre,password,eliminado,idEmp);
	  //  HorizontalLayout hl_01= new HorizontalLayout(password,eliminado);
	  //  HorizontalLayout hl_02= new HorizontalLayout(idEmp);
	    HorizontalLayout hl_03= new HorizontalLayout(btnRoles);
	    
		NativeButton btnGraba = new NativeButton("Actualiza");
	    NativeButton btnElimina = new NativeButton("Elimina");
	    NativeButton btnCancela = new NativeButton("Cancela");
	    HorizontalLayout hl_1= new HorizontalLayout(btnGraba,btnElimina,btnCancela);
	    Usuarios usrLog = new Usuarios();
	    
	    public MyForm(){

	    }
	    
	    public MyForm(Usuarios us){
	    	usrLog=us;
	    }
	    
	    public void setItemDataSource(Item item) {
	    	idEmp.setContainerDataSource(empleadosDao.getAllEmpleadosContainer());
	        addComponents(hl_0,hl_03,hl_1);
	  //      FieldGroup binder = new FieldGroup(item);
	  //      binder.bindMemberFields(this);
	        
	        hl_0.setComponentAlignment(eliminado, Alignment.MIDDLE_CENTER);
	        
	        tb.setSelectable(false);
	    	btnNew.setEnabled(false);
	    	
	        // Bind the form
	        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(item);
	        binder.setBuffered(true);
	        binder.bindMemberFields(this);

	        // Have an error display
	        final ErrorLabel formError = new ErrorLabel();
	        formError.setWidth(null);
	  //      addComponent(formError);
	        
	        binder.setErrorDisplay(formError);
	        
	        hl_0.setSpacing(true);
	//        hl_01.setSpacing(true);
	//        hl_02.setSpacing(true);
	        hl_03.setSpacing(true);
	        
	        //nombre.addStyleName("horizontal");
	        btnGraba.setImmediate(true);
	        btnGraba.addStyleName("Small");

	    	btnElimina.addStyleName("Small");


	    	eliminado.addStyleName("Small");
	    	
	    	id.setEnabled(false);
	    	id.setReadOnly(true);
	    	
	    	nombre.addStyleName("Small");	    	
	    	nombre.setRequired(true);
	    	nombre.setRequiredError("Campo Obligatorio");
	    	nombre.addValidator(new StringLengthValidator("Longitud Máxima 25 caracteres", 0, 25, true));
	  //  	nombre.addValidator(new IntegerRangeValidator("The value must be integer between 0-120 (was {0})", 0, 120));
	    	nombre.setImmediate(true);
	    	nombre.setValidationVisible(true);
	    	nombre.setEnabled(false);
	    	nombre.setReadOnly(true);	
	    	
	    	password.addStyleName("Small");
	    	password.setRequired(true);
	    	password.setRequiredError("Campo Obligatorio");
	    	password.addValidator(new StringLengthValidator("Longitud Máxima 20 caracteres", 0, 20, true));
	    	
	    	idEmp.addStyleName("Small");
	    	idEmp.setRequired(true);
	    	idEmp.setRequiredError("Campo Obligatorio");
	    	idEmp.setItemCaptionPropertyId("descEmpleado");
	    	idEmp.setImmediate(true);
	    	idEmp.setInvalidAllowed(true);
	    	idEmp.setNullSelectionAllowed(true);
	    	idEmp.setTextInputAllowed(true);
	    	idEmp.setFilteringMode(FilteringMode.CONTAINS);
	    	
	    	id.setWidth(6, Unit.EM);
	    	nombre.setWidth(10, Unit.EM);
	    	password.setWidth(8, Unit.EM);
	    	idEmp.setWidth(30, Unit.EM);

	    	
	    	if ((Integer) item.getItemProperty("id").getValue() == 0){
				btnGraba.setCaption("Graba Nuevo");
				btnElimina.setEnabled(false);
		    	nombre.setEnabled(true);
		    	nombre.setReadOnly(false);
		    	idEmp.setEnabled(true);
		    	idEmp.setReadOnly(false);
		    	nombre.setValue("");
		    	password.setValue("");
		    	idEmp.setValue(null);
		    	eliminado.setValue(false);
				tb.setSelectable(false);
	    	}else {
	    		int valor=0;
	    		if (!idEmp.getValue().equals(null)){
	    			valor=(int)idEmp.getValue();
	    		}
	    		idEmp.setContainerDataSource(empleadosDao.getAllEmpleadosContainer());
	    		idEmp.setValue(valor);

	    	}

	    	
	    	
	        btnGraba.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
					try{
							binder.commit();
							UsuariosDAO u = new UsuariosDAO();
							
							if(btnGraba.getCaption()=="Graba Nuevo"){
								try {
									 if (u.existe(nombre.getValue())==0){
										u.agregaUsuario(nombre.getValue(), password.getValue(), (int)idEmp.getValue(), eliminado.getValue(), usrLog.getId()); 
										btnGraba.setCaption("Actualiza");
										ini();
										 
										}else{
											Notification.show("El Código "+ nombre.getValue() +" ya existe registrado", Type.ERROR_MESSAGE);
											nombre.setValue("");
											password.setValue("");
										}	
									} catch (SQLException e) {
										e.printStackTrace();
									}						
								}
							else if(btnGraba.getCaption()=="Actualiza"){
								u.modificaUsuario(nombre.getValue(), password.getValue(), (int)idEmp.getValue(), eliminado.getValue(), usrLog.getId()); 
								ini();
							}

						} catch (CommitException e) {
							e.getMessage();
						}
				}
			});
	          
	        
	        btnElimina.addClickListener(new ClickListener(){
	        	public void buttonClick(ClickEvent event) {
	        		UsuariosDAO u = new UsuariosDAO();
						ConfirmDialog.show(getUI(), "Confirmar", "¿Desea eliminar el registro seleccionado?","Sí", "No", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
						          if (dialog.isConfirmed()) {
						               // C�digo S�
						        	  u.eliminaUsuario(nombre.getValue(), usrLog.getId()); 	
										ini();
						          } else {
						               // C�digo No     
						          }
						     }
						});

				}
	        });
	        	

	        btnCancela.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
					ini();
					}
			});
	   
	    
	        btnRoles.addClickListener((click) -> { 
	        	if(id.getValue().equals(null) || id.getValue().equals("0")){
					Notification.show("Debe grabar primero el usuario y luego asignarle sus roles.",Type.ERROR_MESSAGE);
				}else{
					UI.getCurrent().addWindow(new MySubRol(nombre.getValue(), (newValue) -> {}));   
				}		
	        });
	        
	        
	        
	    }
	};
	
	
///////////////////////////////////////////////	
///////////////////////////////////////////////
	// Show Roles de Usuario
	class MySubRol extends Window {
	    public MySubRol(String empleado, Consumer<String> save) {
	        super("Roles"); // Set window caption
	        center();
	//        String tipoEmpleado = "emp";
	        setModal(true);
	//        setClosable(false);
	        setResizable(false);
	        this.setWidth(50, Unit.PERCENTAGE);
//	        this.setHeight(70, Unit.PERCENTAGE);
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        content.setSpacing(true);
        
	        TextField valueEditor = new TextField("Value to edit", String.valueOf(empleado));
	        
	        // Trivial logic for saving the edited data
	        @SuppressWarnings("unused")
			Button saveButton = new Button("Save", (click) -> {
	            // Close the sub-window
	            close();
	            save.accept(valueEditor.getValue());
	        });
	        
	        
			RolesPorUsuarioForm vusuarios7 = new RolesPorUsuarioForm(usrLog, empleado);
//			verticalLayout_Form.addComponent(vusuarios6);
			content.addComponents(vusuarios7);
	        
//	        content.addComponents(valueEditor, saveButton);

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
		verticalLayout_1.setHeight("98.0%");
		verticalLayout_1.setMargin(true);
		contenedorPanel.setContent(verticalLayout_1);
		
		return contenedorPanel;
	}
	

}
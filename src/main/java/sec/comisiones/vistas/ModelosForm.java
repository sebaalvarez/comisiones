package sec.comisiones.vistas;

import java.sql.SQLException;

import org.vaadin.dialogs.ConfirmDialog;

import sec.comisiones.dao.MarcasDAO;
import sec.comisiones.dao.ModelosDAO;
import sec.comisiones.manager.ModelosManager;
import sec.comisiones.mapeos.Usuarios;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class ModelosForm extends CustomComponent {

	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	final ModelosManager tblUser = new ModelosManager();
	private Table tb;
	NativeButton btnNew=new NativeButton();
	Usuarios usrLog = new Usuarios();
	private MarcasDAO marcasDao = new MarcasDAO();
	
	public ModelosForm() {
		ini();
	}
	
	public ModelosForm(Usuarios usr) {
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

		tb.setSelectable(true);
		btnNew.setEnabled(true);
		
		final MyForm myform = new MyForm(usrLog);
		myform.setVisible(true);
		verticalLayout_1.addComponent(myform);
		
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
					//    	myform.setVisible(false);
					    	ini();
					    }
			    }
		});
		
		
		btnNew.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				
				ModelosDAO ud=new ModelosDAO();
				Item item = ud.getItem();
							
	//		    Notification.show("item selected: "+ item.getItemPropertyIds(), Notification.Type.HUMANIZED_MESSAGE ); 
			    	
	            myform.setItemDataSource(item);
     //			if (! myform.isVisible()) {
		                myform.setVisible(true);
	//           }
				}
		});
		
		
	}
	

///////////////////////////////////////////////	
///////////////////////////////////////////////	
	// Show item details here
	class MyForm extends FormLayout {

	    TextField nombre = new TextField("Código");
	    TextField descripcion = new TextField("Descripción");
	    ComboBox idMarca = new ComboBox("Marca");

	    
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
	    	idMarca.setContainerDataSource(marcasDao.getAllMarcasContainer());
	    	
	        addComponents(nombre, descripcion, idMarca, hl_1);
	//        FieldGroup binder = new FieldGroup(item);
	//        binder.bindMemberFields(this);
	        
	        // Bind the form
	        final ErrorfulFieldGroup binder = new ErrorfulFieldGroup(item);
	        binder.setBuffered(true);
	        binder.bindMemberFields(this);

	        // Have an error display
	        final ErrorLabel formError = new ErrorLabel();
	        formError.setWidth(null);
	      //addComponent(formError);
	        
	        binder.setErrorDisplay(formError);
            
	        tb.setSelectable(false);
	        btnNew.setEnabled(false);
	        
	        btnGraba.addStyleName("Small");	        
	        btnGraba.setImmediate(true);
	    //	btnModifica.setDescription("Guarda Modificaciones");

	    	
	    	btnElimina.addStyleName("Small");
	    	

	    	nombre.addStyleName("Small");
	    	nombre.setRequired(true);
	    	nombre.setRequiredError("Campo Obligatorio");
	    	nombre.addValidator(new StringLengthValidator("Longitud Máxima 15 caracteres", 0, 15, true));
	    	nombre.setImmediate(true);
	    	nombre.setValidationVisible(true);
	    	nombre.setEnabled(false);
	    	nombre.setReadOnly(true);	
	    	
	    	descripcion.addStyleName("Small");
	    	descripcion.setRequired(true);
	    	descripcion.setRequiredError("Campo Obligatorio");
	    	descripcion.addValidator(new StringLengthValidator("Longitud Máxima 20 caracteres", 0, 200, true));     
	    	descripcion.setImmediate(true);
	    	descripcion.setValidationVisible(true);
	    	descripcion.setWidth("100.0%");

	    	
	    	
	    	idMarca.addStyleName("Small");
	    	idMarca.setRequired(true);
	    	idMarca.setRequiredError("Campo Obligatorio");
	    	idMarca.setItemCaptionPropertyId("nombre");
	    	idMarca.setImmediate(true);
	    	idMarca.setInvalidAllowed(true);
	    	idMarca.setNullSelectionAllowed(true);
	    	idMarca.setTextInputAllowed(false);
	    	
	    	
	    	if ((Integer) item.getItemProperty("id").getValue() == 0){
	    		btnGraba.setCaption("Graba Nuevo");
				btnElimina.setEnabled(false);
		    	nombre.setEnabled(true);
		    	nombre.setReadOnly(false);
		    	nombre.setValue("");
		    	descripcion.setValue("");
		    	idMarca.setValue("");
				tb.setSelectable(false);
	    	}
	    	
	    	
		//////////////////////////////////
		///////// BOTONES ////////////////
		//////////////////////////////////	   
	    	
	    	btnGraba.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
						try {
							binder.commit();
							
							ModelosDAO u = new ModelosDAO();
							
							if(btnGraba.getCaption()=="Graba Nuevo"){
								try {
									 if ((int)idMarca.getValue()>0){
										 if (u.existe(nombre.getValue())==0){
											u.agrega(nombre.getValue(), descripcion.getValue(), (int)idMarca.getValue(), usrLog.getId()); 
											btnGraba.setCaption("Actualiza");
											tb.setSelectable(true);
											ini();
											}else{
												Notification.show("El Código "+ nombre.getValue() +" ya existe registrado", Type.ERROR_MESSAGE);
												nombre.setValue("");
											}
										 }else{
											 Notification.show("Debe seleccionar una Marca. ", Type.ERROR_MESSAGE);
										 }
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else if (btnGraba.getCaption()=="Actualiza"){
									u.modifica(nombre.getValue(), descripcion.getValue(), (int)idMarca.getValue(), usrLog.getId()); 		
									ini();
								}
						} catch (CommitException e) {
							e.printStackTrace();
						}


				}
			});
	        
        
	        btnElimina.addClickListener(new ClickListener(){
	        	public void buttonClick(ClickEvent event) {
	        		ModelosDAO u = new ModelosDAO();
					if (u.existenRegistrosRelacionados(nombre.getValue())==0) {
						ConfirmDialog.show(getUI(), "Confirmar", "¿Desea eliminar el registro seleccionado?","Sí", "No", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
						          if (dialog.isConfirmed()) {
						               // C�digo S�
						        	  u.elimina(nombre.getValue(), usrLog.getId()); 		
										ini();
						          } else {
						               // C�digo No     
						          }
						     }
						});

					} else {
						Notification.show("No se puede eliminar el registro seleccionado"," Existen registros asociados.",Type.ERROR_MESSAGE);
						ini();
					}
				}
	        });

	        	
	        	
	        	
	        	
	        	
					
											

	        
						
						
						
	        
	        btnCancela.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
						ini();
					}
			});

	    }
	};

	
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
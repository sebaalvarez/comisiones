package sec.comisiones.vistas;

import java.sql.SQLException;

import org.vaadin.dialogs.ConfirmDialog;

import sec.comisiones.dao.MarcasDAO;
import sec.comisiones.dao.ModelosDAO;
import sec.comisiones.dao.TiposVehiculosDAO;
import sec.comisiones.dao.VehiculosDAO;
import sec.comisiones.manager.VehiculosManager;
import sec.comisiones.mapeos.Usuarios;

import com.agpro.controles.Formateos;
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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class VehiculosForm extends CustomComponent {

	private VerticalLayout mainLayout;
	private Panel contenedorPanel;
	private VerticalLayout verticalLayout_1;
	final VehiculosManager tblUser = new VehiculosManager();
	private Table tb;
	NativeButton btnNew=new NativeButton();
	Usuarios usrLog = new Usuarios();
	private MarcasDAO marcasDao = new MarcasDAO();
	private ModelosDAO modelosDao = new ModelosDAO();
	private TiposVehiculosDAO tiposVehiculosDao = new TiposVehiculosDAO();
	
	public VehiculosForm() {
		ini();
	}
	
	public VehiculosForm(Usuarios usr) {
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
					//    	myform.setVisible(false);
					    	ini();
					    }
			    }
		});
		
		
		btnNew.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				
				VehiculosDAO ud=new VehiculosDAO();
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

	    TextField dominio = new TextField("Patente");
	    TextField anio = new TextField("Año");
	    ComboBox idMarca = new ComboBox("Marca");
	    ComboBox idModelo = new ComboBox("Modelo");
	    ComboBox idTipo = new ComboBox("Tipo Vehículo");
	    CheckBox activo = new CheckBox("Activo");
	    
		NativeButton btnGraba = new NativeButton("Actualiza");
	    NativeButton btnElimina = new NativeButton("Elimina");
	    NativeButton btnCancela = new NativeButton("Cancela");
	    HorizontalLayout hl_1= new HorizontalLayout(btnGraba,btnElimina,btnCancela);
	    HorizontalLayout hl_0= new HorizontalLayout();
	    
	    Usuarios usrLog = new Usuarios();
	    
	    public MyForm(){

	    }
	    
	    public MyForm(Usuarios us){
	    	usrLog=us;
	    }
	    
	    public void setItemDataSource(Item item) {
	    	idMarca.setContainerDataSource(marcasDao.getAllMarcasContainer());
		    idModelo.setContainerDataSource(modelosDao.getAllModelosContainer());
	    	idTipo.setContainerDataSource(tiposVehiculosDao.getAllTiposVehiculosContainer());
	    	hl_0.addComponents(dominio, anio, idMarca, idModelo, idTipo, activo);
	    	hl_0.setSpacing(true);
	        addComponents(hl_0, hl_1);
	        
	        hl_0.setComponentAlignment(activo, Alignment.MIDDLE_LEFT);
	    	
	        tb.setSelectable(false);
			btnNew.setEnabled(false);
			
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
            
	        
	        btnGraba.addStyleName("Small");	        
	        btnGraba.setImmediate(true);

	    	
	    	btnElimina.addStyleName("Small");
	    	

	    	dominio.addStyleName("Small");
	    	dominio.setRequired(true);
	    	dominio.setRequiredError("Campo Obligatorio");
	    	dominio.addValidator(new StringLengthValidator("Longitud Máxima 7 caracteres", 0, 7, true));
	    	dominio.setImmediate(true);
	    	dominio.setValidationVisible(true);
	    	dominio.setEnabled(false);
	    	dominio.setReadOnly(true);	
	    	
	    	anio.addStyleName("Small");
	    	anio.setRequired(true);
	    	anio.setRequiredError("Campo Obligatorio");
	    	//anio.addValidator(new StringLengthValidator("Longitud M�xima 4 caracteres", 0, 5, true));     
	    	anio.setImmediate(true);
	    	anio.setValidationVisible(true);
	    	anio.setWidth("10.0%");

	    	
	    	
	    	idMarca.addStyleName("Small");
	    	idMarca.setRequired(true);
	    	idMarca.setRequiredError("Campo Obligatorio");
	    	idMarca.setItemCaptionPropertyId("nombre");
	    	idMarca.setImmediate(true);
	    	idMarca.setInvalidAllowed(true);
	    	idMarca.setNullSelectionAllowed(true);
	    	idMarca.setTextInputAllowed(false);
	    	
	    	
	    	idModelo.addStyleName("Small");
	    	idModelo.setRequired(true);
	    	idModelo.setRequiredError("Campo Obligatorio");
	    	idModelo.setItemCaptionPropertyId("nombre");
	    	idModelo.setImmediate(true);
	    	idModelo.setInvalidAllowed(true);
	    	idModelo.setNullSelectionAllowed(true);
	    	idModelo.setTextInputAllowed(false);
	    	
	    	
	    	idTipo.addStyleName("Small");
	    	idTipo.setRequired(true);
	    	idTipo.setRequiredError("Campo Obligatorio");
	    	idTipo.setItemCaptionPropertyId("nombre");
	    	idTipo.setImmediate(true);
	    	idTipo.setInvalidAllowed(true);
	    	idTipo.setNullSelectionAllowed(true);
	    	idTipo.setTextInputAllowed(false);
	    	
	    	
	    	if ((Integer) item.getItemProperty("id").getValue() == 0){
	    		btnGraba.setCaption("Graba Nuevo");
				btnElimina.setEnabled(false);
		    	dominio.setEnabled(true);
		    	dominio.setReadOnly(false);
		    	dominio.setValue("");
		    	anio.setValue("");
		    	idMarca.setValue(null);
		    	idModelo.setContainerDataSource(modelosDao.getAllModelosContainer(0));
		    	idModelo.setValue(null);
		    	idTipo.setValue(null);
				tb.setSelectable(false);
	    	} else {
	    		int valor=(int)idModelo.getValue();
	    		idModelo.setContainerDataSource(modelosDao.getAllModelosContainer((int)idMarca.getValue()));
	    		idModelo.setValue(valor);
	    //		idModelo.getItemCaption(idModelo.getValue())
	    	}
	    	
	    	
		//////////////////////////////////
		///////// BOTONES ////////////////
		//////////////////////////////////	   
	    	
	    	btnGraba.addClickListener(new ClickListener(){
				public void buttonClick(ClickEvent event) {
						try {
							binder.commit();
							
							VehiculosDAO u = new VehiculosDAO();
							int num=new Formateos().convertirComaPorPunto(anio.getValue()).intValue();
							if(btnGraba.getCaption()=="Graba Nuevo"){
								try {
									 if ((int)idMarca.getValue()>0 && (int)idModelo.getValue()>0 && (int)idTipo.getValue()>0){
										 if (u.existe(dominio.getValue())==0){
											u.agrega(dominio.getValue(), num, (int)idMarca.getValue(), (int)idModelo.getValue(), (int)idTipo.getValue(), activo.getValue(),usrLog.getId()); 
											btnGraba.setCaption("Actualiza");
											tb.setSelectable(true);
											ini();
											}else{
												Notification.show("El Vehículo patente "+ dominio.getValue() +" ya existe registrado", Type.ERROR_MESSAGE);
												dominio.setValue("");
											}
										 }else{
											 Notification.show("Debe seleccionar una Marca, Modelo y Tipo. ", Type.ERROR_MESSAGE);
										 }
									} catch (SQLException e) {
										e.printStackTrace();
									}
								} else if (btnGraba.getCaption()=="Actualiza"){
									u.modifica(dominio.getValue(), num, (int)idMarca.getValue(), (int)idModelo.getValue(), (int)idTipo.getValue(),activo.getValue(),usrLog.getId()); 		
									ini();
								}
						} catch (CommitException e) {
							e.printStackTrace();
						}


				}
			});
	        
        
	        btnElimina.addClickListener(new ClickListener(){
	        	public void buttonClick(ClickEvent event) {
	        		VehiculosDAO u = new VehiculosDAO();
					if (u.existenRegistrosRelacionados(dominio.getValue())==0) {
						ConfirmDialog.show(getUI(), "Confirmar", "¿Desea eliminar el registro seleccionado?","Sí", "No", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
						          if (dialog.isConfirmed()) {
						               // C�digo S�
						        	  u.elimina(dominio.getValue(), usrLog.getId()); 		
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

	   
			idMarca.addValueChangeListener(new ValueChangeListener() {
				 public void valueChange(ValueChangeEvent event) {
					 Object selectedItemId = event.getProperty().getValue();
					 if (selectedItemId != null) {
						 idModelo.setContainerDataSource(modelosDao.getAllModelosContainer((int)idMarca.getValue()));
					 }
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
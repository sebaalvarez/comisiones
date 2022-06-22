package sec.comisiones.vistas;


import sec.comisiones.dao.UsuariosDAO;
import sec.comisiones.mapeos.Usuarios;

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;



public class CambioPasswordForm extends CustomComponent  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8557081246754552912L;
	private VerticalLayout Form_mainLayout;
	private GridLayout Form_gridLayout0; 
	private GridLayout Form_gridLayout1; 
 

	TextField txtIdEmpleado = new TextField("IdEmp");
	TextField txtNombre = new TextField("Nombre");
	TextField txtApellido = new TextField("Apellido");
    TextField txtDni = new TextField("DNI");
    TextField txtIdUsuario = new TextField("IdUsuario");
    TextField txtnomUsuario = new TextField("Nombre Usuario");
    PasswordField txtOldPassword = new PasswordField("Contraseña Actual");
    PasswordField txtOldPassword1 = new PasswordField("Contraseña Actual");
    PasswordField txtNewPassword = new PasswordField("Nuevo Password");
    PasswordField txtNewPassword1 = new PasswordField("Confirmar Nueva Contraseña");
    

    
    NativeButton btnCierre = new NativeButton("Salir");
    NativeButton btnVolver = new NativeButton("Volver");

	
	Usuarios usrLog = new Usuarios();
	UsuariosDAO u = new UsuariosDAO();
	
	
	String idPlanificacion = "";
	String flag = "";
	int bandera = 0;
	
	int control=0;
	
	public CambioPasswordForm() {
		ini(bandera,btnCierre,btnVolver);  
	}
	
	public CambioPasswordForm(Usuarios usr) {
		usrLog= usr;
		ini(bandera,btnCierre,btnVolver);   
	}
	
	public CambioPasswordForm(Usuarios usr, String idPlanif) {
		usrLog= usr;
		this.idPlanificacion=idPlanif;
		ini(bandera,btnCierre,btnVolver);  
	}

	public CambioPasswordForm(Usuarios usr, String flag, NativeButton btnCierre1,  NativeButton btnVolver1) {
		usrLog= usr;
		this.flag=flag;
		this.btnCierre=btnCierre1;
		this.btnVolver=btnVolver1;
		ini(bandera,btnCierre,btnVolver);   
	}
	
	
	///////////////////////////////////////////////	
	///////////////////////////////////////////////	
	
	private void ini(int bandera, NativeButton btnCierre,NativeButton btnVolver){
		buildMainLayout();
		setCompositionRoot(Form_mainLayout);	

		NativeButton btnModificar = new NativeButton("Modificar");
	    NativeButton btnCancelar = new NativeButton("Cancelar");
    	
		txtIdEmpleado.setWidth(6f, Unit.EM);
		txtDni.setWidth(8f, Unit.EM);
		txtNombre.setWidth(18f, Unit.EM);
		txtApellido.setWidth(18f, Unit.EM);
		
		txtIdUsuario.setWidth(6f, Unit.EM);
		txtnomUsuario.setWidth(8f, Unit.EM);
		txtOldPassword.setWidth(15f, Unit.EM);
		txtOldPassword1.setWidth(15f, Unit.EM);
		txtNewPassword.setWidth(15f, Unit.EM);
		txtNewPassword1.setWidth(15f, Unit.EM);
 	

		txtIdEmpleado.setEnabled(false);
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtDni.setEnabled(false);
		txtIdUsuario.setEnabled(false);
		txtnomUsuario.setEnabled(false);
		txtOldPassword.setEnabled(false);
		
		txtOldPassword1.setRequired(true);
		txtNewPassword.setRequired(true);
		txtNewPassword1.setRequired(true);
		
		Form_gridLayout0.addComponent(btnModificar,0,0,0,0);
    	Form_gridLayout0.addComponent(btnCancelar,1,0,1,0);
    	
		HorizontalLayout hl_0 = new HorizontalLayout();
		hl_0.addComponents(txtIdEmpleado,txtDni,txtNombre,txtApellido);
		hl_0.setSpacing(true);
		Form_gridLayout1.addComponent(hl_0,0,0,4,0);
		
	//	Form_gridLayout1.addComponent(txtIdEmpleado,0,0,0,0);
    //	Form_gridLayout1.addComponent(txtDni,1,0,1,0);
    //	Form_gridLayout1.addComponent(txtNombre,2,0,2,0);
    //	Form_gridLayout1.addComponent(txtApellido,3,0,3,0);
    	
		HorizontalLayout hl_1 = new HorizontalLayout();
		hl_1.addComponents(txtIdUsuario,txtnomUsuario);
		hl_1.setSpacing(true);
		Form_gridLayout1.addComponent(hl_1,0,1,4,1);
		
   //	Form_gridLayout1.addComponent(txtIdUsuario,0,1,0,1);
   // 	Form_gridLayout1.addComponent(txtnomUsuario,1,1,1,1);

    	
  //  	Form_gridLayout1.addComponent(txtOldPassword,1,2,1,2);
    	Form_gridLayout1.addComponent(txtOldPassword1,0,2,0,2);
    	Form_gridLayout1.addComponent(txtNewPassword,1,2,1,2);
    	Form_gridLayout1.addComponent(txtNewPassword1,2,2,2,2);
    	
  
	
		try {
				txtIdEmpleado.setValue(String.valueOf(usrLog.getIdEmp()));
				txtDni.setValue(String.valueOf(usrLog.getDniEmp()));
				txtNombre.setValue(usrLog.getNombreEmp());
				txtApellido.setValue(usrLog.getApellidoEmp());
				txtIdUsuario.setValue(String.valueOf(usrLog.getId()));
				txtnomUsuario.setValue(usrLog.getNombre());
				txtOldPassword.setValue(usrLog.getPassword());
			} catch (Exception e) {
				Notification.show("No se pudo acceder al cambio de contraseña, por favor comuniquese con el administrador del sistema.", e.getMessage(),Type.ERROR_MESSAGE);
				e.printStackTrace();
			}
		
	   
		
		
		
        
		
		btnModificar.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
					if (!txtOldPassword1.isEmpty() && !txtNewPassword.isEmpty() && !txtNewPassword1.isEmpty()) {
						if(txtOldPassword1.getValue().equals(txtOldPassword.getValue())){
							if(txtNewPassword1.getValue().equals(txtNewPassword.getValue())){
									
							    	ConfirmDialog.show(getUI(), "Confirmar", "¿Desea guardar? Luego del cambio de contraseña deberá volver a ingresar al sistema.","Sí", "No", new ConfirmDialog.Listener() {
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
										    		// Código Sí
												try {
										        			u.modificaPassword(Integer.parseInt(txtIdUsuario.getValue()),txtNewPassword.getValue(),usrLog.getId());
										        	  		btnCierre.click();
										        		} catch (Exception e) {
										        	  		Notification.show("Se produjo un error al intentar grabar.", e.getMessage(), Type.ERROR_MESSAGE);
										        	  			e.printStackTrace();
										        	  	}
											} else {
										        	  // Código No
											}
										}    
									});
							    	
							    	
							    	
							    	
							}else{
								Notification.show("No es correcta la confirmación de la contraseña.",Notification.Type.ERROR_MESSAGE);
							}
							} else{
								Notification.show("No es correcta la contraseña actual.",Notification.Type.ERROR_MESSAGE);
							} 	
						} else {
								Notification.show("Debe ingresar los campos obligatorios.",Notification.Type.ERROR_MESSAGE);
							}
						} 
		});
        
		
		

		btnCancelar.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				btnVolver.click();	
			} 
		});
        

		
	
		
 
}
	

	
///////////////////////////////////////////////	
///////////////////////////////////////////////	





///////////////////////////////////////////////	
///////////////////////////////////////////////
	
	@AutoGenerated
	private void buildMainLayout() {

		Form_mainLayout = new VerticalLayout();
		Form_mainLayout.setImmediate(false);
		Form_mainLayout.setWidth("100%");
		Form_mainLayout.setHeight("100%");
		Form_mainLayout.setSizeFull();
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		

		Form_mainLayout.addComponent(buildContenedorPanel());

	}
	
	

	private Panel buildContenedorPanel() {

		// common part: create layout
		Panel Form_contenedorPanel = new Panel();
		Form_contenedorPanel.setImmediate(false);
		Form_contenedorPanel.setWidth("100.0%");
		Form_contenedorPanel.setHeight("100.0%");
//		Form_contenedorPanel.setSizeFull();
		
		Panel Form_panel0 = new Panel();
		Panel Form_panel1 = new Panel();
//		Panel Form_panel2 = new Panel();

		
		Form_gridLayout0 = new GridLayout(6,1);
    	Form_gridLayout0.setWidth("100.0%");
    	Form_gridLayout0.setColumnExpandRatio(0, 0);
    	Form_gridLayout0.setColumnExpandRatio(1, 0);
    	Form_gridLayout0.setColumnExpandRatio(2, 0);
    	Form_gridLayout0.setColumnExpandRatio(3, 0);
    	Form_gridLayout0.setColumnExpandRatio(4, 0);
    	Form_gridLayout0.setColumnExpandRatio(5, 1f);
    	
    	Form_gridLayout0.setSpacing(true);
    	
		Form_gridLayout1 = new GridLayout(9,6);
    	Form_gridLayout1.setWidth("100.0%");
    	Form_gridLayout1.setColumnExpandRatio(0, 0);
    	Form_gridLayout1.setColumnExpandRatio(1, 0);
    	Form_gridLayout1.setColumnExpandRatio(2, 0);
    	Form_gridLayout1.setColumnExpandRatio(3, 0);
    	Form_gridLayout1.setColumnExpandRatio(4, 0);
    	Form_gridLayout1.setColumnExpandRatio(5, 0);
    	Form_gridLayout1.setColumnExpandRatio(6, 0);
    	Form_gridLayout1.setColumnExpandRatio(7, 0);
    	Form_gridLayout1.setColumnExpandRatio(8, 1f);

    	Form_gridLayout1.setSpacing(true);
    	
    	Form_panel0.setContent(Form_gridLayout0);
    	Form_panel1.setContent(Form_gridLayout1);


		// verticalLayout_1
    	VerticalLayout Form_verticalLayout_1 = new VerticalLayout();
		Form_verticalLayout_1.setImmediate(false);
		Form_verticalLayout_1.setWidth("100.0%");
		Form_verticalLayout_1.setHeight("100.0%");
		Form_verticalLayout_1.setMargin(true);
		Form_verticalLayout_1.addComponents(Form_panel0,Form_panel1);
		Form_verticalLayout_1.setSpacing(true);
		
		Form_contenedorPanel.setContent(Form_verticalLayout_1);
		
		return Form_contenedorPanel;
		
	// grilla >> panel >> verticalLayout >> panel	
	}


}

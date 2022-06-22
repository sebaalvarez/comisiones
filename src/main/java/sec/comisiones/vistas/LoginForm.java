package sec.comisiones.vistas;



import sec.comisiones.dao.RolesDAO;
import sec.comisiones.dao.UsuariosDAO;
import sec.comisiones.mapeos.Usuarios;
import sec.comisiones.vistas.PrincipalForm;
import sec.comisiones.mapeos.RolesPorUsuarios;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;
import com.agpro.controles.Conexion;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;


@SuppressWarnings("serial")
public class LoginForm extends CustomComponent {

	private VerticalLayout mainLayout;
	
	private NativeButton btnLogin;
	private PasswordField txtPassword;
	private TextField txtUser;
	private ComboBox cmbRol;
	
	private String idUsuario="";


	public LoginForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		
	    	RolesDAO c = new RolesDAO();
	    	cmbRol.setContainerDataSource(c.getRolesDeUsuarioContainer(idUsuario));
	    	cmbRol.setItemCaptionPropertyId("nombreRol");
	    	
	    
	    	txtUser.addTextChangeListener(new TextChangeListener() {
            public void textChange(TextChangeEvent event) {
				if(txtUser.getValue()!= null){
					
					BeanContainer<Integer, RolesPorUsuarios> contenedor=c.getRolesDeUsuarioContainer(event.getText());
					cmbRol.setContainerDataSource(contenedor);
    	
				    	if(contenedor.size()==1){
				    		cmbRol.setValue(contenedor.firstItemId());
					}
				
					
				}
				else {
					cmbRol.setContainerDataSource(c.getRolesDeUsuarioContainer(""));
				}

            }
        });


		btnLogin.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					
						if (txtUser.getValue() != "" && txtPassword.getValue() !="" && cmbRol.getValue()!=null && cmbRol.size()>0){
							UsuariosDAO uman= new UsuariosDAO();
							String user = (String) txtUser.getValue();
							String pass = (String) txtPassword.getValue();
							int rol = (int) cmbRol.getValue();
			//				Notification.show("Usuario: '"+ user+"' PAss: '"+ pass+"' Rol: '"+ rol+"'", Notification.Type.ERROR_MESSAGE);
							Usuarios usr = uman.getUsuarioLogin(user, pass, rol);
							if (usr.getId()==0){
									Notification.show("Usuario o Password Incorrecto ", Notification.Type.ERROR_MESSAGE);
					//		    	txtUser.setValue("");
							    	txtPassword.setValue("");
					//		    	cmbRol.setContainerDataSource(c.getRolesDeUsuarioContainer(""));
							    }else{
//							    	Notification.show("Acceso Correcto ", Notification.Type.ERROR_MESSAGE);
							    	mainLayout.removeAllComponents();
							    	PrincipalForm p = new PrincipalForm(usr);
							    	mainLayout.addComponent(p);
							    }
						} else {
							Notification.show("Hay campos requeridos vacios.", Notification.Type.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
     	
			}
		});
			
		
		
/*		
		btnLogin1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
						
				
				WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
				String ipAddress = webBrowser.getAddress();
				String userAgentInfo = webBrowser.getBrowserApplication();
				String touchDevice = String.valueOf( webBrowser.isTouchDevice() );
				String screenSize = webBrowser.getScreenWidth() + "x" + webBrowser.getScreenHeight();
				String locale = webBrowser.getLocale().toString();
				String isHttps = String.valueOf( webBrowser.isSecureConnection() );
				
				
				StringBuilder description = new StringBuilder();
				description.append( " { IP_Address=" ).append( ipAddress );
				description.append( " | HTTPS=" ).append( isHttps );
				description.append( " | Locale=" ).append( locale );
				description.append( " | TouchDevice=" ).append( touchDevice );
				description.append( " | ScreenSize=" ).append( screenSize );
				description.append( " | UserAgent=" ).append( userAgentInfo );
				description.append( " }" );
				
		//		new Notification("",""+description,Notification.Type.ERROR_MESSAGE, true).show(Page.getCurrent());
				Notification.show("",""+description,Notification.Type.ERROR_MESSAGE);
				
     	
			}
		});

*/		
	}



	
	
	private VerticalLayout buildMainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);

		Panel pnlFormCabecera = build_cabecera();
		Panel pnlFormCuerpo = build_body();
		
		mainLayout.addComponents(pnlFormCabecera,pnlFormCuerpo);
		
		return mainLayout;
	}

	
	private Panel build_cabecera() {
		
		Panel pnlForm = new Panel();
		pnlForm.setWidth(100.0f, Unit.PERCENTAGE);
		pnlForm.setHeight(13.0f, Unit.EM);
		
		VerticalLayout vl_cabecera = new VerticalLayout();
		vl_cabecera.setImmediate(false);
		vl_cabecera.setSizeFull();
		vl_cabecera.setMargin(false);
		vl_cabecera.setSpacing(false);
		
		
		Label lbl = new Label("Sistema de Información - "+ Conexion.getSistemaNombre());
		lbl.addStyleName("letraTitulo");
		
		HorizontalLayout hl=new HorizontalLayout();
		hl.setSizeFull();
		hl.addComponent(lbl);
		hl.setComponentAlignment(lbl, Alignment.MIDDLE_CENTER);
		

		vl_cabecera.addComponent(hl);
		vl_cabecera.setExpandRatio(hl, 1.0f);
		
		pnlForm.setContent(vl_cabecera);
		
		return pnlForm;

	}
		
	
	private Panel build_body() {

		Panel pnlForm = new Panel();
		pnlForm.setWidth(100.0f, Unit.PERCENTAGE);
		pnlForm.setHeight(30.0f, Unit.EM);

		
		VerticalLayout vl_body = new VerticalLayout();
		vl_body.setImmediate(false);
		vl_body.setSizeFull();
		vl_body.setMargin(false);
		vl_body.setSpacing(false);
		
		GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setSizeFull();
		gridLayout_1.setMargin(false);
		gridLayout_1.setColumns(2);
		gridLayout_1.setRows(15);
		
				
		gridLayout_1.setStyleName("fondoImagenBodyLogin");
		
		
		// labelUser
		Label labelUser = new Label();
		labelUser.setImmediate(false);
		labelUser.setWidth("-1px");
		labelUser.setHeight("-1px");
		labelUser.setValue("Usuario: ");
		labelUser.setStyleName("labelBlanco");
		gridLayout_1.addComponent(labelUser, 0, 4);
		gridLayout_1.setComponentAlignment(labelUser, Alignment.MIDDLE_RIGHT);
		
		// txtUser
		txtUser = new TextField();
		txtUser.setImmediate(false);
		txtUser.setWidth("-1px");
		txtUser.setHeight("-1px");
		txtUser.setRequired(true);
		gridLayout_1.addComponent(txtUser, 1, 4);
		gridLayout_1.setComponentAlignment(txtUser, Alignment.MIDDLE_LEFT);
		
		// labelPassword
		Label labelPassword = new Label();
		labelPassword.setImmediate(false);
		labelPassword.setWidth("-1px");
		labelPassword.setHeight("-1px");
		labelPassword.setValue("Password: ");
		labelPassword.setStyleName("labelBlanco");
		gridLayout_1.addComponent(labelPassword, 0, 5);
		gridLayout_1.setComponentAlignment(labelPassword, Alignment.MIDDLE_RIGHT);
		
		// txtPassword
		txtPassword = new PasswordField();
		txtPassword.setImmediate(false);
		txtPassword.setWidth("-1px");
		txtPassword.setHeight("-1px");
		txtPassword.setRequired(true);
		gridLayout_1.addComponent(txtPassword, 1, 5);
		gridLayout_1.setComponentAlignment(txtPassword, Alignment.MIDDLE_LEFT);
		
		// labelRol
		Label labelRol = new Label();
		labelRol.setImmediate(false);
		labelRol.setWidth("-1px");
		labelRol.setHeight("-1px");
		labelRol.setValue("Rol: ");
		gridLayout_1.addComponent(labelRol, 0, 6);
		gridLayout_1.setComponentAlignment(labelRol, Alignment.MIDDLE_RIGHT);
		labelRol.setStyleName("labelBlanco");
		
		cmbRol = new ComboBox();
		cmbRol.setImmediate(false);
		cmbRol.setWidth("-1px");
		cmbRol.setHeight("-1px");
		cmbRol.setRequired(true);
		gridLayout_1.addComponent(cmbRol, 1, 6);
		gridLayout_1.setComponentAlignment(cmbRol, Alignment.MIDDLE_LEFT);
		
		
		btnLogin = new NativeButton();
		btnLogin.setCaption("Login");
		btnLogin.setImmediate(true);
		btnLogin.setWidth("-1px");
		btnLogin.setHeight("25px");
		gridLayout_1.addComponent(btnLogin, 0, 7, 1, 7);
		gridLayout_1.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);

	
		vl_body.addComponent(gridLayout_1);
		vl_body.setComponentAlignment(gridLayout_1, Alignment.MIDDLE_CENTER);
		
		pnlForm.setContent(vl_body);
		
		return pnlForm;
	}
		
	
	
	
}

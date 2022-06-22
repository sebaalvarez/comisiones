package sec.comisiones;



import javax.servlet.annotation.WebServlet;

import java.util.Locale;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.agpro.controles.EncryptDecrypt;
import com.agpro.controles.LeerArchivoTXT;
import com.vaadin.annotations.PreserveOnRefresh;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.Page;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import sec.comisiones.vistas.LoginForm;
import sec.comisiones.vistas.PrincipalForm;
import sec.comisiones.dao.UsuariosDAO;
import sec.comisiones.mapeos.Usuarios;







@PreserveOnRefresh
public class MyUI extends UI {

	/*
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    */
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7505247730230944259L;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true, ui = MyUI.class, widgetset = "sec.comisiones.widgetset.ComisionesWidgetset")
	
	public static class Servlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2002265768700749368L;

	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		Page.getCurrent().setTitle("Comisiones");
		UI.getCurrent().setLocale(new Locale("es"));
		java.util.Locale.setDefault(new Locale ("ES"));
		layout.setSizeFull();
		layout.setMargin(new MarginInfo(false, false, false, false));
		layout.setStyleName("fondoPrincipal");

		setContent(layout);
		
		
		getSession().getService().setSystemMessagesProvider(
			    new SystemMessagesProvider() {
				    /**
					 * 
					 */
					private static final long serialVersionUID = 5713473991598178614L;

					public SystemMessages getSystemMessages(
				        SystemMessagesInfo systemMessagesInfo) {
				        CustomizedSystemMessages messages = new CustomizedSystemMessages();
				        messages.setSessionExpiredMessage("Debe recargar la página y loguearse nuevamente");
				        messages.setSessionExpiredCaption("Finalizo la sesión");
				        messages.setSessionExpiredNotificationEnabled(true);
				        return messages;
			        
				    }
			});
		
		

		boolean login=true;
		String sistema="";
		String nombreSistema="";
		String user="";
		String pass= "";
		int idRolUsuario=0;
		
		
		LeerArchivoTXT cnn = new LeerArchivoTXT();
		
		EncryptDecrypt enc = new EncryptDecrypt();
		
		login=Boolean.parseBoolean(cnn.DevuelveValor("login"));
		sistema= cnn.DevuelveValor("sistema");
		user=cnn.DevuelveValor("usuario");
		pass=enc.decryptPropertyValue(cnn.DevuelveValor("password"));
		idRolUsuario=Integer.parseInt(cnn.DevuelveValor("idRolUsuario"));
		nombreSistema=cnn.DevuelveValor("sistemaNombre");
		
//		System.out.println("login:"+login+" -- sistema:"+ sistema+" -- user:"+user+" -- pass:"+pass+" -- idRol:"+idRolUsuario);
		
		
		this.setTheme("mytheme"+sistema);
	 	Page.getCurrent().setTitle(nombreSistema);
	 	
	 	
		
		
		if(login) {			
			LoginForm p = new LoginForm();
			
			layout.addComponent(p);
			
		} else {
			
		    UsuariosDAO uman= new UsuariosDAO();
		    Usuarios usr=null;
//			try {
				
			usr = uman.getUsuarioLogin(user, pass, idRolUsuario);
			PrincipalForm p = new PrincipalForm(usr); 
			
			layout.addComponent(p);
				
//			} catch (SQLException e) {
//				Notification.show("No se puede conectar a la base de datos","Comuniquese con el administrador del sistema.",Type.ERROR_MESSAGE);
//			}
			
			
		}

		
	}
	
}

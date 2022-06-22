package com.agpro.controles;



import java.io.File;
import java.util.function.Consumer;

import com.vaadin.server.FileResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;




@SuppressWarnings("serial")
public class VisorPDFForm extends CustomComponent {

		private Panel contenedorPanel;
		private VerticalLayout verticalLayout_1;
		private String ruta="";
		
		public VisorPDFForm() {
			ini();  
		}
		
		public VisorPDFForm(String ruta) {
			this.ruta=ruta;
			ini();

		}   


	///////////////////////////////////////////////	
	///////////////////////////////////////////////	
	private void ini(){

		UI.getCurrent().addWindow(new MySub(ruta, (newValue) -> { }));
		
	}

			
	class MySub extends Window {
	    public MySub(String a1, Consumer<String> save) {
	    		super("Reporte PDF");
	    		center();
	        setModal(true);
	        setClosable(true);
	        setResizable(true);
	        this.setWidth(75, Unit.PERCENTAGE);
	        this.setHeight(97, Unit.PERCENTAGE);
	        
	        VerticalLayout content = new VerticalLayout();
	        content.setMargin(true);
	        content.setSpacing(true);
	        content.setSizeFull();
	        
			contenedorPanel = new Panel();
			contenedorPanel.setImmediate(false);
//			contenedorPanel.setWidth("100.0%");
//			contenedorPanel.setHeight("90.0%");
			contenedorPanel.setSizeFull();
			
			verticalLayout_1 = new VerticalLayout();
			verticalLayout_1.setImmediate(false);
//			verticalLayout_1.setWidth(100,Unit.PERCENTAGE);
//			verticalLayout_1.setHeight(70,Unit.PERCENTAGE);
			verticalLayout_1.setMargin(true);
			verticalLayout_1.setSizeFull();
			
			
			File file=new File(ruta);
			Embedded pdf = new Embedded();
			
			if(file.exists()){
				pdf.setSource(new FileResource(file));
				pdf.setMimeType("application/pdf");
				pdf.setType(2);
			
			} else {
				Notification.show("Archivo no encontrado",Type.ERROR_MESSAGE);
			}
			
			verticalLayout_1.addComponents(pdf);
			pdf.setSizeFull();
			
/*			
			String ruta="c:/as.pdf";
			String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
			Button saveExcel = new Button();
	//		Resource res = new FileResource(new File(basepath +"/WEB-INF/docs/settings.xlsx"));
			Resource res = new FileResource(new File(ruta));
			FileDownloader fd = new FileDownloader(res);
			fd.extend(saveExcel);  
*/        
	        
			contenedorPanel.setContent(verticalLayout_1);
	        content.addComponents(contenedorPanel);
	        setContent(content);
	    }
	}


}


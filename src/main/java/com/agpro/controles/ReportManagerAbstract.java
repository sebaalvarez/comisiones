package com.agpro.controles;


import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.agpro.controles.VisorPDFForm;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;





public abstract class ReportManagerAbstract {
	
	private static JasperReport reporte;
	private static JasperPrint  reportFilled; 
	private static JasperViewer viewer;
	
//	static Conexion conn = new Conexion();
	
	static Connection cnn = Conexion.getConn();
	static String path0 = Conexion.getpathReporteSistema();
	static String path2 = Conexion.getpathImagenesSistema();
	static String path3 = Conexion.getpathReportesGenerados();
	static String path4 = Conexion.getpathUploadsImagenes();
	
	private static String nombreReporte="";
	private static Map<String, Object> param= new HashMap<String, Object>();
	private static String preNombreReporte="";
	
/*	
	Button reportGeneratorButton = new Button("Ver reporte");
	
	reportGeneratorButton.addClickListener(new ClickListener(){
		public void buttonClick(ClickEvent event) {
			Map<String, Object> parametro= new HashMap<String, Object>();
			parametro.put("Parameter1",idActividad.getValue());
			ReportManagerAbstract.VerReporte("OTs2Sub.jasper", parametro);
		}
	});

*/	
	
	
	
	public static void CrearReporte (String path){

		try
		{
			String path1 = path0 + path;
			reporte = (JasperReport) JRLoader.loadObjectFromFile(path1);
			reportFilled = new JasperPrint();
			reportFilled = JasperFillManager.fillReport(reporte, null,  cnn);
			
		} catch (JRException ex){
			ex.printStackTrace();
		}
		
		
	}
	
	
	public static void showViewer(String path){
		
		viewer = new JasperViewer(reportFilled,false);
		viewer.setTitle("Vista Previa "+ path);
		viewer.setVisible(true);
		
	}

	
	public  static void exportToPDF (String destination){
		
		try{
			JasperExportManager.exportReportToPdfFile(reportFilled, destination);
		
		}catch(JRException ex){
			ex.printStackTrace();		
		}
		
	}
	
	
	


	public static void VerReporte (String nombreReporte1, Map<String, Object> parametro){
		preNombreReporte="";
		nombreReporte=nombreReporte1;
		param=parametro;
		ini();
	}
	
	
	public static void VerReporte (String nombreReporte1, Map<String, Object> parametro, String preNombre){
		preNombreReporte=preNombre;
		nombreReporte=nombreReporte1;
		param=parametro;
		ini();
	}
	
	
	
	@SuppressWarnings("unused")
	public static void ini() {
		
		try
		{
			String path1 = path0 + nombreReporte;
//			System.out.println("Ruta archivo: "+ path1 +" -- Dir Repo: "+ path0 +" - Dir Imagen: "+ path2 );
						
			reporte = (JasperReport) JRLoader.loadObjectFromFile(path1);

			reportFilled = new JasperPrint();
			
		//	Cargo los parametros que vienen desde la vista y el path de los reportes e imagenes para los subreportes
			Map<String, Object> param1= new HashMap<String, Object>();
			param1=param;
	//		param.put("Parameter1",parametro);
			param1.put("REPORT_DIR", path0);
			param1.put("IMAGENES_DIR", path2);
			param1.put("IMAGENES_UPLOAD_DIR", path4);
		
			reportFilled = JasperFillManager.fillReport(reporte, param1,  cnn);

			Calendar cal = Calendar.getInstance(); 

			String a= String.valueOf(cal.get(Calendar.YEAR));
			String b= String.valueOf(cal.get(Calendar.MONTH)+1);		
			if(b.length()==1){b="0"+b;}
			String c= String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			if(c.length()==1){c="0"+c;}
			String d= String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
			if(d.length()==1){d="0"+d;}
			String e= String.valueOf(cal.get(Calendar.MINUTE));
			if(e.length()==1){e="0"+e;}
			String f= String.valueOf(cal.get(Calendar.SECOND));
			if(f.length()==1){f="0"+f;}
			
			String v = nombreReporte.substring(0, nombreReporte.length()-7)+"_"+a+b+c+d+e+f; 
			
			if(preNombreReporte!="") {
				v = preNombreReporte+"_"+a+b+c; 
			} 
			
			String ruta = path3 + v +".pdf";
			
			JasperExportManager.exportReportToPdfFile(reportFilled, ruta);
		
//			viewer = new JasperViewer(reportFilled,false);
//			viewer.setTitle("Vista Previa "+ nombreReporte);
//			viewer.setVisible(true);	
			
			VisorPDFForm ventPdf=new VisorPDFForm(ruta);
			
			
		} catch (JRException ex){
			ex.printStackTrace();
		}
		
		
	}
	
	
	public static void VerReporte (String path){

		try
		{
			String path1 = path0 + path;
			reporte = (JasperReport) JRLoader.loadObjectFromFile(path1);
			reportFilled = new JasperPrint();
			reportFilled = JasperFillManager.fillReport(reporte, null,  cnn);
			
			viewer = new JasperViewer(reportFilled,false);
			viewer.setTitle("Vista Previa "+ path);
			viewer.setVisible(true);
			
		} catch (JRException ex){
			ex.printStackTrace();
		}
		
		
	}
		

		

	
}

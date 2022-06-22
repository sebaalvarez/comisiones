package com.agpro.controles;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;



public class LeerArchivoTXT {

	
	
	public String DevuelveValor(String codigo)  {
		String str="";

		str=DevuelveValor(new Conexion().getpathConfiguracion() + "conf.ini", codigo);

		return str;
		
	}
	

	public String DevuelveValor(String path, String codigo)  {
		String resultado="";
		String cadena;
	    CharSequence valor="";
	    CharSequence codigo1="";
	    int flag=0;

	    try{ 
	    		BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
	    		String [] cad=new String[2];
	    
	    		while((cadena = b.readLine())!=null && flag==0) {
			    	
				cad=cadena.split(":");
				codigo1=cad[0].trim();
				valor=cad[1].trim();
//				System.out.println("Cod1: "+codigo1+" -- Valor1: "+ valor );

			    if(codigo1.toString().trim().equals(codigo)) {
			    		resultado=valor.toString().trim();
			    		flag=1;
			    }
			    
	    		}
	    	b.close();
	    	
	    	if(flag==0) {
	    		Notification.show("No se encontro valor para el código: "+codigo, "",Type.ERROR_MESSAGE);
	    	}
	    	
	    	
		} catch (Exception e) {
			
			Notification.show("Error al intentar mostrar los registros"," "+e.getMessage(),Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	     
	     
//	    System.out.println("Código: "+ codigo +" -- Valor: "+ resultado +" -- encontrado: "+ flag +" " );
	    
	    
		 return resultado;
	}
	
	
	
//	   public void escribirArchivo() throws IOException {
//
//	        Writer escribe = null;
//
//	        try {
//	            escribe = new BufferedWriter(new OutputStreamWriter(
//	                    new FileOutputStream(file), "UTF8"));
//	            escribe.write("Este es un archivo con codificación utf-8\n"
//	                    + "Ejemplo:"
//	                    + "estos son letras con acento y caracteres especiales: áéíóú ñ $ & %");
//
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            escribe.close();
//	        }
//	    }

//	    public void leerArchivo() throws IOException {
//
//	        String cadena = "";
//	        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
//
//	        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
//	        try {
//	            while ((cadena = in.readLine()) != null) {
//	                System.out.println(cadena);
//
//	            }
//	        } catch (Exception e) {
//
//	        } finally {
//	            in.close();
//	        }
//
//	    }
	
	
}

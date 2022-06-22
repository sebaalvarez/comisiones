package com.agpro.controles;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;



public class LeerProperties {

	Properties prop = new Properties();
	
	
	
	
	public Properties DevuelveProperties(String path, String nombreProp)  {
		
		InputStream input = null;

		try {

			input = new FileInputStream(path + nombreProp + ".properties");
			// load a properties file
			prop.load(input);

			// get the property value and print it out
//			System.out.println(prop.getProperty("database"));
//			System.out.println(prop.getProperty("dbuser"));
//			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  
		return prop;
		
	}
	

	
	
	
	
	
	
	
	
	public void cargaProperties(String path, String nombreProp) {
		
		
		OutputStream output = null;

		try {

			output = new FileOutputStream(path + nombreProp + ".properties");

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		
		
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

package com.agpro.controles;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;



public class Formateos {

	Validaciones v= new Validaciones();
	
	    public String DiezDigitosNumericos(String value) throws ConversionException {
	        if (value == null) {
	            return null;
	        }

	        //Quitamos espacios en blanco.
	        value = value.trim();

	        //Si es una serie de numeros, esto sera "true"
	        boolean onlyNumbers = value.matches("[0-9]+");

	        // Cadena de ejemplo cuando hay letras
	        if (!onlyNumbers) {
	        	if (!v.esNumeroEntero(value)) {Notification.show(v.msgError());}
	        	return "";
	        }

	        // Si no hay 10 digitos, llenamos con ceros
	        if (value.length() < 10) {
	            int padding = 10 - value.length();
	            StringBuilder sb = new StringBuilder(10);
	            
	            for (int i = 0; i < padding; i++) {
	                sb.append("0");
	            }
	            sb.append(value);
	            return sb.toString();
	        }

	        // 10 digitos o mas, recortamos
	        return value.substring(0, 10);
	    }


	 ////////////////////////////////////////////////////////////////////////   
	//////////////////////////////////////////////////////////////////////////
	
        protected DecimalFormat getFormat(Locale locale) {
            if (locale == null) { locale = Locale.getDefault(); }
            DecimalFormat dc = (DecimalFormat) NumberFormat.getInstance(locale);
            dc.applyPattern("#,##0.00");
            dc.setParseBigDecimal(true);
            dc.setRoundingMode(RoundingMode.HALF_UP);
            return dc;
        }

        protected DecimalFormat getFormat() {
        		Locale locale = Locale.getDefault(); 
            DecimalFormat dc = (DecimalFormat) NumberFormat.getInstance(locale);
            dc.applyPattern("###,##0.00");
            dc.setParseBigDecimal(true);
            dc.setRoundingMode(RoundingMode.HALF_UP);
            return dc;
        }
	      
////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//	        public BigDecimal convertirAModelo(String value, Locale locale) throws ConversionException {
	        public BigDecimal convertirComaPorPunto(String value) throws ConversionException {
	            if (value == null) { return  null; }

	            // Quita espacios en blanco
	            value = value.trim();

	            
	            if (!v.esNumeroDecimal(value)) {
	            	Notification.show(v.msgError());
	            	return null;
	            }
	            
	            
	            // Analiza y detecta errores. If the full string was not used, it is an error.
	            ParsePosition parsePosition = new ParsePosition(0);
	            
//	            BigDecimal parsedValue = (BigDecimal) getFormat(locale).parse(value, parsePosition);
	            BigDecimal parsedValue = (BigDecimal) getFormat().parse(value, parsePosition);
	            
	            if (parsePosition.getIndex() != value.length()) {
	                throw new ConversionException("No se puede convertir '" + value + "' a " + getModelType().getName());
	            }

	            if (parsedValue == null) { return null; }
	            
	            return parsedValue;
	        }

	        
/*	        public String convertToPresentation(BigDecimal value, Locale locale) throws ConversionException {
	            if (value == null) {
	                return null;
	            }
	
	            return getFormat(locale).format(value);
        	}
*/
	 
	        
	        public BigDecimal redondearDosDecimales (float num){
		        	DecimalFormat df = new DecimalFormat("###,##0.00");
		        	String value= String.valueOf(num);
	
		        	if (value == null) { return  null; }
	
	            // Quita espacios en blanco
	            value = value.trim();
	
	            if (!v.esNumeroDecimal(value)) {
		            	Notification.show(v.msgError());
		            	return null;
	            }
	            
	            float s=num;
		            
		    	    	BigDecimal price = this.convertirComaPorPunto(String.valueOf(df.format(s)));
		    	    	return price;
	        }
	        
	        
/*	        public String convertToPresentation(BigDecimal value, Locale locale) throws ConversionException {
	            if (value == null) {
	                return null;
	            }

	            return getFormat(locale).format(value);
	        }
*/
	        
	        public Class<BigDecimal> getModelType() {
	            return BigDecimal.class;
	        }

/*
	        public Class<String> getPresentationType() {
	            return String.class;
	        }
*/        




//	    }    
	    


	    	public String formatDate (String date, String initDateFormat, String endDateFormat)  {
	    		String parsedDate= date;
	    		//System.out.println("Fecha ingreso: "+date+ " - Formato ingreso: "+initDateFormat+" - Formato salida: "+endDateFormat+"");
	    		try{
	    			if (!date.isEmpty() || !initDateFormat.isEmpty() || !endDateFormat.isEmpty()){
	    				Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
	    			    //System.out.println(" Fecha formateada inicio: " +initDate);
	    			    
	    			    SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
	    			    
	    			    parsedDate = formatter.format(initDate);
	    			    //System.out.println(" Fecha formateada fin: " +parsedDate);
	    			}
	    		} catch(Exception e){
	    			Notification.show("Se especifico un formato incorrecto de fecha. ",e.getMessage(),Type.ERROR_MESSAGE);
	    		}
	    	    return parsedDate;
	    	}
	    
	    
	    
	    
		public double redondearDecimales(double valorInicial, int numeroDecimales) {
	        double parteEntera, resultado;
	        resultado = valorInicial;
	        parteEntera = Math.floor(resultado);
	        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        resultado=Math.round(resultado);
	        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        return resultado;
	    }
	    
	    
		public float devuelveFloat(TextField t) {
			return this.devuelveFloat(t.getValue());
		}
	    
		public float devuelveFloat(String t) {
			float f=0;
			f=Float.parseFloat(t.replace(".", "").replace(",", "."));
			return f;
		}
		
		
		public int devuelveInt(TextField t) {
			return this.devuelveInt(t.getValue());
		}

		public int devuelveInt(String t) {
			int f=0;
			f=Integer.parseInt(t.replace(".", ""));
			return f;
		}
		
		
		
		public int devuelveValorCmb(ComboBox cmb) {
			int i=0;
			if(!cmb.isEmpty()){i=(int)cmb.getValue();}
			return i;
		}

		
		public String devuelveValorTxt(TextField txt) {
			return this.devuelveValorTxt(txt.getValue());
		}
		

		public String devuelveValorTxtArea(TextArea txt) {
			return this.devuelveValorTxt(txt.getValue());
		}
		
		public String devuelveValorTxt(String txt) {
			String i="";
			if(!txt.isEmpty()){i=txt.replace("'", "´").trim();}
			return i;
		}
		
	
}


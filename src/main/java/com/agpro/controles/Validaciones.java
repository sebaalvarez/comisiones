package com.agpro.controles;

import com.vaadin.ui.PasswordField;

public class Validaciones {

	
	
	public boolean esNumeroEntero(String texto) {
		if (texto.trim().matches("[0-9]+")){
			return true;
		} else {
			return false;
		}
	    
	}
	
	
	public boolean esNumeroDecimal (String texto){
		if (texto.trim().replace(".", "").replace(",", "").matches("[0-9]+")){
			return true;
		} else {
			return false;
		}
		
		
		
		
		
	}
	
	public String msgError () {
	    String msg="No es un múmero valido. ";
	    return msg;
	}
	
	
	
	public static boolean validarPoliticaPwd(PasswordField txtNewPwd) { // más de 5 caracteres al menos una mayuscula, una minuscula y un número
		int largo = 5;
		int flagMayu = 0;
		int flagMinu = 0;
		int flagNum = 0;
		String pass = txtNewPwd.getValue();
		
		
		for(int i = 0; i < pass.length(); i ++) {
			 if(Character.isUpperCase(pass.charAt(i))){           
				 flagMayu = 1;
		          
			 } else if (Character.isLowerCase(pass.charAt(i))) {                      
				 flagMinu = 1;
		          
			 } else if (Character.isDigit(pass.charAt(i))) {                      
				 flagNum = 1;
		          
			 }	 	 
		} 
		
		if(pass.length() >= largo && flagMayu == 1 && flagMinu == 1 && flagNum == 1) {
			return true;
		} else {
			LogAndNotification.printError("La contraseña debe contener más de 5 caracteres, al menos un caracter en mayuscula,"
					+ " un caracter en minuscula y un número","");
			return false;	
		}
	}
	
	
	
}

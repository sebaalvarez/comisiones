package com.agpro.controles;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;






public class EncryptDecrypt {

 
	

    public EncryptDecrypt(){
    }

    
    
    public String encryptPropertyValue(String pwd) {
	    	String encryptedPassword="";
	    	try{
		    	StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		    	encryptor.setPassword("jasypt");
		    	encryptedPassword = encryptor.encrypt(pwd);
	    	} catch (Exception e) {
	//			Notification.show("", ""+e.getMessage(),Type.ERROR_MESSAGE);
	    		encryptedPassword = pwd;
	    	}
	    	
	    	return encryptedPassword;

    }
 
    
    public String decryptPropertyValue(String pwd)  {
    		String decryptedPropertyValue="";
    		try{
    			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    			encryptor.setPassword("jasypt");
    			decryptedPropertyValue = encryptor.decrypt(pwd);
		} catch (Exception e) {
//    				Notification.show("", ""+e.getMessage(),Type.ERROR_MESSAGE);
			decryptedPropertyValue = pwd;
		}

        return decryptedPropertyValue;
    }
	
	
}

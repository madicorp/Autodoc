package net.madicorp.autodoc.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.naming.ServiceUnavailableException;


public class Hash {

	/**
	 * @param args
	 * @throws ServiceUnavailableException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static  String hash(String U) throws ServiceUnavailableException, NoSuchAlgorithmException {
		String V=encrypt(U);
		return V;

	}
	
	public synchronized static String encrypt(String plaintext) throws ServiceUnavailableException, NoSuchAlgorithmException 
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plaintext.getBytes());
 
        byte byteData[] = md.digest();
 
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
	}


}

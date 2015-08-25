package com.center.buuluu.common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncryptMD5Util {

	private static Log logger = LogFactory.getLog(EncryptMD5Util.class);
	
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"}; 
	
	public static String getMD5(String value) {
		String result = null;
		
		if (value == null) {
			return null;
		}

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(value.getBytes());
			String resultString = byteArrayToHexString(results);
			
			//result = resultString.toUpperCase();
			result = resultString.toLowerCase();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		
		
		return result;
		
	}
	
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static String getFileMD5(String fileName) throws Exception {
		return getHash(fileName, "MD5");
	}

	private static String getHash(String fileName, String hashType) throws Exception {
		InputStream fis;
		fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(hashType);
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		//return toHexString(md5.digest());
		return byteArrayToHexString(md5.digest());
	}

	public static void main(String[] args){
		String password = "12345";
		//System.out.println("MD5 of "+password+":\n"+EncryptMD5Util.getMD5(password));
		try {
			System.out.println(getFileMD5("R:\\temp\\d5cafbcf1df89edb78e02e435f977250.zip"));
			String filePath = ".111aaaa.aaa";
			int dotIndex = filePath.indexOf(".");
			String md5sum = filePath.substring(0, dotIndex > 0 ? dotIndex : filePath.length());
//			System.out.println("md5sum====" + md5sum);
			int a = 1000 / 240;
			StringBuffer sb= new StringBuffer("");
			for (int i = 0; i < 10; i++) {
				sb.append(i).append(",");
			}
			if (sb.lastIndexOf(",") == sb.length() - 1) {
				System.out.println(sb.substring(0, sb.length() - 1) + "====");
			} else {
				System.out.println(sb);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

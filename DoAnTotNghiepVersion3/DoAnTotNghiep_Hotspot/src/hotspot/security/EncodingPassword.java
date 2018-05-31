/**
 * Copyright(C) 2018 Luvina software
 * EncodingPassword.java 24/04/2018 NguyenDuyPhong
 */
package hotspot.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Class chứa các phương thức mã hóa mật khẩu
 * @author duyphong170195
 *
 */
public class EncodingPassword {
	/**
	 * Mã hóa mật khẩu theo sha1 
	 * @param password:mật khẩu chưa đã mã hóa
	 * @return mật khẩu đã được mã hóa.
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String password, String salt) throws NoSuchAlgorithmException {	
		
		MessageDigest messageDigest = null;	
		try{
			messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update((password+salt).getBytes());
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}	
		String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);	
		return encryptedPassword;
	}
}

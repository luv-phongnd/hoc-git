/**
 * Copyright(C) 2018 NguyenDuyPhong
 * KetNoiProperties.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.properties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
/**
 * 
 * @author duyphong170195
 *
 */
public class KetNoiProperties {
	/**
	 * Dùng để kết nối tới file properties
	 * @param path : đường dẫn tới file properties
	 * @return Properties đã được tải dữ liệu 
	 */
	public static Properties connectFileProperty(String path){
		Properties prop = new Properties();
		
		/*The ClassLoader class uses a delegation model to search for classes and resources. 
		 * Each instance of ClassLoader has an associated parent class loader. 
		 * When requested to find a class or resource, a ClassLoader instance will delegate the search for the class or resource to its parent class loader before attempting to find the class or resource itself.
		 *  The virtual machine's built-in class loader, called the "bootstrap class loader", does not itself have a parent but may serve as the parent of a ClassLoader instance.*/
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		try{
			//prop.load dùng để tải dữ liệu từ InputStream object.
			prop.load(new InputStreamReader(input, "UTF-8"));
			
			return prop;
		}catch(IOException e){
			e.printStackTrace();
			try {
				input.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

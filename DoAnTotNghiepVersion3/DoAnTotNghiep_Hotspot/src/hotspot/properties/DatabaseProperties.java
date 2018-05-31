
/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DatabaseProperties.java 18/04/2018 NguyenDuyPhong
 */

package hotspot.properties;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import hotspot.utils.CommonConstant;


/**
 * Thao tác với file database.properties
 * @author NguyenDuyPhong
 *
 */
public class DatabaseProperties {
	private static Properties prop;
	private static Map<String,String> hashmap;
	//Khởi tạo 1 lần duy nhất khi lớp được gọi cho tới khi tắt chương trình
	static{
		//Kết nối tới file config_properties
		prop=KetNoiProperties.connectFileProperty(CommonConstant.DATABASE_PROPERTIES);
		//Khởi tạo hashMap chứa giá trị key,value trong file config.properties
		hashmap = new HashMap<String,String>();
		//Enum chứa các key
		Enumeration<?> e = prop.propertyNames();
		//Duyệt enum 
		while(e.hasMoreElements()){
			//Lấy giá trị từ enum
			String key = (String) e.nextElement();
			//Gán giá trị key,value vào hashmap
			hashmap.put(key, prop.getProperty(key,"UTF-8"));
		}
	}
	
	/**
	 * Lấy dữ liệu từ file properties theo propertyKey
	 * @param propertyKey : Tên key trong file database.properties
	 * @return : giá trị của key propertyKey
	 */
	public static String getData(String hashKey){
		//Lấy value từ Key
		String value = hashmap.get(hashKey);
		//Trả về Value
		return value;
	}
}

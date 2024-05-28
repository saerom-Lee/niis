package org.nii.niis.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common
* @FileName : JSONUtil.java 
* @Date : 2016. 12. 19.
* @description : JSON 변환 유틸
* </pre>
*/
public class JSONUtil {
	
	/** 
	* <pre>
	* @Method : strToJson
	* @Date : 2016. 12. 19.
	* @description : String -> Json 변환
	* </pre>
	* @param str
	* @return
	* @throws ParseException
	*/
	public static JSONObject strToJson(String str) throws ParseException {
		JSONParser jsonParser = new JSONParser();
        
        JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
        
        return jsonObject;
	}
	
	/** 
	* <pre>
	* @Method : jsonToMap
	* @Date : 2016. 12. 19.
	* @description : Json -> Map 변환
	* </pre>
	* @param str
	* @return
	* @throws ParseException
	*/
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String str) throws ParseException {
		Map<String, Object> parsedMap = new HashMap<String, Object>();		
		
		try{
			ContainerFactory containerFactory = new ContainerFactory() {
				@Override
		        public Map<String, Object> createObjectContainer() {
		            return new LinkedHashMap<String, Object>();
		        }
		        @Override
		        public List<Object> creatArrayContainer() {
		            return new LinkedList<Object>();
		        }
		    };
		     
		    Object obj = new JSONParser().parse(str, containerFactory);
		    parsedMap = (HashMap<String, Object>) obj;
		}catch (ParseException e){
		    e.printStackTrace();
		}
		return parsedMap;
	}
}

package org.nii.niis.niim.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONUtil {
	
	public static JSONObject strToJson(String str) throws ParseException {
		JSONParser jsonParser = new JSONParser();
        
        JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
        
        return jsonObject;
	}
	
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

package org.nii.niis.niim.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.MetaService;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;

@Service("metaService")
public class MetaServiceImpl implements MetaService {
	
	private final String[] airMetaTable = {
			"air_basemeta_dts",
			"air_orientmap_dts",
			"air_product_dts",
			"air_producteo_dts",
			"meta_airmap_acqut",
			"meta_airmap_cont",
			/*"meta_airmap_contr",*/
			"meta_airmap_distr",
			"meta_airmap_ident",
			"meta_airmap_mait",
			"meta_airmap_refrc",
			"meta_airmap_spatial"
	};
	private final String[] demMetaTable = {
			"dem_basemeta_dts",
/*			"dem_gpscoord_dts",*/
			"dem_product_dts"
	};
	private final String[] ortMetaTable = {
			"meta_ort_cont",
			/*"meta_ort_contr",*/
			"meta_ort_distr",
			"meta_ort_ident",
			"meta_ort_mait",
			"meta_ort_refrc",
			"meta_ort_spatial",
			"ort_basemeta_dts",
			"ort_product_dts"
	};
	private final String[] lidMetaTable = {
			/*"lid_product_dts"*/
	};
	private final String[] nirMetaTable = {
			"air_basemeta_dts",
			"air_orientmap_dts",
			"air_product_dts",
			"air_producteo_dts",
			"meta_airmap_acqut",
			"meta_airmap_cont",
			/*"meta_airmap_contr",*/
			"meta_airmap_distr",
			"meta_airmap_ident",
			"meta_airmap_mait",
			"meta_airmap_refrc",
			"meta_airmap_spatial"
	};
	private final String[] tdsMetaTable = {
			/*"tds_product_dts"*/
	};
	
	
	@Resource(name="propertiesService")
    private EgovPropertyService propertyService;
	
	@Resource(name="metaDAO")
	private MetaDAO metaDAO;
	
	
	public Map<String, List<Map<String, Object>>> getAirMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> airMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<airMetaTable.length; i++){
			sendMap.put("queryId", airMetaTable[i]);
			sendMap.put("imageCde", propertyService.getString("Globals.airImageCode"));
			airMetaMap.put(airMetaTable[i], metaDAO.getAirMetaInfo(sendMap));
		}
		return airMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getAirLibMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> airLibMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<airMetaTable.length; i++){
			sendMap.put("queryId", airMetaTable[i]);
			sendMap.put("imageCde", propertyService.getString("Globals.airLibImageCode"));
			airLibMetaMap.put(airMetaTable[i], metaDAO.getAirLibMetaInfo(sendMap));
		}
		return airLibMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getDemMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> demMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<demMetaTable.length; i++){
			sendMap.put("queryId", demMetaTable[i]);
			demMetaMap.put(demMetaTable[i], metaDAO.getDemMetaInfo(sendMap));
		}
		return demMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getOrtMetaInfo(Map<String, Object> sendMap) throws Exception{
		
		Map<String, List<Map<String, Object>>> ortMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<ortMetaTable.length; i++){
			sendMap.put("queryId", ortMetaTable[i]);
			ortMetaMap.put(ortMetaTable[i], metaDAO.getOrtMetaInfo(sendMap));
		}
		return ortMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getLidMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> lidMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<lidMetaTable.length; i++){
			sendMap.put("queryId", lidMetaTable[i]);
			lidMetaMap.put(lidMetaTable[i], metaDAO.getLidMetaInfo(sendMap));
		}
		return lidMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getNirMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> nirMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<nirMetaTable.length; i++){
			sendMap.put("queryId", nirMetaTable[i]);
			sendMap.put("imageCde", propertyService.getString("Globals.nirImageCode"));
			nirMetaMap.put(nirMetaTable[i], metaDAO.getNirMetaInfo(sendMap));
		}
		return nirMetaMap;
	}
	
	public Map<String, List<Map<String, Object>>> getTdsMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> tdsMetaMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<tdsMetaTable.length; i++){
			sendMap.put("queryId", tdsMetaTable[i]);
			tdsMetaMap.put(tdsMetaTable[i], metaDAO.getTdsMetaInfo(sendMap));
		}
		return tdsMetaMap;
	}
	
	
	public String[] getAirMetaTable() {
		return airMetaTable;
	}
	public String[] getDemMetaTable() {
		return demMetaTable;
	}
	public String[] getOrtMetaTable() {
		return ortMetaTable;
	}
	public String[] getLidMetaTable() {
		return lidMetaTable;
	}
	public String[] getNirMetaTable() {
		return nirMetaTable;
	}
	public String[] getTdsMetaTable() {
		return tdsMetaTable;
	}
}

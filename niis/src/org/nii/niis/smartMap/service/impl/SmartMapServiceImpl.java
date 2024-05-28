package org.nii.niis.smartMap.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.smartMap.service.SmartMapService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("smartMapService")
public class SmartMapServiceImpl implements SmartMapService {
	
	@Resource(name="smartMapDAO")
    private SmartMapDAO smartMapDAO;
	
	@Resource(name="txManager")
	protected DataSourceTransactionManager txManager;

	private final String[] metaTable = {
			"air_zone_dts",
			"air_product_dts",
			"air_basemeta_dts",
//			"air_crs_dts",
			"air_orientmap_as",
			"air_orientmap_dts",
			"air_producteo_dts",
			
			"dem_product_dts",
			"dem_basemeta_dts",
			"dem_orientmap_as",
			
			"ort_product_dts",
			"ort_basemeta_dts",
			"ort_orientmap_as"
	};
	
	@Override
	public String mataIns(Map<String, List<Map<String, Object>>> metaMap) throws Exception {
		String insStatus = "";
		
		// 트랜젝션 설정
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus txStatus = txManager.getTransaction(def);
	    
	    try{
			// 테이블명
			for (int i=0; i<metaTable.length; i++){
				
				//테이블 하나에 해당하는 데이터 Set
				List<Map<String, Object>> dataList = metaMap.get(metaTable[i]); 
				
				for(int j=0; j<dataList.size(); j++){
					//데이터 한줄
					Map<String, Object> sendMap = new HashMap<String, Object>();
					
					Iterator<String> iter = dataList.get(j).keySet().iterator();
					while(iter.hasNext()){
						String key = (String)iter.next();
						Object value = dataList.get(j).get(key);
						sendMap.put(key, value);
					}
					smartMapDAO.metaInsert(metaTable[i], sendMap);
				}
			}
		txManager.commit(txStatus);
		insStatus = "succ";
	}catch(Exception e){
		txManager.rollback(txStatus);
		System.out.println(e.getMessage());
		e.printStackTrace();
		insStatus = "fail";
	}
		return insStatus;
	}

	@Override
	public List<String> getZoneList(Map<String, Object> sendMap) throws Exception {
		return smartMapDAO.getZoneList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getStoInfo() throws Exception {
		return smartMapDAO.getStoInfo();
	}

	@Override
	public String getStoDrv(String stoIdn) throws Exception {
		return smartMapDAO.getStoDrv(stoIdn);
	}

	@Override
	public String getFolderNam(String storageCde) throws Exception {
		return smartMapDAO.getFolderNam(storageCde);
	}

	@Override
	public void insertOrtLoc(Map<String, Object> sendMap) throws Exception {
		smartMapDAO.insertOrtLoc(sendMap);
	}
	
	@Override
	public void insertAirLoc(Map<String, Object> sendMap) throws Exception {
		smartMapDAO.insertAirLoc(sendMap);
	}

}

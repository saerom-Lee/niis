package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FancyTreeSuchiConverter extends AbstractFancyTreeConverter {
	private List<FancyNodeData> scaleNodeDatas = new ArrayList<FancyNodeData>(); //축척
	private List<FancyNodeData> openDvsnNodeDatas; //공개구분코드
	private List<FancyNodeData> mapShtNodeDatas; //도엽번호
	private List<FancyNodeData> productYearNodeDatas; //제작년도

	public List<FancyNodeData> getFancyFolderNode(List<Map<String, Object>> list, String useAt, String imgPath) throws Exception {
		try {
			int index = 0;
			
			for (Map<String, Object> map : list) {
				
				int childCnt = Integer.parseInt(String.valueOf(map.get("childCnt"))) + 1;
				
				if(isExitsChildren("scaleCd", scaleNodeDatas, map)){
					
					openDvsnNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle("1:" + (String) map.get("scaleNm"));
					fancyNodeData.setFolder(true);
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("scaleCd"));
					
					fancyNodeData.setData(key);
					
					scaleNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					openDvsnNodeDatas = tempFancyNodeData.getChildren();
				}
				
				
				if(isExitsChildren("openDvsnCd", openDvsnNodeDatas, map)){
					
					mapShtNodeDatas = tempFancyNodeData.getChildren();				
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String)map.get("openDvsnNm"));
					fancyNodeData.setFolder(true);
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("openDvsnCd"));
					
					fancyNodeData.setData(key);
					
					openDvsnNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					mapShtNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("mapShtNo", mapShtNodeDatas, map)) {
					
					productYearNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle('(' + (String) map.get("mapShtNm") + ')' + (String) map.get("mapShtNo"));
					
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:queryIndex('" + map.get("mapShtNo") + "', '" + map.get("scaleCd") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"위치보기\" title=\"위치보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					fancyNodeData.setExtraClasses("suchi_" + map.get("scaleCd") + "_" + map.get("mapShtNo") + "_" + String.valueOf(map.get("mapSerNo")));
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("mapSerNo"));
					key.put("mapShtNo", map.get("mapShtNo"));
					key.put("mapSerNo", String.valueOf(map.get("mapSerNo")));
					
					fancyNodeData.setData(key);
					
					mapShtNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					productYearNodeDatas = tempFancyNodeData.getChildren();
				}
				
				for(int i=0; i<childCnt; i++){
					FancyNodeData fancyNodeData = new FancyNodeData();	
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle("데이터 로딩중....");
					
					productYearNodeDatas.add(fancyNodeData);
				}
				index++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scaleNodeDatas;
	}
	
	
	public List<FancyNodeData> getFancyDataNode(List<Map<String, Object>> list, String useAt, String imgPath) throws Exception {
		try{
			mapShtNodeDatas = new ArrayList<FancyNodeData>();
			
			for (Map<String, Object> map : list) {
				
				if(isExitsChildren("mapShtNo", mapShtNodeDatas, map)){
					
					productYearNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle('(' + (String) map.get("mapShtNm") + ')' + (String) map.get("mapShtNo"));
					
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:queryIndex('" + map.get("mapShtNo") + "', '" + map.get("scaleCd") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"위치보기\" title=\"위치보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("mapShtNo"));
					key.put("mapShtNo", map.get("mapShtNo"));
					key.put("mapSerNo", String.valueOf(map.get("mapSerNo")));
					key.put("mapHistoryNo", map.get("mapHistoryNo"));
					
					fancyNodeData.setData(key);
					
					mapShtNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					productYearNodeDatas = tempFancyNodeData.getChildren();
				}
				
				FancyNodeData fancyNodeData = new FancyNodeData();
				String productYear = ((String) map.get("productYear")).substring(0, 4);
				String noticeNo = (String) map.get("noticeNo");
				if(noticeNo == null) {
					noticeNo = "-";
				}
				
//				fancyNodeData.setTitle((String) map.get("productYear"));
				fancyNodeData.setTitle("<dl id=\"tree\"><dt>제작</dt></dl>&nbsp;" + productYear + "년&nbsp;&nbsp;");
				
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<dl id=\"tree\"><dt>고시번호</dt></dl>&nbsp;" + noticeNo));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class='btn'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href='#' title='성과정보'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src='" + imgPath + "/sub/ic_3.png' class='metaInfo' alt='성과정보' title='성과정보' />"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				
				
				fancyNodeData.setData(map);
				
				productYearNodeDatas.add(fancyNodeData);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapShtNodeDatas;
	}

}

package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FancyTreeOrtConverter extends AbstractFancyTreeConverter {

	private List<FancyNodeData> yearNodeDatas = new ArrayList<FancyNodeData>();
	private List<FancyNodeData> securityDatas;
	private List<FancyNodeData> gtypDstDatas;
	private List<FancyNodeData> zoneNodeDatas;
	private List<FancyNodeData> mapNodeDatas;
	private List<FancyNodeData> mapnumNodeDatas;

	public List<FancyNodeData> getFancyNodeData(List<Map<String, Object>> list, String useAt) {
		
		int index = 0;
		for (Map<String, Object> map : list) {

			if (isExitsChildren("sceneyear", yearNodeDatas, map)) {

				securityDatas = tempFancyNodeData.getChildren();

			} else {

				FancyNodeData fancyNodeData = new FancyNodeData();

				fancyNodeData.setTitle((String) map.get("sceneyear"));
				fancyNodeData.setFolder(true);

				if (index == 0){
					fancyNodeData.setExpanded(true);
				}else{
					fancyNodeData.setExpanded(false);
				}
				
				Map<String, Object> key = new HashMap<String, Object>();
				key.put("key", map.get("sceneyear"));

				fancyNodeData.setData(key);

				yearNodeDatas.add(fancyNodeData);

				tempFancyNodeData = fancyNodeData;

				securityDatas = tempFancyNodeData.getChildren();
			}
			
			if(isExitsChildren("securityCde", securityDatas, map)){
				
				gtypDstDatas = tempFancyNodeData.getChildren();
				
				
			}else{
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("securityCde"));
				fancyNodeData.setFolder(true);
				fancyNodeData.setSelected(true);
				if (index == 0){
					fancyNodeData.setExpanded(true);
				}else{
					fancyNodeData.setExpanded(false);
				}
				
				Map<String, Object> key = new HashMap<String, Object>();
				key.put("key", map.get("securityCde"));
				
				fancyNodeData.setData(key);
				
				securityDatas.add(fancyNodeData);
				tempFancyNodeData = fancyNodeData;
				
				gtypDstDatas = tempFancyNodeData.getChildren();
			}
			
			if(isExitsChildren("gtypDst", gtypDstDatas, map)){
				
				zoneNodeDatas = tempFancyNodeData.getChildren();
				
			}else{
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle(String.valueOf(map.get("gtypDst")));
				fancyNodeData.setFolder(true);
				fancyNodeData.setSelected(true);
				if (index == 0){
					fancyNodeData.setExpanded(true);
				}else{
					fancyNodeData.setExpanded(false);
				}
				
				Map<String, Object> key = new HashMap<String, Object>();
				key.put("key", map.get("gtypDst"));
				
				fancyNodeData.setData(key);
				
				gtypDstDatas.add(fancyNodeData);
				tempFancyNodeData = fancyNodeData;
				
				zoneNodeDatas = tempFancyNodeData.getChildren();
			}
			
			if (isExitsChildren("zoneCode", zoneNodeDatas, map)) {

				mapnumNodeDatas = tempFancyNodeData.getChildren();

			} else {

				FancyNodeData fancyNodeData = new FancyNodeData();

				fancyNodeData.setTitle((String) map.get("zoneNam"));
				fancyNodeData.setFolder(true);
				fancyNodeData.setExpanded(false);

				Map<String, Object> key = new HashMap<String, Object>();
				key.put("key", map.get("zoneCode"));

				fancyNodeData.setData(key);

				zoneNodeDatas.add(fancyNodeData);

				tempFancyNodeData = fancyNodeData;

				mapnumNodeDatas = tempFancyNodeData.getChildren();
			}

			FancyNodeData fancyNodeData = new FancyNodeData();

			fancyNodeData.setTitle((String) map.get("map5000Num"));

			//파일 존재 유무
			if(map.get("fileExt").equals("")){
				fancyNodeData.setUnselectable(true);
				fancyNodeData.setHideCheckbox(true);
				fancyNodeData.setExtraClasses("fancytree-disable");
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:airDetailInfo('ort')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}else{
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:requestImage('ort', '"+String.valueOf(map.get("xmin"))+"','"+String.valueOf(map.get("ymin"))+"','"+String.valueOf(map.get("xmax"))+"','"+String.valueOf(map.get("ymax"))
						+ "','"+(String) map.get("zoneCode")+(String) map.get("fileNam")
						+ "')"
						+ "\"><img src=\"/niis/images/sub/ic_1.png\" alt=\"\" /></a> "));
				if (useAt.equals("00") || useAt.equals("02")){
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'ortTreeRoot')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_2.png\" alt=\"\" /></a> "));					
				}
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openOrtMetadataLayer('"+(String) map.get("zoneNam")+"','"+(String) map.get("map5000Num")+"','"+(String) map.get("zoneCode")+"')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}
			
//			if(map.get("security_c").equals("SEC001")){
//				fancyNodeData.setIcon("doc_blue.gif");
//				
//			}else if(map.get("security_c").equals("SEC002")){
//				fancyNodeData.setIcon("doc_green.gif");
//				
//				if(map.get("mask_ox").equals("N")) fancyNodeData.setIcon("doc_grey.gif"); 
//				
//			}else if(map.get("security_c").equals("SEC003")){
//				fancyNodeData.setIcon("doc_grey.gif");
//				
//				if(map.get("mask_ox").equals("Y")) fancyNodeData.setIcon("doc_green.gif"); 
//			}else{
//				continue;
//			}
			
			
			/*if(!map.get("security_c").equals("SEC001")){
				fancyNodeData.setExtraClasses("fancytree-disable");
				fancyNodeData.setUnselectable(true);
			}else{
				fancyNodeData.setUnselectable(false);
			}*/
			
			fancyNodeData.setData(map);

			mapnumNodeDatas.add(fancyNodeData);
			index++;
		}

		return yearNodeDatas;
	}

	public List<FancyNodeData> getFancyFolderNode(List<Map<String, Object>> list, String useAt) throws Exception {
		return getFancyFolderNode(list, useAt, "/niis/images");
	}
	
	public List<FancyNodeData> getFancyFolderNode(List<Map<String, Object>> list, String useAt, String imgPath) throws Exception {
		
		int index = 0;
		try{
			for (Map<String, Object> map : list) {
				
				int childCnt = Integer.parseInt(String.valueOf(map.get("childCnt")));
	
				if (isExitsChildren("sceneyear", yearNodeDatas, map)) {
	
					securityDatas = tempFancyNodeData.getChildren();
	
				} else {
	
					FancyNodeData fancyNodeData = new FancyNodeData();
	
					fancyNodeData.setTitle((String) map.get("sceneyear"));
					fancyNodeData.setFolder(true);
	
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("sceneyear"));
	
					fancyNodeData.setData(key);
	
					yearNodeDatas.add(fancyNodeData);
	
					tempFancyNodeData = fancyNodeData;
	
					securityDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("securityCde", securityDatas, map)){
					
					gtypDstDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("securityCde"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setSelected(true);
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("securityCde"));
					
					fancyNodeData.setData(key);
					
					securityDatas.add(fancyNodeData);
					tempFancyNodeData = fancyNodeData;
					
					gtypDstDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("gtypDst", gtypDstDatas, map)){
					
					zoneNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle(String.valueOf(map.get("gtypDst")));
					fancyNodeData.setFolder(true);
					fancyNodeData.setSelected(true);
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("gtypDst"));
					
					fancyNodeData.setData(key);
					
					gtypDstDatas.add(fancyNodeData);
					tempFancyNodeData = fancyNodeData;
					
					zoneNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if (isExitsChildren("zoneCode", zoneNodeDatas, map)) {
	
					mapNodeDatas = tempFancyNodeData.getChildren();
	
				} else {
	
					FancyNodeData fancyNodeData = new FancyNodeData();
	
					fancyNodeData.setTitle((String) map.get("zoneNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getOrtZoneMarker('" + map.get("securityCd") + "', '" + map.get("gtypDst") + "', '" + map.get("zoneCode") + "', '')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					if (index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("zoneCode"));
	
					fancyNodeData.setData(key);
	
					zoneNodeDatas.add(fancyNodeData);
	
					tempFancyNodeData = fancyNodeData;
	
					mapNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if (isExitsChildren("mapNum", mapNodeDatas, map)) {
					
					mapnumNodeDatas = tempFancyNodeData.getChildren();
	
				} else {
	
					FancyNodeData fancyNodeData = new FancyNodeData();
	
					fancyNodeData.setTitle((String) map.get("mapNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getOrtZoneMarker('" + map.get("securityCd") + "', '" + map.get("gtypDst") + "', '" + map.get("zoneCode") + "', '" + map.get("mapNum") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					fancyNodeData.setExtraClasses("ort_" + map.get("zoneCode") + "_" + map.get("gtypDst") + "_" + map.get("securityCd") + "_" + map.get("mapNum"));
	
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("mapNum"));
					key.put("imageCde", map.get("imageCde"));
					key.put("zoneCode", map.get("zoneCode"));
					key.put("gtypDst", map.get("gtypDst"));
					key.put("securityCde", map.get("securityCd"));
					key.put("mapNum", map.get("mapNum"));
	
					fancyNodeData.setData(key);
	
					mapNodeDatas.add(fancyNodeData);
	
					tempFancyNodeData = fancyNodeData;
	
					mapnumNodeDatas = tempFancyNodeData.getChildren();
				}
	
				for(int i=0; i<childCnt; i++){
					FancyNodeData fancyNodeData = new FancyNodeData();	
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle("데이터 로딩중....");
					
					mapnumNodeDatas.add(fancyNodeData);
				}
				index++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return yearNodeDatas;
	}
	
	public List<FancyNodeData> getFancyDataNode(List<Map<String, Object>> list, String useAt) throws Exception {
		return getFancyDataNode(list, useAt, "/niis/images");
	}
	public List<FancyNodeData> getFancyDataNode(List<Map<String, Object>> list, String useAt, String imgPath) throws Exception {
		try{
			mapNodeDatas = new ArrayList<FancyNodeData>();
			
			for (Map<String, Object> map : list) {
				
				if(isExitsChildren("mapNum", mapNodeDatas, map)){
					
					mapnumNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("mapNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getOrtZoneMarker('" + map.get("securityCd") + "', '" + map.get("gtypDst") + "', '" + map.get("zoneCode") + "', '" + map.get("mapNum") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("mapNum"));
					
					fancyNodeData.setData(key);
					
					mapNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					mapnumNodeDatas = tempFancyNodeData.getChildren();
				}
				
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("map5000Num"));

				//파일 존재 유무
				if(!map.get("fileExt").equals("O")){
					fancyNodeData.setUnselectable(true);
					fancyNodeData.setHideCheckbox(true);
					fancyNodeData.setExtraClasses("fancytree-disable");
				}
				
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class='btn'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href='#' title='미리보기'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src='" + imgPath + "/sub/ic_1.png' class='preview' alt='미리보기' title='미리보기' />"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href='#' title='메타데이터보기'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src='" + imgPath + "/sub/ic_3.png' class='metaInfo' alt='메타데이터보기' title='메타데이터보기' />"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				
				fancyNodeData.setData(map);
				
				mapnumNodeDatas.add(fancyNodeData);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapNodeDatas;
	}
}

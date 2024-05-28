package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FancyTreeLidConverter extends AbstractFancyTreeConverter {

	private List<FancyNodeData> yearNodeDatas = new ArrayList<FancyNodeData>();
	private List<FancyNodeData> securityDatas;
	private List<FancyNodeData> zoneNodeDatas;
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
				
				zoneNodeDatas = tempFancyNodeData.getChildren();
				
				
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
				
				zoneNodeDatas = tempFancyNodeData.getChildren();
			}
			
//			if(isExitsChildren("gridInt", gridIntDatas, map)){
//				
//				zoneNodeDatas = tempFancyNodeData.getChildren();
//				
//				
//			}else{
//				FancyNodeData fancyNodeData = new FancyNodeData();
//				fancyNodeData.setTitle((String) map.get("gridInt"));
//				fancyNodeData.setFolder(true);
//				fancyNodeData.setSelected(true);
//				if (index == 0){
//					fancyNodeData.setExpanded(true);
//				}else{
//					fancyNodeData.setExpanded(false);
//				}
//				
//				Map<String, Object> key = new HashMap<String, Object>();
//				key.put("key", map.get("gridInt"));
//				
//				fancyNodeData.setData(key);
//				
//				gridIntDatas.add(fancyNodeData);
//				tempFancyNodeData = fancyNodeData;
//				
//				zoneNodeDatas = tempFancyNodeData.getChildren();
//			}
			
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

			fancyNodeData.setTitle(map.get("map5000Num").toString());
			
			//파일 존재 유무
			if(map.get("fileExt").equals("")){
				fancyNodeData.setUnselectable(true);
				fancyNodeData.setHideCheckbox(true);
				fancyNodeData.setExtraClasses("fancytree-disable");
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:airDetailInfo('lid')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}else{
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getView3DInit('lid', '"+String.valueOf(map.get("stoDrv"))+":\\\\"+String.valueOf(map.get("folderNam")).replace("\\", "\\\\")+"\\\\"+String.valueOf(map.get("fileNam"))+"."+String.valueOf(map.get("fileFormat"))
						+ "')"
						+ "\"><img src=\"/niis/images/sub/ic_1.png\" alt=\"\" /></a> "));
				if (useAt.equals("00") || useAt.equals("02")){
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'lidTreeRoot')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_2.png\" alt=\"\" /></a> "));					
				}
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openLidMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("lidarIdn")+"','"+(String) map.get("zoneCode")+"')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}
			
			
//			if(map.get("security_c").equals("SEC001")){
//				fancyNodeData.setIcon("doc_blue.gif");
//			}else if(map.get("security_c").equals("SEC002")){
//				fancyNodeData.setIcon("doc_green.gif");
//			}else if(map.get("security_c").equals("SEC003")){
//				fancyNodeData.setIcon("doc_grey.gif");
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
					
					zoneNodeDatas = tempFancyNodeData.getChildren();
					
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
					
					zoneNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if (isExitsChildren("zoneCode", zoneNodeDatas, map)) {
	
					mapnumNodeDatas = tempFancyNodeData.getChildren();
	
				} else {
	
					FancyNodeData fancyNodeData = new FancyNodeData();
	
					fancyNodeData.setTitle((String) map.get("zoneNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getLidZoneMarker('" + map.get("securityCd") + "', '" + map.get("zoneCode") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					fancyNodeData.setExtraClasses("lid_" + map.get("zoneCode") + "_" + map.get("securityCd"));
	
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("zoneCode"));
					key.put("imageCde", map.get("imageCde"));
					key.put("zoneCode", map.get("zoneCode"));
					key.put("securityCde", map.get("securityCd"));
	
					fancyNodeData.setData(key);
	
					zoneNodeDatas.add(fancyNodeData);
	
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
			zoneNodeDatas = new ArrayList<FancyNodeData>();
			
			for (Map<String, Object> map : list) {
				
				if(isExitsChildren("zoneCode", zoneNodeDatas, map)){
					
					mapnumNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("zoneNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getLidZoneMarker('" + map.get("securityCd") + "', '" + map.get("zoneCode") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
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

				fancyNodeData.setTitle(map.get("map5000Num").toString());
				
				//파일 존재 유무
				if(!map.get("fileExt").equals("O")){
					fancyNodeData.setUnselectable(true);
					fancyNodeData.setHideCheckbox(true);
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openLidMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("lidarIdn")+"','"+(String) map.get("zoneCode")+"')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_3.png\" alt=\"메타데이터보기\" title=\"메타데이터보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				}else{
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getView3DInit('lid', '" + map.get("nixPath") + "') \">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_1.png\" alt=\"이미지 보기\" title=\"이미지 보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a> "));
					/*if(imgPath.indexOf("niim") > -1 && (useAt.equals("00") || useAt.equals("02"))){
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'lidTreeRoot')\" >"));
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_2.png\" alt=\"개별다운로드\" title=\"개별다운로드\" />"));
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));					
					}*/
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openLidMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("lidarIdn")+"','"+(String) map.get("zoneCode")+"')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_3.png\" alt=\"메타데이터보기\" title=\"메타데이터보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				}
				
				fancyNodeData.setData(map);
				
				mapnumNodeDatas.add(fancyNodeData);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return zoneNodeDatas;
	}
}

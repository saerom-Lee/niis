package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FancyTreeTdsConverter extends AbstractFancyTreeConverter {

	private List<FancyNodeData> yearNodeDatas = new ArrayList<FancyNodeData>();
	private List<FancyNodeData> zoneNodeDatas;
	private List<FancyNodeData> mapnumNodeDatas;
	private List<FancyNodeData> badNodeDatas;

	public List<FancyNodeData> getFancyNodeData(List<Map<String, Object>> list, String useAt) {
		int index = 0;
		for (Map<String, Object> map : list) {

			if (isExitsChildren("sceneyear", yearNodeDatas, map)) {

				zoneNodeDatas = tempFancyNodeData.getChildren();

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

				zoneNodeDatas = tempFancyNodeData.getChildren();
			}

//			if(isExitsChildren("securityCde", securityDatas, map)){
//				
//				gridIntDatas = tempFancyNodeData.getChildren();
//				
//				
//			}else{
//				FancyNodeData fancyNodeData = new FancyNodeData();
//				fancyNodeData.setTitle((String) map.get("securityCde"));
//				fancyNodeData.setFolder(true);
//				fancyNodeData.setSelected(true);
//				if (index == 0){
//					fancyNodeData.setExpanded(true);
//				}else{
//					fancyNodeData.setExpanded(false);
//				}
//				
//				Map<String, Object> key = new HashMap<String, Object>();
//				key.put("key", map.get("securityCde"));
//				
//				fancyNodeData.setData(key);
//				
//				securityDatas.add(fancyNodeData);
//				tempFancyNodeData = fancyNodeData;
//				
//				gridIntDatas = tempFancyNodeData.getChildren();
//			}
			
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

			fancyNodeData.setTitle((String) map.get("map5000Num"));
			
			//파일 존재 유무
			if(map.get("fileExt").equals("")){
				fancyNodeData.setUnselectable(true);
				fancyNodeData.setHideCheckbox(true);
				fancyNodeData.setExtraClasses("fancytree-disable");
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:airDetailInfo('tds')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}else{
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getView3DInit('tds', '"+String.valueOf(map.get("stoDrv"))+":\\\\"+String.valueOf(map.get("folderNam")).replace("\\", "\\\\")+"\\\\"+String.valueOf(map.get("fileNam"))+"."+String.valueOf(map.get("fileFormat"))
						+ "')"
						+ "\"><img src=\"/niis/images/sub/ic_1.png\" alt=\"\" /></a> "));
				if (useAt.equals("00") || useAt.equals("02")){
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'tdsTreeRoot')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_2.png\" alt=\"\" /></a> "));					
				}
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openTdsMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("tdsIdn")+"','"+(String) map.get("zoneCode")+"')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
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
				
				if(isExitsChildren("sceneyear", yearNodeDatas, map)){
	
					zoneNodeDatas = tempFancyNodeData.getChildren();
	
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("sceneyear"));
					fancyNodeData.setFolder(true);
					
					if(index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
	
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("sceneyear"));
	
					fancyNodeData.setData(key);
	
					yearNodeDatas.add(fancyNodeData);
	
					tempFancyNodeData = fancyNodeData;
	
					zoneNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("zoneCode", zoneNodeDatas, map)){
					
					mapnumNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();

					fancyNodeData.setTitle((String) map.get("zoneNam"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);

					if(index == 0){
						fancyNodeData.setExpanded(true);
					}else{
						fancyNodeData.setExpanded(false);
					}
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("zoneCode"));

					fancyNodeData.setData(key);

					zoneNodeDatas.add(fancyNodeData);

					tempFancyNodeData = fancyNodeData;

					mapnumNodeDatas = tempFancyNodeData.getChildren();		
				}
				
				if(isExitsChildren("utl3dMpn", mapnumNodeDatas, map)){
					
					badNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("utl3dMpn"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getTdsZoneMarker('" + map.get("zoneCode") + "', '" + map.get("utl3dMpn") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					fancyNodeData.setExtraClasses("tds_" + map.get("zoneCode") + "_" + map.get("utl3dMpn"));
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("utl3dMpn"));
					key.put("imageCde", map.get("imageCde"));
					key.put("zoneCode", map.get("zoneCode"));
					key.put("utl3dMpn", map.get("utl3dMpn"));
					
					fancyNodeData.setData(key);
					
					mapnumNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					badNodeDatas = tempFancyNodeData.getChildren();		
				}
				
				
				for(int i=0; i<childCnt; i++){
					FancyNodeData fancyNodeData = new FancyNodeData();	
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle("데이터 로딩중....");
					
					badNodeDatas.add(fancyNodeData);
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
			mapnumNodeDatas = new ArrayList<FancyNodeData>();
			
			for (Map<String, Object> map : list) {
				
				if(isExitsChildren("utl3dMpn", mapnumNodeDatas, map)){
					
					badNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("utl3dMpn"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getTdsZoneMarker('" + map.get("zoneCode") + "', '" + map.get("utl3dMpn") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("utl3dMpn"));
					
					fancyNodeData.setData(key);
					
					mapnumNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					badNodeDatas = tempFancyNodeData.getChildren();
				}
				
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("utl3dBad"));
				
				//파일 존재 유무
				if(!map.get("fileExt").equals("O")){
					fancyNodeData.setUnselectable(true);
					fancyNodeData.setHideCheckbox(true);
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openTdsMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("tdsIdn")+"','"+(String) map.get("zoneCode")+"')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_3.png\" alt=\"메타데이터보기\" title=\"메타데이터보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				}else{
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getView3DInit('tds', '" + map.get("nixPath") + "') \">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_1.png\" alt=\"이미지 보기\" title=\"이미지 보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					/*if(imgPath.indexOf("niim") > -1 && (useAt.equals("00") || useAt.equals("02"))){
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'tdsTreeRoot')\" >"));
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_2.png\" alt=\"개별다운로드\" title=\"개별다운로드\" />"));
						fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));					
					}*/
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openTdsMetadataLayer('"+(String) map.get("zoneNam")+"','"+map.get("tdsIdn")+"','"+(String) map.get("zoneCode")+"')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/sub/ic_3.png\" alt=\"메타데이터보기\" title=\"메타데이터보기\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				}
				
				fancyNodeData.setData(map);
				
				badNodeDatas.add(fancyNodeData);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapnumNodeDatas;
	}
}

package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FancyTreeAirConverter extends AbstractFancyTreeConverter {
	
	private List<FancyNodeData> yearNodeDatas = new ArrayList<FancyNodeData>();
	private List<FancyNodeData> securityDatas;
	private List<FancyNodeData> zoneNodeDatas;
	private List<FancyNodeData> courseNodeDatas;
	private List<FancyNodeData> numNodeDatas;
	
	public List<FancyNodeData> getFancyNodeData(List<Map<String, Object>> list, String useAt) {
		int index = 0;
		for (Map<String, Object> map : list) {
			
			if(isExitsChildren("sceneyear", yearNodeDatas, map)){
				
				securityDatas = tempFancyNodeData.getChildren();
				
			}else{
				
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
			
			if(isExitsChildren("zoneCode", zoneNodeDatas, map)){
				
				courseNodeDatas = tempFancyNodeData.getChildren();
				
				
			}else{
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("zoneNam"));
				fancyNodeData.setFolder(true);
				fancyNodeData.setSelected(true);
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
				
				courseNodeDatas = tempFancyNodeData.getChildren();
			}
			
			if(isExitsChildren("phCourse", courseNodeDatas, map)){
				
				numNodeDatas = tempFancyNodeData.getChildren();
				
			}else{
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("phCourse"));
				fancyNodeData.setFolder(true);
				fancyNodeData.setExpanded(false);
				
				Map<String, Object> key = new HashMap<String, Object>();
				key.put("key", map.get("phCourse"));
				
				fancyNodeData.setData(key);
				
				courseNodeDatas.add(fancyNodeData);
				
				tempFancyNodeData = fancyNodeData;
				
				numNodeDatas = tempFancyNodeData.getChildren();		
			}

			FancyNodeData fancyNodeData = new FancyNodeData();			
			fancyNodeData.setTitle((String) map.get("phNum"));
			
			CoordConv conv = new CoordConv();
			
			if(map.get("origin").equals("중부")){
				conv.coordSet(conv.UTMK, conv.KTMM,conv.UTMK ,conv.KTMM);
			}else if(map.get("origin").equals("동부")){
				conv.coordSet(conv.UTMK, conv.KTME,conv.UTMK ,conv.KTME);
			}else if(map.get("origin").equals("서부")){
				conv.coordSet(conv.UTMK, conv.KTMW,conv.UTMK ,conv.KTMW);
			}
			
			conv.mapConv((double)map.get("xmin"), (double)map.get("xmin"));
			
			System.out.println("KTMM X>>>>>>>>>" + conv.getOutX());
			System.out.println("KTMM Y>>>>>>>>>" + conv.getOutY());
			
			
			//파일 존재 유무
			if(map.get("fileExt").equals("")){
				fancyNodeData.setUnselectable(true);
				fancyNodeData.setHideCheckbox(true);
				fancyNodeData.setExtraClasses("fancytree-disable");
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:airDetailInfo('air')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}else{
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:requestImage('air', '"+String.valueOf(map.get("xmin"))+"','"+String.valueOf(map.get("ymin"))+"','"+String.valueOf(map.get("xmax"))+"','"+String.valueOf(map.get("ymax"))
						+ "','"+(String) map.get("fileNam")
						+ "')"
						+ "\"><img src=\"/niis/images/sub/ic_1.png\" alt=\"\" /></a> "));
				if (useAt.equals("00") || useAt.equals("02")){
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:applicationList(2,'airTreeRoot')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_2.png\" alt=\"개별다운로드\" /></a> "));
				}
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:openAirMetadataLayer('"+(String) map.get("zoneNam")+"','"+(String) map.get("zoneCode")+"','"+(String) map.get("phCourse")+"','"+(String) map.get("phNum")+"')\" class=\"btnLayerOpen\"><img src=\"/niis/images/sub/ic_3.png\" alt=\"메타데이터보기\" /></a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
			}

//			if(map.get("securityC").equals("SEC001")){
//				fancyNodeData.setIcon("doc_blue.gif");
//				
//			}else if(map.get("securityC").equals("SEC002")){
//				fancyNodeData.setIcon("doc_green.gif");
//				
//				if(map.get("maskOx").equals("N")) fancyNodeData.setIcon("doc_grey.gif"); 
//				
//			}else if(map.get("securityC").equals("SEC003")){
//				fancyNodeData.setIcon("doc_grey.gif");
//				
//				if(map.get("maskOx").equals("Y")) fancyNodeData.setIcon("doc_green.gif"); 
//			}else{
//				continue;
//			}
			
			fancyNodeData.setData(map);
			
			numNodeDatas.add(fancyNodeData);
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
					
					securityDatas = tempFancyNodeData.getChildren();
					
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
					
					securityDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("securityCde", securityDatas, map)){
					
					zoneNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("securityCde"));
					fancyNodeData.setFolder(true);
					//fancyNodeData.setSelected(true);
					
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
				
				if(isExitsChildren("zoneCode", zoneNodeDatas, map)){
					
					courseNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("zoneNam"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getAirZoneMarker('" + map.get("securityCd") + "', '" + map.get("zoneCode") + "', '')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					//fancyNodeData.setSelected(true);
					
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
					
					courseNodeDatas = tempFancyNodeData.getChildren();
				}
				
				if(isExitsChildren("phCourse", courseNodeDatas, map)){
					
					numNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("phCourse"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getAirZoneMarker('" + map.get("securityCd") + "', '" + map.get("zoneCode") + "', '" + map.get("phCourse") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					fancyNodeData.setExtraClasses("air_" + map.get("zoneCode") + "_" + map.get("phCourse") + "_" + map.get("securityCd"));
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("phCourse"));
					key.put("imageCde", map.get("imageCde"));
					key.put("zoneCode", map.get("zoneCode"));
					key.put("phCourse", map.get("phCourse"));
					key.put("securityCde", map.get("securityCd"));
					
					fancyNodeData.setData(key);
					
					courseNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					numNodeDatas = tempFancyNodeData.getChildren();		
				}
				
				
				for(int i=0; i<childCnt; i++){
					FancyNodeData fancyNodeData = new FancyNodeData();	
					fancyNodeData.setExtraClasses("fancytree-disable");
					fancyNodeData.setTitle("데이터 로딩중....");
					
					numNodeDatas.add(fancyNodeData);
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
			courseNodeDatas = new ArrayList<FancyNodeData>();
			
			for (Map<String, Object> map : list) {
				
				if(isExitsChildren("phCourse", courseNodeDatas, map)){
					
					numNodeDatas = tempFancyNodeData.getChildren();
					
				}else{
					
					FancyNodeData fancyNodeData = new FancyNodeData();
					fancyNodeData.setTitle((String) map.get("phCourse"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class=\"btn\">"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href=\"javascript:getAirZoneMarker('" + map.get("securityCd") + "', '" + map.get("zoneCode") + "', '" + map.get("phCourse") + "')\" >"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src=\"" + imgPath + "/icon/ic_3.png\" alt=\"\" />"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
					fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
					fancyNodeData.setFolder(true);
					fancyNodeData.setExpanded(false);
					
					Map<String, Object> key = new HashMap<String, Object>();
					key.put("key", map.get("phCourse"));
					
					fancyNodeData.setData(key);
					
					courseNodeDatas.add(fancyNodeData);
					
					tempFancyNodeData = fancyNodeData;
					
					numNodeDatas = tempFancyNodeData.getChildren();
				}
				
				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("phNum"));
				
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
				
				numNodeDatas.add(fancyNodeData);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseNodeDatas;
	}
}

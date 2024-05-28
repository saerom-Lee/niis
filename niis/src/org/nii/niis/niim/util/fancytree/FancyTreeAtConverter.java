package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FancyTreeAtConverter extends AbstractFancyTreeConverter {

	private List<FancyNodeData> zoneNodeDatas = new ArrayList<FancyNodeData>();

	public List<FancyNodeData> getFancyFolderNode(List<Map<String, Object>> list, String useAt) throws Exception {
		return getFancyFolderNode(list, useAt, "/niis/images");
	}

	public List<FancyNodeData> getFancyFolderNode(List<Map<String, Object>> list, String useAt, String imgPath)
			throws Exception {
		int index = 0;

		try {
			for (Map<String, Object> map : list) {

				FancyNodeData fancyNodeData = new FancyNodeData();
				fancyNodeData.setTitle((String) map.get("zoneYy") + ' ' + (String) map.get("zoneNam"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<span class='btn'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<a href='#' title='메타데이터보기'>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("<img src='" + imgPath + "/sub/ic_3.png' class='metaInfo' alt='메타데이터보기' title='메타데이터보기' />"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</a>"));
				fancyNodeData.setTitle(fancyNodeData.getTitle().concat("</span>"));
				
				fancyNodeData.setData(map);

				if (index == 0) {
					fancyNodeData.setExpanded(true);
				} else {
					fancyNodeData.setExpanded(false);
				}

				Map<String, Object> key = new HashMap<String, Object>();
				key.put("zoneCode", map.get("zoneCode"));
				key.put("zoneNam", map.get("zoneNam"));
				key.put("imageCde", map.get("imageCde"));
				key.put("zoneYy", map.get("zoneYy"));

				fancyNodeData.setData(key);

				zoneNodeDatas.add(fancyNodeData);
				tempFancyNodeData = fancyNodeData;

				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zoneNodeDatas;
	}

}

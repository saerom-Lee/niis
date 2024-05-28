package org.nii.niis.niim.util.fancytree;

import java.util.List;
import java.util.Map;


public abstract class AbstractFancyTreeConverter {

	protected FancyNodeData tempFancyNodeData;

	public boolean isExitsChildren(String compareColumn, List<FancyNodeData> datas, Map<String, Object> compareMap) {
		
		for (FancyNodeData fancyNodeData : datas) {
			if(fancyNodeData.getData().get("key").equals(compareMap.get(compareColumn))){
				tempFancyNodeData = fancyNodeData;
				return true;
			}
		}
		return false;
	}

}
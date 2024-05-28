package org.nii.niis.niim.util.fancytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FancyNodeData {

	private String title = "";
	private boolean folder;
	private boolean hideCheckbox;
	private boolean unselectable;
	private boolean expanded;
	private boolean selected;
	private String extraClasses = "";
	private String icon = "";
	private Map<String, Object> data = new HashMap<String, Object>();
	private List<FancyNodeData> children = new ArrayList<FancyNodeData>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isFolder() {
		return folder;
	}
	public void setFolder(boolean folder) {
		this.folder = folder;
	}
	public boolean isHideCheckbox() {
		return hideCheckbox;
	}
	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}
	public boolean isUnselectable() {
		return unselectable;
	}
	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public String getExtraClasses() {
		return extraClasses;
	}
	public void setExtraClasses(String extraClasses) {
		this.extraClasses = extraClasses;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public List<FancyNodeData> getChildren() {
		return children;
	}
	public void setChildren(List<FancyNodeData> children) {
		this.children = children;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}

package org.nii.niis.niim.service;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CommonVO {
	
	private int _sPage_; 
	private int _ePage_;
	
	/**
	 * return _sPage_
	 * @return int
	 */
	public int get_sPage_() {
		return _sPage_;
	}
	/**
	 * set _sPage_
	 * @param _sPage_
	 */
	public void set_sPage_(int _sPage_) {
		this._sPage_ = _sPage_;
	}	
	
	/**
	 * return _ePage_
	 * @return int
	 */
	public int get_ePage_() {
		return _ePage_;
	}
	/**
	 * set _ePage_
	 * @param _ePage_
	 */
	public void set_ePage_(int _ePage_) {
		this._ePage_ = _ePage_;
	}
	
	/**
	 * 데이터 확인용 function
	 * @return String
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}

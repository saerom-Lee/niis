package org.nii.niis.niim.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.nii.niis.niim.service.CommonVO;
import org.springframework.web.servlet.ModelAndView;

public class PagingUtil {
	
	private static PagingUtil pagingUtil = new PagingUtil();
	
	private PagingUtil(){}
	
	/**
	 * 클래스 singleton 처리
	 * @return PagingUtil
	 */
	public static synchronized PagingUtil getInstance(){
		return pagingUtil;
	}
	
	
	private List<Page> pages = new ArrayList<Page>();
	
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param vo
	 * @param mav
	 * @param tot
	 * @return 
	 */
	public void setPageData(Map<String, Object> param, CommonVO vo, ModelAndView mav, int tot){
    	
		param.put("_$totCnt", tot);
		setPageData(param, vo, mav);
    }
	
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param vo
	 * @param mav
	 * @return 
	 */
	public void setPageData(Map<String, Object> param, CommonVO vo, ModelAndView mav){
    	
    	setPageData(param, mav);
    	vo.set_sPage_((Integer)param.get("_sPage_"));
    	vo.set_ePage_((Integer)param.get("_ePage_"));
    }
	
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param sendMap
	 * @param mav
	 * @param tot
	 * @return 
	 */
	public void setPageData(Map<String, Object> param, Map<String, Object> sendMap, ModelAndView mav, int tot){
    	
		param.put("_$totCnt", tot);
		setPageData(param, sendMap, mav);
    }
	
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param sendMap
	 * @param mav
	 * @return 
	 */
	public void setPageData(Map<String, Object> param, Map<String, Object> sendMap, ModelAndView mav){
    	
		sendMap.put("_$pageUri"		, param.get("_$pageUri"));
	    sendMap.put("_$pageDivID"	, param.get("_$pageDivID"));
	    sendMap.put("_$pageFunc"	, param.get("_$pageFunc"));
	    sendMap.put("_$pageParam"	, param.get("_$pageParam"));
	    sendMap.put("_$currPage"	, param.get("_$currPage"));
	    sendMap.put("_$totCnt"		, param.get("_$totCnt"));
	    sendMap.put("_$pageUnit"	, param.get("_$pageUnit"));
	    sendMap.put("_$pageSize"	, param.get("_$pageSize"));
		setPageData(sendMap, mav);
    }
	
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param mav
	 * @param tot
	 * @return 
	 */
	public void setPageData(Map<String, Object> param, ModelAndView mav, int tot){
    	
    	param.put("_$totCnt", tot);
    	setPageData(param, mav);
    }
    
	/**
	 * 공통페이지처리 유틸
	 * @param param
	 * @param mav
	 * @return 
	 */
    public void setPageData(Map<String, Object> param, ModelAndView mav){
    	Iterator<String> itr = param.keySet().iterator();while(itr.hasNext()){String key = itr.next();System.out.println("key["+key+"], value["+param.get(key)+"]");}
    	Page pageData = null;
    	
    	try{
	    	if(null != pages && pages.size() > 0){
	    		pageData = pages.get(0);
	    	}else{
	    		pageData = new Page();
	    	}
	    	
	    	pageData.setUri((String)param.get("_$pageUri"));
	    	pageData.setDiv((String)param.get("_$pageDivID"));
	    	pageData.setFunc((String)param.get("_$pageFunc"));
	    	pageData.setParam((String)param.get("_$pageParam"));
	    	pageData.setCurrPage(NVL(param.get("_$currPage"), 1));
	    	
	    	
	    	int pTotCnt = NVL(param.get("_$totCnt"), 0);
	    	int pUnit   = NVL(param.get("_$pageUnit"), 25);
	    	int pSize   = NVL(param.get("_$pageSize"), 10);
	    	
	    	pageData.setPageInfo(pTotCnt, pUnit, pSize);
	    	
	    	if(pages == null){
	    		pages = new ArrayList<Page>();
	    	}
	    	
	    	pages.add(0, pageData);
	    	
	    	mav.addObject("_pageData_", render(pageData));
	    	System.out.println("");
	    	System.out.println(pageData.toString());
	    	System.out.println("");
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	int startPage = (pageData.currPage-1)*pageData.pageUnit + 1;
    	int endPage = (pageData.currPage)*pageData.pageUnit;
    	param.put("_sPage_", startPage);
    	param.put("_ePage_", endPage);
    }
    
    
    /**
	 * 페이징처리 rendering
	 * @param pageData
	 * @return String
	 */
	private String render(Page pageData) {
		
		if(pageData.getTotCnt() <= 0){
			return "";
		}
		
		StringBuilder html = new StringBuilder();
		System.out.println("pagedataIndexOf>>>>>>" + pageData.getUri().indexOf("/niis/niim/") + ">>>>>>>.." + (pageData.getUri().indexOf("/niis/niim/") < 0));
		System.out.println("pageUri>>>>>>" + pageData.getUri());
		if(pageData.getUri().indexOf("/niis/") < 0/* || pageData.getUri().indexOf("/search/") < 0*/){
			String hiddenId = "#" + pageData.getDiv() + " #_currPage";
			String first = pageData.getCurrPage() > 1 ? "onclick=\"javascript:$('" + hiddenId + "').val('1');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String prev  = pageData.getCurrPage() > 1 ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getPrevPage() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String next  = pageData.getCurrPage() < pageData.getTotPage() ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getNextPage() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String last  = pageData.getCurrPage() < pageData.getTotPage() ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getTotPage() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			
			html.append("<input type=\"hidden\" id=\"_currPage\" value=\"1\">");
			html.append("<input type=\"hidden\" id=\"_totPage\" value=\"" + pageData.getTotPage() + "\">");
			html.append("<a href=\"#none\" class=\"btnFirst\" " + first + ">"
					+   	"<img src=\"/niis/images/board/btn_first.gif\" alt=\"처음\" />"
					+   "</a>");
			html.append("<a href=\"#none\" class=\"btnPrev\" " + prev + ">"
					+   	"<img src=\"/niis/images/board/btn_prev.gif\" alt=\"이전\" />"
					+   "</a>");
			html.append("paging &nbsp;&nbsp;<input type=\"text\" id=\"_pageInput\" class=\"text\" value=\"" + pageData.getCurrPage() + "\" /> <span>/ " + pageData.getTotPage() + "  페이지</span>");
			html.append("<a href=\"#none\" class=\"btnNext\" " + next + ">"
					+   	"<img src=\"/niis/images/board/btn_next.gif\" alt=\"다음\" />"
					+   "</a>");
			html.append("<a href=\"#none\" class=\"btnLast\" " + last + ">"
					+   	"<img src=\"/niis/images/board/btn_last.gif\" alt=\"마지막\" />"
					+   "</a>");
			html.append("<select id=\"_pageUnit\" onchange=\"" + pageData.getFunc() + "(" + pageData.getParam() + ");\">"
					+   	"<option value=\"25\"" + (pageData.getPageUnit() == 25 ? "selected" : "") + ">25</option>"
					+   	"<option value=\"50\"" + (pageData.getPageUnit() == 50 ? "selected" : "") + ">50</option>"
					+   	"<option value=\"100\"" + (pageData.getPageUnit() == 100 ? "selected" : "") + ">100</option>"
					+   "</select>");
		}else{
			String hiddenId = "#" + pageData.getDiv() + " #_currPage";
			String first = pageData.getCurrPage() > 1 ? "onclick=\"javascript:$('" + hiddenId + "').val('1');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String prev  = pageData.getCurrPage() > 1 ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getPrevIndex() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String next  = pageData.getCurrPage() < pageData.getTotPage() ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getNextIndex() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			String last  = pageData.getCurrPage() < pageData.getTotPage() ? "onclick=\"javascript:$('" + hiddenId + "').val('" + pageData.getTotPage() + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\"" : "";
			
			int startIndex = ((pageData.getCurrPage() - 1) / pageData.getPageSize()) * pageData.getPageSize() + 1;
			
			html.append("<input type=\"hidden\" id=\"_currPage\" value=\"1\">");
			html.append("<input type=\"hidden\" id=\"_totPage\" value=\"" + pageData.getTotPage() + "\">");
			html.append("<li class=\"btnPaging btnPagingFirst\">"
					+   	"<a href=\"#none\" " + first + ">처음</a>"
					+   "</li>");
			html.append("<li class=\"btnPaging btnPagingPrev\">"
					+   	"<a href=\"#none\" " + prev + ">이전</a>"
					+   "</li>");
			for(int i=startIndex; i<(startIndex + pageData.getPageSize()); i++){
				if(pageData.getCurrPage() == i){
					html.append("<li><strong>" + i + "</strong></li>");
				}else{
					html.append("<li><a href=\"#none\" onclick=\"javascript:$('" + hiddenId + "').val('" + i + "');" + pageData.getFunc() + "(" + pageData.getParam() + ");\">" + i + "</a></li>");
				}
				if(pageData.getTotPage() == i){
					break;
				}
			}
			html.append("<li class=\"btnPaging btnPagingNext\">"
					+   	"<a href=\"#none\" " + next + ">다음</a>"
					+   "</li>");
			html.append("<li class=\"btnPaging btnPagingLast\">"
					+   	"<a href=\"#none\" " + last + ">마지막</a>"
					+   "</li>");
		}
		
		return html.toString();
	}


	
	public class Page {
		
		private String name;
		private String uri;
		private String div;
		private String func;
		private String param;
	    private int totCnt;
	    private int totPage;
	    private int pageUnit;
	    private int pageSize;
	    private int prevPage;
	    private int currPage;
	    private int nextPage;
	    private int prevIndex;
	    private int nextIndex;
	    
	    public String getName() {return name;}
		public String getUri() {return uri;}
		public String getDiv() {return div;}
		public String getFunc() {return func;}
		public String getParam() {return param;}
		public int getTotCnt() {return totCnt;}
		public int getTotPage() {return totPage;}
		public int getPageUnit() {return pageUnit;}
		public int getPageSize() {return pageSize;}
		public int getPrevPage() {return prevPage;}
		public int getCurrPage() {return currPage;}
		public int getNextPage() {return nextPage;}
		public int getPrevIndex() {return prevIndex;}
		public int getNextIndex() {return nextIndex;}
		
		public void setName(String name) {this.name = name;}
		public void setUri(String uri) {this.uri = uri;}
		public void setDiv(String div) {this.div = div;}
		public void setFunc(String func) {this.func = func;}
		public void setParam(String param) {this.param = param;}
		//public void setTotCnt(int totCnt) {this.totCnt = totCnt;}
		public void setTotPage(int totPage) {this.totPage = totPage;}
		//public void setPageUnit(int pageUnit) {this.pageUnit = pageUnit;}
		//public void setPageSize(int pageSize) {this.pageSize = pageSize;}
		//public void setPrevPage(int prevPage) {this.prevPage = prevPage;}
		public void setCurrPage(int currPage) {this.currPage = currPage;}
		//public void setNextPage(int nextPage) {this.nextPage = nextPage;}
		//public void setPrevIndex(int prevIndex) {this.prevIndex = prevIndex;}
		//public void setNextIndex(int nextIndex) {this.nextIndex = nextIndex;}
		
		/**
		 * 페이지데이터 세팅
		 * @param totCnt
		 * @param pageUnit
		 * @param pageSize
		 * @return 
		 */
		public void setPageInfo(int totCnt, int pageUnit, int pageSize){
			
			this.totCnt    = totCnt;
			this.pageUnit  = pageUnit;
			this.pageSize  = pageSize;
			this.totPage   = totCnt / pageUnit + (totCnt % pageUnit > 0 ? 1 : 0);
			this.prevPage  = currPage - 1;
			this.nextPage  = currPage + 1;
			this.prevIndex = currPage <= pageSize ? 1 : ((currPage - 1) / pageSize) * pageSize;
			this.nextIndex = currPage >= totPage - pageSize ? totPage : ((currPage - 1) / pageSize + 1) * pageSize + 1; 
		}
		
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		}
	}
	
	/**
	 * null 처리 함수
	 * @param str
	 * @return String
	 */
	public String NVL(Object str){
		if(null == str) return "";
		else return (String)str;
	}
	
	/**
	 * null 처리 함수
	 * @param str1
	 * @param str2
	 * @return String
	 */
	public String NVL(Object str1, String str2){
		if(null == str1) return str2;
		else return (String)str1;
	}
	
	/**
	 * null 처리 함수
	 * @param num1
	 * @param num2
	 * @return int
	 */
	public int NVL(Object num1, int num2){
		try{
			if(null == num1) return num2;
			else return Integer.parseInt(num1.toString());
		}catch(NumberFormatException e){
			return num2;
		}
	}
}

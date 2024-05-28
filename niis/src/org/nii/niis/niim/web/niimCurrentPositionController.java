package org.nii.niis.niim.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.SearchAreaVO;
import org.nii.niis.niim.service.SearchPOIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubist.giscore.io.WKBReader;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

/*
 * 공간검색 서비스 비즈니스 로직을 처리하는 Control 클래스
 */
@Controller
public class niimCurrentPositionController
{	
	@Resource(name="SearchPOIService")
	private SearchPOIService searchPOIService;
	
	private List<SearchAreaVO> currentlist = new ArrayList<SearchAreaVO>();
	private List<SearchAreaVO> list = null;
	
	private String sSrs = "UTMK";
	
	public niimCurrentPositionController(){
		this.getAreaGeometry();
	}
	
    @RequestMapping(value="/rest/getCurPosition.do")
    public void getCurPosition(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		byte[] resBytes = null;
        try{
        	this.getAreaGeometry(Float.parseFloat(x), Float.parseFloat(y));
        	this.pointOfContains(Float.parseFloat(x), Float.parseFloat(y));
        	resBytes = this.getXML();
        }catch(Exception ex){
            System.out.println(ex);
        }

        if(resBytes == null)
        {
            return;
        }
        int iLength = resBytes.length;
        response.setContentLength(iLength);
        OutputStream os = response.getOutputStream();
        response.setContentType("text/xml; charset=UTF-8");
        os.write(resBytes);
        os.flush();
        os.close();
    }
    
    private void getAreaGeometry(){
		List<SearchAreaVO> bndry = null;
		try{
			if (this.list == null){
				bndry = this.searchPOIService.selectAreaList();
				if (bndry != null){
	                WKBReader wkbReader = new WKBReader();
	                for (int i = 0; i < bndry.size(); i++){
	                	SearchAreaVO vo = (SearchAreaVO)bndry.get(i);
	                	vo.setGeom(wkbReader.read(vo.getWkb_geometry()));
	                }
				}
                this.list = bndry;
			}else{
				this.currentlist.clear();
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}
    
    private void getAreaGeometry(float x, float y){
		List<SearchAreaVO> bndry = null;
		try{
//			if (this.list == null){
				bndry = this.searchPOIService.selectAreaList(x, y);
				if (bndry != null){
	                WKBReader wkbReader = new WKBReader();
	                for (int i = 0; i < bndry.size(); i++){
	                	SearchAreaVO vo = (SearchAreaVO)bndry.get(i);
	                	vo.setGeom(wkbReader.read(vo.getWkb_geometry()));
	                }
				}
                this.list = bndry;
//			}else{
//				this.currentlist.clear();
//			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}
	
    private String getContentsXML(){
    	StringBuffer sf = new StringBuffer();
    	String sido = null;
    	String gugun = null;
    	String dong = null;
    	String admcd = "";
    	if (this.currentlist != null){
			sf.append("<position>");
    		for (int i = 0; i < this.currentlist.size(); i++){
    			SearchAreaVO vo = (SearchAreaVO)this.currentlist.get(i);
    			if (vo.getDong_nm() != ""){
    				dong = vo.getDong_nm();
    				gugun = vo.getSigngu_nm();
    				sido = vo.getCtprvn_nm();
    				admcd = vo.getAdmcd();
    			}else{
    				sido = vo.getCtprvn_nm();
    				admcd = vo.getAdmcd();
    			}
    		}
			sf.append(sido+" "+gugun+" "+dong);
			sf.append("</position>");
			sf.append("<positionCode>");
			sf.append(admcd);
			sf.append("</positionCode>");
    	}
    	
    	return sf.toString();
    }
	
    
	private void pointOfContains(float x, float y){
		Geometry pointGeom = null;
		Coordinate point = new Coordinate(x, y);
		GeometryFactory factory = new GeometryFactory();
		pointGeom = factory.createPoint(point);
		
		if (this.list != null){
			for (int i = 0; i < this.list.size(); i++){
				SearchAreaVO vo = (SearchAreaVO)this.list.get(i);
				boolean flag = vo.getGeom().contains(pointGeom);
				if (flag){
					this.currentlist.add(vo);
				}
			}
		}
	}
	
    private byte[] getXML(){
    	StringBuffer kmlBuffer = new StringBuffer();
        kmlBuffer.append("<?xml version='1.0' encoding='UTF-8'?>\n");  
        kmlBuffer.append("<Document>");
        
        kmlBuffer.append(this.getContentsXML());
        
        kmlBuffer.append("</Document>");   
//		System.out.println("------------------------------------------");
//		System.out.println(kmlBuffer.toString());
//		System.out.println("------------------------------------------");
        byte[] resBytes = null;
        try{
        	resBytes = kmlBuffer.toString().getBytes("UTF-8");
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
		return resBytes;
	}
    
    protected String getSrcSRID(){
    	return this.sSrs;
    }
	
}
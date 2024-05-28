package org.nii.niis.niim.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.AddressSearchService;
import org.nii.niis.niim.service.AddressSearchVO;
import org.nii.niis.niim.service.IndexMapVO;
import org.nii.niis.niim.util.BlackList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubist.giscore.io.WKBReader;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행정명 검색 controller 객체
 * @stereotype control 
 */

@Controller
@RequestMapping(value="niimFor")
public class NiimAddressSearchController {
    /**
     * 행정명 검색 interface 객체
     * @directed 
     */
	
	@Resource(name="addressSearchService")
    private AddressSearchService addressSearchService;
		
	private List<EgovMap> addressList = null;
	private List<AddressSearchVO> list = null;
	private List<IndexMapVO> indexMapList = null;

	
	/**
     * 행정경계를 가져온다
     */		
	@RequestMapping(value="/search/searchAddressArea.do")
	public void getAddressArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admcd = request.getParameter("admcd");
		
		byte[] resBytes = null;
		
		// 보안 코딩
		admcd = BlackList.getStrCnvrXss(admcd);
				
		try{
        	this.getAreaGeometry(admcd);
        	resBytes = this.getXML();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }

        if(resBytes == null){
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
	
	/**
     * 행정경계를 가져온다
     */		
	@RequestMapping(value="/search/searchIndexMapArea.do")
	public void getIndexMapArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectKey = request.getParameter("selectKey");
		
		byte[] resBytes = null;
		
		// 보안 코딩
		selectKey = BlackList.getStrCnvrXss(selectKey);
				
		try{
        	this.getIndexMapAreaGeometry(selectKey);
        	resBytes = this.getXML_indexMap();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }

        if(resBytes == null){
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
	
    /**
     * 도엽 조회
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
    @RequestMapping(value="/search/selectIndexMap5000List.do")
    public void selectIndexMap5000List(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	String minx = request.getParameter("minx");
    	String miny = request.getParameter("miny");
    	String maxx = request.getParameter("maxx");
    	String maxy = request.getParameter("maxy");
		byte[] resBytes = null;
		
		try{
        	this.getIndexMapGeometry(minx, miny, maxx, maxy);
        	resBytes = this.getXML_indexMap();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }

        if(resBytes == null){
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
	
	private void getIndexMapGeometry(String minx, String miny, String maxx, String maxy){
		List<IndexMapVO> bndry = null;
		try{
			bndry = this.addressSearchService.selectIndexMap5000List(minx, miny, maxx, maxy);
			
			if (bndry != null){
                WKBReader wkbReader = new WKBReader();
                for (int i = 0; i < bndry.size(); i++){
                	IndexMapVO vo = (IndexMapVO)bndry.get(i);
                	vo.setGeom(wkbReader.read(vo.getWkb_geometry()));
                }
			}
            this.indexMapList = bndry;
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}	
    
	private void getIndexMapAreaGeometry(String selectKey){
		List<IndexMapVO> bndry = null;
		try{
			bndry = this.addressSearchService.getIndexMapArea(selectKey);
			
			if (bndry != null){
                WKBReader wkbReader = new WKBReader();
                for (int i = 0; i < bndry.size(); i++){
                	IndexMapVO vo = (IndexMapVO)bndry.get(i);
                	vo.setGeom(wkbReader.read(vo.getWkb_geometry()));
                }
			}
            this.indexMapList = bndry;
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}	
	
    private byte[] getXML_indexMap(){
    	StringBuffer kmlBuffer = new StringBuffer();
        kmlBuffer.append("<?xml version='1.0' encoding='UTF-8'?>\n");  
        kmlBuffer.append("<Document>");
        
        kmlBuffer.append(this.getGeometryXML_indexMap());        
        
        kmlBuffer.append("</Document>");   
		System.out.println("------------------------------------------");
		System.out.println(kmlBuffer.toString());
		System.out.println("------------------------------------------");
        byte[] resBytes = null;
        try{
        	resBytes = kmlBuffer.toString().getBytes("UTF-8");
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
		return resBytes;
	}

	private String getGeometryXML_indexMap() {
		StringBuffer geomBuffer = new StringBuffer();
		if (this.indexMapList != null){
			for (int i = 0; i < this.indexMapList.size(); i++){
				IndexMapVO vo = (IndexMapVO)this.indexMapList.get(i);
				if (vo.getGeom() instanceof MultiPolygon){
					geomBuffer.append("<MultiGeometry>");
					MultiPolygon mpoly = (MultiPolygon)vo.getGeom();
			        for (int j = 0; j < mpoly.getNumGeometries(); j++) {
			        	Polygon polygon = (Polygon) mpoly.getGeometryN(j);
			        	geomBuffer.append("<Polygon>");
			        	geomBuffer.append(this.getGeomPolygonPlacemark(j, polygon));
			        	geomBuffer.append("</Polygon>");
			        }
			        geomBuffer.append("</MultiGeometry>");
				}else{
					Polygon polygon = (Polygon) vo.getGeom();
					geomBuffer.append("<Polygon>");
					geomBuffer.append("<extrude>1</extrude><tessellate>1</tessellate><altitudeMode>absolute</altitudeMode>");
					geomBuffer.append(this.getGeomPolygonPlacemark(i, polygon));
					geomBuffer.append("</Polygon>");
				}
			}

		}

		return geomBuffer.toString();
	}
	
	private void getAreaGeometry(String admcd){
		List<AddressSearchVO> bndry = null;
		try{
			bndry = this.addressSearchService.getAddressArea(admcd);
			
			if (bndry != null){
                WKBReader wkbReader = new WKBReader();
                for (int i = 0; i < bndry.size(); i++){
                	AddressSearchVO vo = (AddressSearchVO)bndry.get(i);
                	vo.setGeom(wkbReader.read(vo.getWkb_geometry()));
                }
			}
            this.list = bndry;
		}catch (Exception e){
			System.out.println(e.getMessage());
		} 
	}	
	
    private byte[] getXML(){
    	StringBuffer kmlBuffer = new StringBuffer();
        kmlBuffer.append("<?xml version='1.0' encoding='UTF-8'?>\n");  
        kmlBuffer.append("<Document>");
        
        kmlBuffer.append(this.getGeometryXML());        
        
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

	private String getGeometryXML() {
		StringBuffer geomBuffer = new StringBuffer();
		if (this.list != null){
			for (int i = 0; i < this.list.size(); i++){
				AddressSearchVO vo = (AddressSearchVO)this.list.get(i);
				if (vo.getGeom() instanceof MultiPolygon){
					geomBuffer.append("<MultiGeometry>");
					MultiPolygon mpoly = (MultiPolygon)vo.getGeom();
			        for (int j = 0; j < mpoly.getNumGeometries(); j++) {
			        	Polygon polygon = (Polygon) mpoly.getGeometryN(j);
			        	geomBuffer.append("<Polygon>");
			        	geomBuffer.append(this.getGeomPolygonPlacemark(j, polygon));
			        	geomBuffer.append("</Polygon>");
			        }
			        geomBuffer.append("</MultiGeometry>");
				}else{
					Polygon polygon = (Polygon) vo.getGeom();
					geomBuffer.append("<Polygon>");
					geomBuffer.append("<extrude>1</extrude><tessellate>1</tessellate><altitudeMode>absolute</altitudeMode>");
					geomBuffer.append(this.getGeomPolygonPlacemark(i, polygon));
					geomBuffer.append("</Polygon>");
				}
			}

		}

		return geomBuffer.toString();
	}
    
	public String getGeomPolygonPlacemark(int id, Polygon obj)
	{		
		StringBuffer dta = new StringBuffer();
		Polygon poly = null;
		
		Coordinate[] points = null;
		
		if (obj != null)
		{
			dta.append("<outerBoundaryIs><LinearRing><coordinates>");
			
			poly =  (Polygon) obj;
			points = poly.getExteriorRing().getCoordinates();
			
	        if(points.length>0)
	        {	        	
	            for (int j = 0; j < points.length; j++)
	            {
	            	Coordinate point = (Coordinate) points[j];
	            	double x = point.x;
	                double y = point.y;
	                //double z = point.z;
	                
	                //dta.append(x+","+y+","+z+"\n ");
	                if (j == (points.length-1)){
	                	dta.append(x+","+y);
	                }else{
	                	dta.append(x+","+y+" ");
	                }
	                
	            }
	        }
	        dta.append("</coordinates></LinearRing></outerBoundaryIs>");
		}
		return dta.toString();
	}
}
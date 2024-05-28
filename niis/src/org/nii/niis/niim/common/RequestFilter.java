package org.nii.niis.niim.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.nii.niis.niim.util.JSONUtil;

public class RequestFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		RequestWrapper requestWrapper = null;
		
		//교차 프레임 스크립팅 방어 누락
		httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
		
		//HTTP Strict-Transport-Security 헤더 누락
		httpServletResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
		
		//Missing "X-Content-Type-Options" header
		httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
		
		//Missing "X-XSS-Protection" header
		httpServletResponse.setHeader("X-XSS-Protection", "1");
		
		
		//공급시스템
		if(httpServletRequest.getRequestURI().indexOf("/niim/") > -1){
			
			requestWrapper = new RequestWrapper(httpServletRequest);
			
			requestWrapper.setAttribute("usrSysCde", "1");
			requestWrapper.setAttribute("_role", requestWrapper.getParameter("connUserAuth"));
			
			if(httpServletRequest.getHeader("content-type").equals("application/json")){
				try{
					Map<String, Object> map = JSONUtil.jsonToMap(getBody(requestWrapper.getInputStream()));
					//Iterator<String> itr = map.keySet().iterator();while(itr.hasNext()){String key = itr.next();System.out.println("@@@@doFilter key["+key+"], value["+map.get(key)+"]");}
					requestWrapper.setAttribute("_role", map.get("connUserAuth"));
				}catch (ParseException e){
					e.printStackTrace();
				}
			}
			
			chain.doFilter(requestWrapper, response);
		}
		//관리시스템
		else{
			request.setAttribute("usrSysCde", "0");
			request.setAttribute("_role", httpServletRequest.getSession().getAttribute("sUserAuth"));
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}
	
	public static String getBody(ServletInputStream sis) throws IOException {
		 
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            if (sis != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(sis, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        return body;
    }

}

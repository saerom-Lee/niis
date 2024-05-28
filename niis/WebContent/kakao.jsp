<%@ page contentType="appliccaton/json; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false"%>
<%@ page import="java.net.*,java.io.*"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	StringBuffer sb = new StringBuffer();
	try {
		String p = request.getParameter("page");
		//String p = "1";
		if(p == null){
			p = "1";
		}
		else if(!p.matches("\\d+")){
			//숫자가 아닐 경우 1
			p = "1";
		}
		
		String type = request.getParameter("type");
		
		String query = request.getParameter("query");
		String queryEncode = URLEncoder.encode(query,"utf-8");
		
		URL url = new URL("https://dapi.kakao.com/v2/local/search/"+type+".json?page="+p+"&size=5&query="+queryEncode);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod(request.getMethod());
		con.setRequestProperty("Authorization", "KakaoAK 6e61ec6b93da2b97946f9c2969044a39");
		if (request.getContentType() != null) {
			con.setRequestProperty("Content-Type", request.getContentType());
		}

		con.connect();
		InputStreamReader in = new InputStreamReader(con.getInputStream(),"utf-8");
		BufferedReader buff = new BufferedReader(in);
		
		int c;
		while ((c = buff.read()) != -1) {
			sb.append( (char)c ) ;
		}
		
		con.disconnect();
		in.close();
		buff.close();
		
	} catch (Exception e) {
		response.setStatus(500);
	}
%>
<%-- ajax 에서 넘겨준 callback 함수 파라메터 가져오기 --%>
${param.callback}(<%=sb.toString()%>);
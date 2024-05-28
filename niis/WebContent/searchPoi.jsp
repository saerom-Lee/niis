<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false"%>
<%@ page import="java.net.*,java.io.*"%>
<%
	StringBuffer sb = new StringBuffer();
	try {
		String p1 = request.getParameter("start");
		String p2 = request.getParameter("page");
		String p = "1";
		if(p1 == null || p1.equals("")){
			p = p2;
		}
		else if (p2 == null || p2.equals("")){
			p = p1;
		}
		else{
			p = "1";
		}

		String query = new String(request.getParameter("query").getBytes("ISO-8859-1"), "UTF-8");
		String queryEncode = URLEncoder.encode(query,"utf-8");
		String type = request.getParameter("type");

		URL url = new URL("http://sd.ngii.go.kr:9000/sub/keywordSearch_utmk.jsp?page="+p+"&query="+queryEncode+"&type="+type);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod(request.getMethod());
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
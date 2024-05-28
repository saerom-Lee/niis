<a href="javascript:history.back();">back</a><br /><br />

<%
request.setCharacterEncoding("UTF-8");
java.util.Enumeration names = request.getParameterNames();

while (names.hasMoreElements()) {
    String name = (String)names.nextElement();
    String values[] = request.getParameterValues(name);

    if (values != null) {
        for (int i=0; i< values.length; i++) {
            out.println ( i + ". " + name + " = " + values[i] + "<br />");
        }
    }
}
%>
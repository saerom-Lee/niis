<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		
	});
	
</script>
<ul>
	<!-- #pageStandBy -->
	<li>
		<a href="#mainDiv" class="pageMove" menuId="/apply/waitApply.do">대기<span style="color:#87ade5">${map.applyWait }</span></a>
	</li>
	<!-- #pageApproval -->
	<li>
		<a href="#mainDiv" class="pageMove" menuId="/apply/appApply.do">승인<span style="color:#b6d657">${map.applyApp }</span></a>
	</li>
	<!-- #pageReturn -->
	<li class="last">
		<a href="#mainDiv" class="pageMove" menuId="/apply/rejApply.do">반려<span style="color:#f29d6e">${map.applyRej }</span></a>
	</li>
</ul>
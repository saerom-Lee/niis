<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	
	$(document).ready(function(){
		
	});
	
</script>
<c:choose>
	
	<c:when test="${fn:length(list) > 0 }" >
		<c:forEach items="${list }" var="list">
<li style="width:215px;height:21px;">
	<a href="#notice" class="btnPageUp" onclick="ajaxCall('/board/board.do?boardCate=101&boardLink=${list.boardSeq }', '', 'notice');">
		<span style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;float:left;width:90%;">- ${list.title }</span>
			<c:if test="${list.newYn == 'Y' }">
		<img src="/niis/images/common/ic_new.gif" alt="new" title="new" />
			</c:if>
	</a>
</li> 
		</c:forEach>
		<c:forEach begin="1" end="${3 - fn:length(list) }">
<li style="width:215px;height:21px;"><a href="#none" ></a></li>
		</c:forEach>
	</c:when>
	
	<c:otherwise>
<li style="width:215px;height:21px;"><a href="#none" >- 등록된 공지사항이 없습니다.</a></li>
<li style="width:215px;height:21px;"><a href="#none" ></a></li>
<li style="width:215px;height:21px;"><a href="#none" ></a></li>
	</c:otherwise>
	
</c:choose>
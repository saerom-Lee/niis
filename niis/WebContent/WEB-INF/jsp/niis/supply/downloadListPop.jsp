<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
a.btnDownload {
	display:inline-block;
	padding:5px 10px;
	color:#fff;
	background:#2a8095;
}
</style>
<script type="text/javascript">
	
	function appImgDownload(supIdn, imageCde, group){
		//var url = "/niis/supply/downloadPop.do?supIdn=" + supIdn;
		//showModalDialog(url, self, "width=400px,height=320px,resizable=no");
		//window.open(url, self, "width=400px,height=320px,resizable=no");
		
		//$("#commonPop").css("display", "none");
		$("#commonPop").css("z-index", $("#downloadPop").css("z-index")+1);
		
		var url = "/supply/downloadPop.do";
		var data = {
				"supIdn"   : supIdn,
				"imageCde" : imageCde,
				"group"    : group
		}
		
		//console.log(data);
		ajaxCallPop(url, data, "", 460);
	}
	
	function appMetaDownload(supIdn){
		//alert("메타데이터 다운로드");
		
		$.ajax({
			type	 : "GET",
			async 	 : true,
			url 	 : "/niis/supply/metaFileCheck.do",
			data	 : {'supIdn' : supIdn},
			dataType : "json",
			success: function(result){
				if(result.status){
					location.href = "/niis/supply/downloadMetaFile.do?&supIdn=" + supIdn;	
				}
				else{
					alert("메타데이터 파일이 없습니다.");
				}
				
			},
			error: function() {
				alert("메타데이터 파일이 없습니다.");
			}	
		});
		
	}
	
	function appImgAirDownload(supIdn, imageCde, group){
		
		if(group == "meta"){
			//alert("메타데이터 다운로드");
			location.href = "/niis/supply/downloadMetaFile.do?&supIdn=" + supIdn;
		}
		else{
			$.ajax({
				type	 : "POST",
				async 	 : true,
				url 	 : "/niis/download/getFileAirList.do",
				data	 : {'supIdn' : supIdn},
				dataType : "json",
				success: function(result){
					
					var list = result.list;
					
					var param = {};
					for(var i=0; i<list.length; i++){
						if(param[list[i].storageCde] == undefined){
							param[list[i].storageCde] = list[i].fileIdentifier;
						}
						else{
							param[list[i].storageCde] += ","+list[i].fileIdentifier;	
						}
					}
					
					var status = "scrollbars=no,width=700,height=320,location=no, resizable=yes";
					window.open("", "airDownload", status);
					
					var formData = document.downloadFormAir;
					formData.target = "airDownload";
					formData.action = "http://10.98.25.14:9000/iim_files/dext/iim/pdt/airFileDownload.do";
					formData.AIR001.value = param['AIR001'];
					formData.method = "get";
					formData.submit();
					
				},
				error: function() {
					console.log("리스트 로딩 실패");
				}	
			});
		}
	}
	
	function appImgOrtDownload(supIdn, imageCde, group){
		if(group == "meta"){
			//alert("메타데이터 다운로드");
			location.href = "/niis/supply/downloadMetaFile.do?&supIdn=" + supIdn;
		}
		else{
			$.ajax({
				type	 : "POST",
				async 	 : true,
				url 	 : "/niis/download/getFileOrtList.do",
				data	 : {'supIdn' : supIdn},
				dataType : "json",
				success: function(result){
					
					var list = result.list;
					
					var param = {};
					for(var i=0; i<list.length; i++){
						if(param[list[i].storageCde] == undefined){
							param[list[i].storageCde] = list[i].fileIdentifier;
						}
						else{
							param[list[i].storageCde] += ","+list[i].fileIdentifier;	
						}
					}
					
					var status = "scrollbars=no,width=700,height=320,location=no, resizable=yes";
					window.open("", "ortDownload", status);
					
					var formData = document.downloadFormOrt;
					
					while(formData.firstChild)  {	// 자식 노드 삭제
						formData.removeChild(formData.firstChild);
					}
					
					formData.target = "ortDownload";
					formData.action = "http://10.98.25.14:9000/iim_files/dext/iim/pdt/airFileDownload.do";
					var keys = Object.keys(param);
					
					for(var i=0; i<keys.length; i++){
						if(keys[i] == "ORT001"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "ORT001");
							input.setAttribute("value", param['ORT001']);
							formData.appendChild(input);
						}
						if(keys[i] == "ORT002"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "ORT002");
							input.setAttribute("value", param['ORT002']);
							formData.appendChild(input);
						}
						if(keys[i] == "ORT003"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "ORT003");
							input.setAttribute("value", param['ORT003']);
							formData.appendChild(input);
						}
						if(keys[i] == "ORT004"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "ORT004");
							input.setAttribute("value", param['ORT004']);
							formData.appendChild(input);
						}
						if(keys[i] == "ORT005"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "ORT005");
							input.setAttribute("value", param['ORT005']);
							formData.appendChild(input);
						}
					}
					
					console.log(formData);
					formData.method = "get";
					formData.submit();
					
				},
				error: function() {
					console.log("리스트 로딩 실패");
				}	
			});
		}
		
	}
	
	function appImgDemDownload(supIdn, imageCde, group){
		if(group == "meta"){
			//alert("메타데이터 다운로드");
			location.href = "/niis/supply/downloadMetaFile.do?&supIdn=" + supIdn;
		}
		else{
			$.ajax({
				type	 : "POST",
				async 	 : true,
				url 	 : "/niis/download/getFileDemList.do",
				data	 : {'supIdn' : supIdn},
				dataType : "json",
				success: function(result){
					
					var list = result.list;
					
					var param = {};
					for(var i=0; i<list.length; i++){
						if(param[list[i].storageCde] == undefined){
							param[list[i].storageCde] = list[i].fileIdentifier;
						}
						else{
							param[list[i].storageCde] += ","+list[i].fileIdentifier;	
						}
					}
					
					var status = "scrollbars=no,width=700,height=320,location=no, resizable=yes";
					window.open("", "demDownload", status);
					
					var formData = document.downloadFormDem;
					
					while(formData.firstChild)  {	// 자식 노드 삭제
						formData.removeChild(formData.firstChild);
					}
					
					formData.target = "demDownload";
					formData.action = "http://10.98.25.14:9000/iim_files/dext/iim/pdt/airFileDownload.do";
					var keys = Object.keys(param);
					
					for(var i=0; i<keys.length; i++){
						if(keys[i] == "DEM001"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "DEM001");
							input.setAttribute("value", param['DEM001']);
							formData.appendChild(input);
						}
						if(keys[i] == "DEM002"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "DEM002");
							input.setAttribute("value", param['DEM002']);
							formData.appendChild(input);
						}
						if(keys[i] == "DEM003"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "DEM003");
							input.setAttribute("value", param['DEM003']);
							formData.appendChild(input);
						}
						if(keys[i] == "DEM004"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "DEM004");
							input.setAttribute("value", param['DEM004']);
							formData.appendChild(input);
						}
						if(keys[i] == "DEM005"){
							var input = document.createElement('input');
							input.setAttribute("type", "hidden");
							input.setAttribute("name", "DEM005");
							input.setAttribute("value", param['DEM005']);
							formData.appendChild(input);
						}
					}
					
					console.log(formData);
					formData.method = "get";
					formData.submit();
					
				},
				error: function() {
					console.log("리스트 로딩 실패");
				}	
			});
		}
		
	}
	
	
	function appImgMapDownload(supIdn, imageCde, group){
		
		$.ajax({
			type	 : "POST",
			async 	 : true,
			url 	 : "/niis/download/getFileMapList.do",
			data	 : {'supIdn' : supIdn},
			dataType : "json",
			success: function(result){
				
				var list = result.list;
				
				var param = {};
				for(var i=0; i<list.length; i++){
					console.log(list[i]);
					
					if(param['mapShtNo'] == undefined){
						param['mapShtNo'] = list[i].mapShtNo;
						param['mapSerNo'] = list[i].mapSerNo;						
						param['mapHistoryNo'] = list[i].mapHistoryNo;
					}
					else{
						param['mapShtNo'] += ","+list[i].mapShtNo;
						param['mapSerNo'] += ","+list[i].mapSerNo;						
						param['mapHistoryNo'] += ","+list[i].mapHistoryNo;	
					}
				}
				
				var status = "scrollbars=no,width=700,height=320,location=no, resizable=yes";
				window.open("", "mapDownload", status);
				
				var formData = document.downloadFormDem;
				
				while(formData.firstChild)  {	// 자식 노드 삭제
					formData.removeChild(formData.firstChild);
				}
				
				formData.target = "mapDownload";
				formData.action = "http://10.98.25.14:9000/iim_files/dext/pms/mapmngr/mapinfomngr/mapSelectDownPop.do";
				var keys = Object.keys(param);
				
				//&fileNameCheck=N
				for(var i=0; i<keys.length; i++){
					if(keys[i] == "mapShtNo"){
						var input = document.createElement('input');
						input.setAttribute("type", "hidden");
						input.setAttribute("name", "mapShtNo");
						input.setAttribute("value", param['mapShtNo']);
						formData.appendChild(input);
					}
					if(keys[i] == "mapSerNo"){
						var input = document.createElement('input');
						input.setAttribute("type", "hidden");
						input.setAttribute("name", "mapSerNo");
						input.setAttribute("value", param['mapSerNo']);
						formData.appendChild(input);
					}
					if(keys[i] == "mapHistoryNo"){
						var input = document.createElement('input');
						input.setAttribute("type", "hidden");
						input.setAttribute("name", "mapHistoryNo");
						input.setAttribute("value", param['mapHistoryNo']);
						formData.appendChild(input);
					}
					
					
					
				}
				var input2 = document.createElement('input');
				input2.setAttribute("type", "hidden");
				input2.setAttribute("name", "fileNameCheck");
				input2.setAttribute("value", "N");
				formData.appendChild(input2);
				
				
				formData.method = "get";
				formData.submit();
				
			},
			error: function() {
				console.log("리스트 로딩 실패");
			}	
		});
	}
	
	function appImgATDownload(supIdn, imageCde, group){
		
		$.ajax({
			type	 : "POST",
			async 	 : true,
			url 	 : "/niis/download/getFileAtList.do",
			data	 : {'supIdn' : supIdn},
			dataType : "json",
			success: function(result){
				
				var list = result.list;
				
				var param = {};
				
				for(var i=0; i<list.length; i++){
					if(param['zoneCode'] == undefined){
						param['zoneCode'] = list[i].zoneCode;
					}
					else{
						param['zoneCode'] += ","+list[i].zoneCode;	
					}
				}
				
				var status = "scrollbars=no,width=700,height=320,location=no, resizable=yes";
				window.open("", "atDownload", status);
				
				var formData = document.downloadFormAir;
				
				while(formData.firstChild)  {	// 자식 노드 삭제
					formData.removeChild(formData.firstChild);
				}
				
				formData.target = "atDownload";
				formData.action = "http://10.98.25.14:9000/iim_files/dext/iim/atpdt/fileDownload.do";
				
				var input = document.createElement('input');
				input.setAttribute("type", "hidden");
				input.setAttribute("name", "zoneCode");
				input.setAttribute("value", param['zoneCode']);
				formData.appendChild(input);
				
				formData.method = "get";
				formData.submit();
				
			},
			error: function() {
				console.log("리스트 로딩 실패");
			}	
		});
		
	}
	
	function appImgMapMetaDownload(supIdn, imageCde, group){
		
		var url = "/supply/downloadMapMetaListPop.do";
		var data = {
						"supIdn" 	: supIdn
					,	"imageCde" 	: imageCde
					,	"group"		: group
		}
		ajaxCallPop(url, data, "mapMetaDownloadPop", 600);
		
		
	}
</script>
<form name="downloadFormAir">
	<input type="hidden" name="AIR001" value="" />
</form>
<form name="downloadFormOrt">
</form>
<form name="downloadFormDem">
</form>
<form name="downloadFormAt">
</form>
<form name="downloadFormMap">
</form>
<div class="tit">
	<h3>다운로드 선택</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="" /></a>
</div>
<div id="downloadList" style="max-height:600px; background:#fff; overflow-y:auto">
	<!-- .tableStyle -->
	<div class="tableStyle" style="padding:20px;">
		<table>
			<colgroup>
				<col width="15%" />
				<col width="10%" />
				<col width="60%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th>종류</th>
					<th>순번</th>
					<th>정보</th>
					<th>다운</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${metaList }" var="list">
				<tr>
					<td style="text-align:center">메타데이터</td>
					<td style="text-align:center"></td>
					<td>메타데이터</td>
					<td style="text-align:center"><a href="#none" class="btnDownload" onclick="appMetaDownload('${list.supIdn }');">${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
				</tr>
			</c:forEach>
			<c:forEach items="${airList }" var="list">
				<tr>
					<td style="text-align:center">항공사진</td>
					<td style="text-align:center">${list.groupNum }</td>
					<td>${list.zoneNam } <c:if test="${list.groupNum ne 'meta' }">${list.phCourse }코스 ${list.phNum }</c:if><c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
					<td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgAirDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
				</tr>
			</c:forEach>
			<c:forEach items="${demList }" var="list">
				<tr>
					<td style="text-align:center">수치표고</td>
					<td style="text-align:center">${list.groupNum }</td>
					<td>${list.zoneNam } <c:if test="${list.groupNum ne 'meta' }">${list.map5000Num }도엽 ${list.gridInt }</c:if><c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
					<td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgDemDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
				</tr>
			</c:forEach>
			<c:forEach items="${ortList }" var="list">
				<tr>
					<td style="text-align:center">정사영상</td>
					<td style="text-align:center">${list.groupNum }</td>
					<td>${list.zoneNam } <c:if test="${list.groupNum ne 'meta' }">${list.map5000Num }도엽 ${list.gtypDst }</c:if><c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
					<td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgOrtDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
				</tr>
			</c:forEach>
			<c:forEach items="${atList }" var="list">
				<tr>
					<td style="text-align:center">AT성과</td>
					<td style="text-align:center">${list.groupNum }</td>
					<td>${list.zoneNam } <c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
					<td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgATDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
				</tr>
			</c:forEach>
			<c:forEach items="${mapList }" var="list">
				<c:if test="${list.groupNum ne 'meta' }">
					<tr>
						<td style="text-align:center">수치지형도</td>
						<td style="text-align:center">${list.groupNum }</td>
						<td>${list.zoneNam } <c:if test="${list.groupNum ne 'meta' }">${list.mapKindNm} (${list.scaleNm }) ${list.mapShtNm}도엽</c:if><c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
						<td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgMapDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td> 
					</tr>
				</c:if>
				<c:if test="${list.groupNum eq 'meta' }">
					<tr>
						<td style="text-align:center">수치지형도</td>
						<td style="text-align:center">${list.groupNum }</td>
						<td>${list.zoneNam } <c:if test="${list.groupNum ne 'meta' }">${list.mapKindNm} (${list.scaleNm }) ${list.mapShtNm}도엽</c:if><c:if test="${list.cnt != null && list.cnt > 1 }"> 외 ${list.cnt - 1 }건</c:if></td>
						<c:if test="${list.groupNum eq 'meta' }"><td style="text-align:center"><a href="#none" <c:if test="${list.downloadApp eq 'Y' }">class="btnDownload" onclick="appImgMapMetaDownload('${list.supIdn }', '${list.imageCde }', '${list.groupNum }');"</c:if>>${list.downloadApp eq 'Y' ? '다운로드' : '완료' }</a></td></c:if> 
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /.tableStyle -->
</div>
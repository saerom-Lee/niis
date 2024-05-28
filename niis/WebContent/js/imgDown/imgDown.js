var fileDownState = null;
var fileDownTreeType = '';

function applicationList(state, treeType) {
	if (state == 1) {
		var nodeCntSum = 0;	//전체 신청 건수
		var noChildren = 0;	//영상 신청 건수
		var airCnt = 0;
		var ortCnt = 0;
		var demCnt = 0;
		var atCnt = 0;
		var suchiCnt = 0;
		var reqName = '';

		$("#combineTreeMenu > ul li[id$='TreeRoot']").each(function() {
			try {
				var selectedNodes = $(this).fancytree("getTree").getSelectedNodes();
				
				for (var idx = 0; idx < selectedNodes.length; idx++) {
					var childrenNode = selectedNodes[idx];
					var imageCde = childrenNode.data.imageCde;
					
					if (!childrenNode.hasChildren() && childrenNode.extraClasses != undefined) { 
						noChildren += 1;

						if (imageCde == 'PDT001') {
							airCnt += 1;
						} else if (imageCde == 'PDT004') {
							ortCnt += 1;
						} else if (imageCde == 'PDT002') {
							demCnt += 1;
						} else if (imageCde == 'atCde') {
							atCnt += 1;
						}

					} else {
						if (imageCde == 'PDT001') {
							airCnt += childrenNode.children.length;
						} else if (imageCde == 'PDT004') {
							ortCnt += childrenNode.children.length;
						} else if (imageCde == 'PDT002') {
							demCnt += childrenNode.children.length;
						} else if (imageCde == 'atCde') {
							atCnt += childrenNode.children.length;
						}
					}
				}
				nodeCntSum += $(this).fancytree("getTree").getSelectedNodes().length;

			} catch (e) {
				nodeCntSum += 0;
			}
		});
		
		$("#combineTreeMenu > ul li[id$='TR']").each(function() {
			try {
				var selectedNodes = $(this).fancytree("getTree").getSelectedNodes();
					
				for (var idx = 0; idx < selectedNodes.length; idx++) {
					var childrenNode = selectedNodes[idx];
					if (!childrenNode.hasChildren() && childrenNode.extraClasses != undefined) {
						noChildren += 1;
			        	suchiCnt += 1;
			        }
				}
				nodeCntSum += $(this).fancytree("getTree").getSelectedNodes().length;

			} catch (e) {
				nodeCntSum += 0;
			}
		});

		if (noChildren > 50) {
			alert("총 50건까지 신청할 수 있습니다.");
			return;
		}

		if (noChildren == 0) {
			alert("선택된 성과가 없습니다.");
			return;
		}
	}

	fileDownState = state;
	fileDownTreeType = treeType;
	/*activeNodes = $("#"+fileDownTreeType).fancytree("getTree").getActiveNode();
	alert(activeNodes.length);*/

	if (airCnt > 0) 											reqName += "항공사진 " + airCnt + "건";
	if (airCnt > 0 && ortCnt > 0) 								reqName += ", ";
	if (ortCnt > 0) 											reqName += "정사영상 " + ortCnt + "건";
	if (airCnt + ortCnt > 0 && demCnt > 0) 						reqName += ", ";
	if (demCnt > 0) 											reqName += "수치표고 " + demCnt + "건";
	if (airCnt + ortCnt + demCnt > 0 && atCnt > 0) 				reqName += ", ";
	if (atCnt > 0) 												reqName += "AT성과 " + atCnt + "건";
	if (airCnt + ortCnt + demCnt + atCnt > 0 && suchiCnt > 0) 	reqName += ", ";
	if (suchiCnt > 0) 											reqName += "수치지형도 " + suchiCnt + "건";

	var data = {
		"reqName": reqName
	};

	ajaxCallPop("/apply/regApplyPopTree.do", data, "totalApplication", "700");
}

/**
 * Active Item 반환
 */
function getSelectedDataObj() {
	//이미지 타입, 사업지구 코드, 코스번호, 사진번호, 파일(경로포함)
	var activeNodes;
	var fileData = new Array();
	if (fileDownTreeType != '') {
		activeNodes = $("#" + fileDownTreeType).fancytree("getTree").getActiveNode();
		var nodeData = new Array();
		if (!activeNodes.hasChildren() && activeNodes.extraClasses != undefined && activeNodes.extraClasses.indexOf('fancytree-disable') == -1) {
			if (fileDownTreeType == "airTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.phCourse;
				nodeData[3] = activeNodes.data.phNum;
				//            	nodeData[4] = activeNodes.data.fileNam;
				//nodeData[4] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.zoneCode.substring(7,11)+"\\"+activeNodes.data.phCourse+"\\"+activeNodes.data.fileNam;
				nodeData[4] = activeNodes.data.filePath;
			} else if(fileDownTreeType == "airLibTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.phCourse;
				nodeData[3] = activeNodes.data.phNum;
				//            	nodeData[4] = activeNodes.data.fileNam;
				//nodeData[4] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.zoneCode.substring(7,11)+"\\"+activeNodes.data.phCourse+"\\"+activeNodes.data.fileNam;
				nodeData[4] = activeNodes.data.filePath;
			} else if (fileDownTreeType == "ortTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.map5000Num;
				nodeData[3] = activeNodes.data.gtypDst;
				//nodeData[3] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.gtypDst+"\\"+activeNodes.data.map5000Num+"\\"+activeNodes.data.fileNam;
				nodeData[4] = activeNodes.data.filePath;
			} else if (fileDownTreeType == "demTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.map5000Num;
				nodeData[3] = activeNodes.data.gridInt;
				//nodeData[3] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.gtypDst+"\\"+activeNodes.data.map5000Num+"\\"+activeNodes.data.fileNam;
				nodeData[4] = activeNodes.data.filePath;
			} else if (fileDownTreeType == "lidTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.lidarIdn;
				//nodeData[3] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.gtypDst+"\\"+activeNodes.data.map5000Num+"\\"+activeNodes.data.fileNam;
				nodeData[3] = activeNodes.data.filePath;
			} else if (fileDownTreeType == "nirTreeRoot")  {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.phCourse;
				nodeData[3] = activeNodes.data.phNum;
				//nodeData[4] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.sceneyear+"\\"+activeNodes.data.zoneCode.substring(7,11)+"\\"+activeNodes.data.phCourse+"\\"+activeNodes.data.fileNam;
				nodeData[4] = activeNodes.data.filePath;
			} else if (fileDownTreeType == "tdsTreeRoot") {
				nodeData[0] = activeNodes.data.imageCde;
				nodeData[1] = activeNodes.data.zoneCode;
				nodeData[2] = activeNodes.data.tdsIdn;
				//nodeData[3] = activeNodes.data.stoDrv+":\\"+activeNodes.data.folderNam+"\\"+activeNodes.data.fileNam;
				nodeData[3] = activeNodes.data.filePath;
			}

			fileData.push(nodeData);
		}
	}
	return fileData;
}

/**
 * 통합검색 결과 데이터 반환
 * imgTypeObj@ 0:항공사진, 1:정사영상, 2:수치표고, 3:AT성과, 4:수치지형도
 */
function getSelectedData() {
	var fileList = [];
	var imgTypeObj = getSelecteImgType();
	if (imgTypeObj.indexOf(",") > 0) {
		var temp = imgTypeObj.split(",");
		for (var i = 0; i < temp.length; i++) {
			var list = getSelectDataType(temp[i]);
			if (list != null) {
				fileList = fileList.concat(list);
			}
		}
	} else {
		fileList = getSelectDataType(imgTypeObj);
	}
	return fileList;
}

/**
 * 영상종류 선택 확인
 * @param type
 */
function getSelectDataType(type) {
	var fileObj = null;
	switch (type) {
		case "0":
			fileObj = getAirCheckedDataObj();
			break;
		case "1":
			fileObj = getOrtCheckedDataObj();
			break;
		case "2":
			fileObj = getDemCheckedDataObj();
			break;
		case "3":
			fileObj = getAtCheckedDataObj();
			break;
		case "4":
			fileObj = getSuchiCheckedDataObj();
			break;
	}

	return fileObj;
}

/**
 * 항공사진 선택 객체 반환
 */
function getAirCheckedDataObj() {
	var fileData = new Array();
	var selectedNodes = $("#airTreeRoot").fancytree("getTree").getSelectedNodes();
	if (selectedNodes != null) {

		for (var idx = 0; idx < selectedNodes.length; idx++) {
			var nodeData = new Array();
			var node = selectedNodes[idx];
			if (!node.hasChildren() && node.extraClasses != undefined && node.extraClasses.indexOf('fancytree-disable') == -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.phCourse;
				nodeData[3] = node.data.phNum;
				//nodeData[4] = node.data.stoDrv+":\\"+node.data.folderNam+"\\"+node.data.sceneyear+"\\"+node.data.zoneCode.substring(7,11)+"\\"+node.data.phCourse+"\\"+node.data.fileNam;
				nodeData[4] = node.data.filePath;


				fileData.push(nodeData);
			}

			//아직 로딩 되지 않은 노드 정보 추가
			if (null != node.extraClasses && "" != node.extraClasses && node.extraClasses.indexOf("air_") > -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
//				nodeData[2] = node.data.phCourse;
				nodeData[2] = node.data.phCourse + "|" + node.data.securityCde;
				nodeData[3] = "loadedYet";
				nodeData[4] = "loadedYet";

				fileData.push(nodeData);
			}
		}
	}

	var selectedLibNodes = $("#airLibTreeRoot").fancytree("getTree").getSelectedNodes();

	if (selectedLibNodes != null) {

		for (var idx = 0; idx < selectedLibNodes.length; idx++) {
			var nodeData = new Array();
			var node = selectedLibNodes[idx];
			if (!node.hasChildren() && node.extraClasses != undefined && node.extraClasses.indexOf('fancytree-disable') == -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.phCourse;
				nodeData[3] = node.data.phNum;
				//nodeData[4] = node.data.stoDrv+":\\"+node.data.folderNam+"\\"+node.data.sceneyear+"\\"+node.data.zoneCode.substring(7,11)+"\\"+node.data.phCourse+"\\"+node.data.fileNam;
				nodeData[4] = node.data.filePath;


				fileData.push(nodeData);
			}

			//아직 로딩 되지 않은 노드 정보 추가
			if (null != node.extraClasses && "" != node.extraClasses && node.extraClasses.indexOf("air_") > -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
//				nodeData[2] = node.data.phCourse;
				nodeData[2] = node.data.phCourse + "|" + node.data.securityCde;
				nodeData[3] = "loadedYet";
				nodeData[4] = "loadedYet";

				fileData.push(nodeData);
			}
		}
	}

	return fileData;
}


/**
 * 정사영상 선택 객체 반환
 */
function getOrtCheckedDataObj() {
	var fileData = new Array();
	var selectedNodes = $("#ortTreeRoot").fancytree("getTree").getSelectedNodes();

	if (selectedNodes != null) {
		for (var idx = 0; idx < selectedNodes.length; idx++) {
			var nodeData = new Array();
			var node = selectedNodes[idx];
			if (!node.hasChildren() && node.extraClasses != undefined && node.extraClasses.indexOf('fancytree-disable') == -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.map5000Num;
				nodeData[3] = node.data.gtypDst;
				//nodeData[3] = node.data.stoDrv+":\\"+node.data.folderNam+"\\"+node.data.sceneyear+"\\"+node.data.gtypDst+"\\"+node.data.map5000Num+"\\"+node.data.fileNam;
				nodeData[4] = node.data.filePath;

				fileData.push(nodeData);
			}

			//아직 로딩 되지 않은 노드 정보 추가
			if (null != node.extraClasses && "" != node.extraClasses && node.extraClasses.indexOf("ort_") > -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.securityCde + "|" + node.data.gtypDst + "|" + node.data.mapNum;
				nodeData[3] = "loadedYet";
				nodeData[4] = "loadedYet";

				fileData.push(nodeData);
			}
		}
	}

	return fileData;
}

/**
 * 수치표고 선택 객체 반환
 */
function getDemCheckedDataObj() {
	var fileData = new Array();
	var selectedNodes = $("#demTreeRoot").fancytree("getTree").getSelectedNodes();

	if (selectedNodes != null) {
		for (var idx = 0; idx < selectedNodes.length; idx++) {
			var nodeData = new Array();
			var node = selectedNodes[idx];
			if (!node.hasChildren() && node.extraClasses != undefined && node.extraClasses.indexOf('fancytree-disable') == -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.map5000Num;
				nodeData[3] = node.data.gridInt;
				//nodeData[3] = node.data.stoDrv+":\\"+node.data.folderNam+"\\"+node.data.sceneyear+"\\"+node.data.gridInt+"\\"+node.data.map5000Num+"\\"+node.data.fileNam;
				nodeData[4] = node.data.filePath;

				fileData.push(nodeData);
			}

			//아직 로딩 되지 않은 노드 정보 추가
			if (null != node.extraClasses && "" != node.extraClasses && node.extraClasses.indexOf("dem_") > -1) {
				nodeData[0] = node.data.imageCde;
				nodeData[1] = node.data.zoneCode;
				nodeData[2] = node.data.securityCde + "|" + node.data.gridInt + "|" + node.data.mapNum;
				nodeData[3] = "loadedYet";
				nodeData[4] = "loadedYet";

				fileData.push(nodeData);
			}
		}
	}

	return fileData;
}

/**
 * AT 선택 객체 반환
 */
function getAtCheckedDataObj() {
	var fileData = new Array();
	var selectedNodes = $("#atTreeRoot").fancytree("getTree").getSelectedNodes();

	if (selectedNodes != null) {
		for (var idx = 0; idx < selectedNodes.length; idx++) {
			var nodeData = new Array();
			var node = selectedNodes[idx];
			if (!node.hasChildren()) {
				nodeData[0] = "atCde";
				nodeData[1] = node.data.zoneCode;
	
				fileData.push(nodeData);
			}
		}
	}

	return fileData;
}

/**
 * 수치지형도 선택 객체 반환
 */
function getSuchiCheckedDataObj() {
	var fileData = new Array();
//	var selectedNodes = $("#suchiTreeRoot").fancytree("getTree").getSelectedNodes();
	
	$("#combineTreeMenu > ul li[id$='TR']").each(function() {
		var selectedNodes = $(this).fancytree("getTree").getSelectedNodes();
		
		if (selectedNodes != null) {
			for (var idx = 0; idx < selectedNodes.length; idx++) {
				var nodeData = new Array();
				var node = selectedNodes[idx];
				if (!node.hasChildren() && node.extraClasses != undefined) {
					if (node.data.mapShtNo) {
						nodeData[0] = "PDT008";
						nodeData[1] = node.data.mapShtNo;
						nodeData[2] = node.data.mapSerNo;
						nodeData[3] = node.data.mapHistoryNo;
		
						fileData.push(nodeData);
					}
				}
	
				//아직 로딩 되지 않은 노드 정보 추가
				if (null != node.extraClasses && "" != node.extraClasses && node.extraClasses.indexOf("suchi_") > -1) {
					nodeData[0] = "PDT008";
					nodeData[1] = node.data.mapShtNo;
					nodeData[2] = node.data.mapSerNo;
					nodeData[3] = "loadedYet";
	
					fileData.push(nodeData);
				}
			}
		}
		
	});		
	return fileData;
}

function regApplyTree() {

	/**************************** 신청서 validation 시작 ****************************/
	var apPost = $("#applyForm #apPost").val();
	var apDepartment = $("#applyForm #apDepartment").val();
	var apName = $("#applyForm #apName").val();
	var apTel = $("#applyForm #apTel").val();
	var purpose = $("#applyForm #purpose option:selected").val();
	var detailPurpose = $("#applyForm #detailPurpose option:selected").val();
	var useYn   = $("#applyForm #useYn:checked").val();
	var agreeYn = $("#applyForm #agreeYn:checked").val();

	if (apPost == null || apPost == "" || apPost == "undefined") {
		alert("기관명을 입력해 주세요.");
		$("#applyForm #apPost").focus();
		return;
	}

	if (apDepartment == null || apDepartment == "" || apDepartment == "undefined") {
		alert("부서를 입력해 주세요.");
		$("#applyForm #apDepartment").focus();
		return;
	}

	if (apName == null || apName == "" || apName == "undefined") {
		alert("성명을 입력해 주세요.");
		$("#applyForm #apName").focus();
		return;
	}

	if (apTel == null || apTel == "" || apTel == "undefined") {
		alert("연락처를 입력해 주세요.");
		$("#applyForm #apTel").focus();
		return;
	} else {
		if (checkTel(apTel)) {
			alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 010-XXXX-XXXX");
			$("#applyForm #apTel").focus();
			return;
		}
	}

	if (purpose == null || purpose == "" || purpose == "undefined") {
		alert("신청목적을 선택해 주세요.");
		$("#applyForm #purpose").focus();
		return;
	}

	if (detailPurpose == null || detailPurpose == "" || detailPurpose == "undefined") {
		alert("상세 신청목적을 선택해 주세요.");
		$("#applyForm #detailPurpose").focus();
		return;
	}
	
	if(useYn != "Y"){
			alert("공개제한 공간정보 이용 서약서에 체크해 주세요.");
			$("#applyForm #useYn").focus();
			return;
		}

	if (agreeYn != "Y") {
		alert("신청정보 확인에 체크해 주세요.");
		$("#applyForm #agreeYn").focus();
		return;
	}
	/**************************** 신청서 validation 종료 ****************************/

	//이미지 타입, 사업지구 코드, 코스번호, 사진번호, 파일(경로포함)
	var obj;
	if (fileDownState == 1)  {
		obj = getSelectedData();
	} else {
		obj = getSelectedDataObj();
	}
	
	//파일 수만큼
	var fileType = [];
	var zoneCode = [];
	var pathOne = [];
	var pathTwo = [];
	var fileName = [];

	for (var i = 0; i < obj.length; i++) {
		//if(obj[i][0] == "PDT002" || obj[i][0] == "PDT004" || obj[i][0] == "PDT005" || obj[i][0] == "PDT010"){

		//수치지형도		
		if(obj[i][0] == "PDT008") {
			fileType.push(obj[i][0]); 	//PDT008
			zoneCode.push(obj[i][1]);	//sht
			pathOne.push(obj[i][2]);	//ser
			pathTwo.push(obj[i][3]);	//his or yet
			fileName.push("");	
		//AT성과
		} else if (obj[i][0] == "atCde") {
			fileType.push(obj[i][0]);
			zoneCode.push(obj[i][1]);
			pathOne.push("");
			pathTwo.push("");
			fileName.push("");
		} else {
			fileType.push(obj[i][0]);
			zoneCode.push(obj[i][1]);
			pathOne.push(obj[i][2]);
			pathTwo.push(obj[i][3]);
			fileName.push(obj[i][4]);
		}
	}

	//신청서 정보
	var data = $("#applyForm").serializeObject();
	//이전 검색 조건 포함
	$.extend(data, beforeSearchParam);
	data["imageCde"] = fileType;
	data["keyVal1"] = zoneCode;
	data["keyVal2"] = pathOne;
	data["keyVal3"] = pathTwo;
	data["keyVal4"] = fileName;
	
	ajaxCallJson("/apply/regApplyTree.do", data, function(result, data) {
		if (result != null && result.isSuccess == "1") {
			alert("신청서가 정상적으로 접수되었습니다.");

			//메인화면 신청 내역 갱신
			getMainApply();
			$("#applyForm .btnLayerClose").click();
			
		} else {
			alert("신청서 접수에 실패하였습니다.");
		}
	});
}

function isNull(text) {
	if (text == null) {
		text = "-";
	}
	return text;
}

function to_date(date_str) {
	var yyyyMMdd = String(date_str);
	var sYear = yyyyMMdd.substring(0, 4);
	var sMonth = yyyyMMdd.substring(4,  6)
	var sDate = yyyyMMdd.substring(6, 8);

	return sYear + "-" + sMonth + "-" + sDate;
}
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공간정보 행정망 서비스 이용가이드</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/userGuidePop.css" />

<script type="text/javascript" src="/niis/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery.mCustomScrollbar.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#serviceGuideLayer").on("click", ".serviceGuideMenu li a", function(){
			
			var index = $(this).parent().index();
			
			$("#serviceGuideLayer .serviceGuideMenu li").each(function(){
				if($(this).find("strong").length > 0){
					$(this).html('<a href="#none">' + $(this).find("strong").html() + '</a>');
				}
			});
			
			$(this).parent("li").html('<strong>' + $(this).html() + '</strong>');
			
			var $ul = $("#serviceGuideLayer .serviceGuideMenuContent ul")
			
			$ul.hide().eq(index).show();
			$ul.eq(index).children("li").removeClass("active");
			$ul.eq(index).children("li").first().addClass("active");
		});
		
		$('.btnServiceGuideMenuControl .next').click(function(){
			
			var eq  = $(".serviceGuideMenu ul").find("strong").parents("li").index();
			var $ul = $('.serviceGuideMenuContent ul').eq(eq);
			
			if($ul.children('li').last().hasClass('active')) {
				$ul.children('li').removeClass('active').first().addClass('active');
			}else{
				$ul.children('li.active').next().addClass('active').siblings().removeClass('active');
			}
		});
		$('.btnServiceGuideMenuControl .prev').click(function(){
			
			var eq  = $(".serviceGuideMenu ul").find("strong").parents("li").index();
			var $ul = $('.serviceGuideMenuContent ul').eq(eq);
			
			if($ul.children('li').first().hasClass('active')) {
				$ul.children('li').removeClass('active').last().addClass('active');
			}else{
				$ul.children('li.active').prev().addClass('active').siblings().removeClass('active');
			}
		});
		
		$("#serviceGuideLayer #helpYn").click(function(){
			cfrmInitGuide();
		});
		
		var width = $('#serviceGuideLayer').outerWidth() + (window.outerWidth - window.innerWidth);
		var height = $('#serviceGuideLayer').outerHeight() + (window.outerHeight - window.innerHeight);
		
		window.resizeTo(width, height);
	});
	
	function cfrmInitGuide(){
		
		jQuery.ajax({
			type		: "POST",
			async		: true,
			url			: "/niis/userinfo/cfrmInitGuide.do",
			dataType	: "json",
			data		: "",
			success : function(result){
				alert("해당 가이드는 좌측의 [이용안내] 메뉴를 통해 \n다시 확인하실 수 있습니다.");
				window.close();
			},
			error : function(result){
				alert("해당 가이드는 좌측의 [이용안내] 메뉴를 통해 \n다시 확인하실 수 있습니다.");
				window.close();
			}
		});
	}
	
</script>
</head>
<body>
<div id="serviceGuideLayer" class="layer" style="width:1100px;height:492px">
	<!-- 
<div id="serviceGuideLayer" class="layer" style="width:1100px;height:547px">
	<div class="tit">
		<h3>이용안내</h3>
		<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="" /></a>
	</div>
	 -->
	<div class="con">
		<div class="serviceGuideMenu">
			<div class="mCustomScrollbar">
				<ul>
					<li><strong>지도를 통한 원본DB 검색 및 신청 방법</strong></li>
					<li><a href="#none">원본DB 목록 신청 방법</a></li>
					<li><a href="#none">신청한 영상의 승인, 반려 여부 확인 방법</a></li>
				</ul>
			</div>
		</div>
		<div class="serviceGuideMenuContent">
		
			<!-- 지도를 통한 원본DB 검색 및 신청 방법 -->
			<ul style="width:100%;height:100%;">
				<li class="active">
					<div class="img"><img src="/niis/images/guide/guide_1_1.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">1</span>
						원본DB 검색 메뉴를 선택합니다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_2.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">2</span>
						행정, 명칭, 지번, 새주소, 도엽 중 검색 방법을 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_3.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">3</span>
						검색 방법 선택 후 각 방법에 맞는 검색 조건을 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_4.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">4</span>
						검색할 영상의 종류, 영상 촬영년도, 사업지구를 선택하고 검색 버튼을 누른다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_5.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">5</span>
						<img src="/niis/images/icon/ic_3.png" style="padding:0px 3px;" />&nbsp;마크를 통한 영상 위치 확인<br />
						<img src="/niis/images/sub/ic_1.png" />&nbsp;지도와 영상 중첩을 통한 영상 확인<br />
						<img src="/niis/images/sub/ic_3.png" />&nbsp;영상의 메타데이터 확인<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_6.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">6</span>
						다운로드할 영상을 선택한 뒤 신청서 작성 버튼을 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_7.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">7</span>
						신청서 내용을 기입하고 메타데이터 포함여부를 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_8.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">8</span>
						대기를 선택하여 신청 내역을 확인한다.<br />
					</div>
				</li>
			</ul>
			<!-- //지도를 통한 원본DB 검색 및 신청 방법 -->
			
			<!-- 원본DB 목록 신청 방법 -->
			<ul style="width:100%;height:100%;display:none">
				<li>
					<div class="img"><img src="/niis/images/guide/guide_2_1.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">1</span>
						원본DB 목록 메뉴에서 신청할 영상종류를 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_2_2.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">2</span>
						사업년도, 사업지구를 필수항목으로 선택한다.<br />
						코스번호, 사진번호, 해상도, 카메라구분의 상세 조건이 필요 시 선택한다.<br />
						조회버튼을 눌러 조회한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_2_3.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">3</span>
						검색 결과를 확인 후 신청할 영상을 선택하고, 신청서 작성 버튼을 선택한다.<br />
						검색 결과 전체를 신청하려면 전체 신청을 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_2_4.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">4</span>
						신청서 내용을 기입하고 메타데이터 포함여부를 선택한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_1_8.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">5</span>
						대기를 선택하여 신청 내역을 확인한다.<br />
					</div>
				</li>
			</ul>
			<!-- //원본DB 목록 신청 방법 -->
			
			<!-- 신청한 영상의 승인, 반려 여부 확인 방법 -->
			<ul style="width:100%;height:100%;display:none">
				<li>
					<div class="img"><img src="/niis/images/guide/guide_3_1.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">1</span>
						승인을 선택하여 승인 내역을 확인한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_3_2.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">2</span>
						다운로드를 선택하여 다운로드 한다.<br />
						다운로드 기간연장이 필요할 시 기간 연장 버튼을 선택한다.(총 2회, 20일 연장가능)<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_3_3.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">3</span>
						반려를 선택하여 반려 내역을 확인한다.<br />
					</div>
				</li>
				<li>
					<div class="img"><img src="/niis/images/guide/guide_3_4.png" style="max-width: 100%; height: auto;" alt="" /></div>
					<div class="serviceGuideTxt">
						<span class="num">4</span>
						반려사유 확인 버튼을 선택하여 반려 사유를 확인한다.<br />
					</div>
				</li>
			</ul>
			<!-- 신청한 영상의 승인, 반려 여부 확인 방법 -->
			
			<div class="btnServiceGuideMenuControl">
				<a href="#none" class="prev"><</a>
				<a href="#none" class="next">></a>
			</div>
		</div>
		<div class="dontSeeYouAgain">
			<input type="checkbox" id="helpYn" />
			<label for="helpYn">다시 보지 않기</label>
		</div>
	</div>
</div>
</body>
</html>
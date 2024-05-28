<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
console.log("테스트");
</script>
  
			<!-- .topFix -->
			<div class="topFix">
				<h2>사용자 관리 (관리자 전용)</h2>
				<a href="#main" class="btnPageClose"><img src="/niim/images/common/btn_page_close.png" alt="닫기" /></a>
				<div class="search">
					<ul>
						<li>
							<span style="width:0;"></span>
							<select id="usrSearchGbn" style="min-width:80px;">
								<option value="">선택</option>
								<option value="0">사용자이름</option>
								<option value="1">사용자ID</option>
							</select>
							<input type="text" id="usrSearchWrd"/>
						</li>
					</ul>
					<div class="btnSearch"><a href="#none" onclick="getUserList();">검색</a></div>
				</div>
			</div>
			<!-- /.topFix -->
			<!-- .bottomFix -->
			<div class="bottomFix">
				<h3>조회 결과 <a href="#none" class="btnFulllScreen"><span class="skip">확대</span></a></h3>
				<!-- .centerVariable -->
				<div class="centerVariable">
					<!-- .boardStyle_2 -->
					<div class="boardStyle_2">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
 							<colgroup><col width="50px" /></colgroup>
							<tbody>
								<tr>
<!-- 									<th><input type="checkbox" /></th> -->
<!-- 									<th>이름</th> -->
<!-- 									<th>부서명</th> -->
<!-- 									<th>권한</th> -->
									<th><input type="checkbox" /></th>
									<th>ID</th>
									<th>이름</th>
									<th>부서명</th>
									<th>권한</th>
									<th>전화번호</th>
									<th>이메일</th>
									<th>최종접속상태</th>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- /.boardStyle_2 -->
					<!-- .boardStyle_2 -->
					<div class="boardStyle_2">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
 							<colgroup><col width="50px" /></colgroup>
							<tbody id="usrInfoList">
<!-- 								<tr> -->
<!-- 									<td><input type="checkbox" /></td> -->
<!-- 									<td>조병상</td> -->
<!-- 									<td>국토지리정보원</td> -->
<!-- 									<td>관리자</td> -->
<!-- 								</tr> -->
							</tbody>
						</table>
					</div>
					<!-- /.boardStyle_2 -->
				</div>
				<!-- /.centerVariable -->
				<!-- .bottom -->
				<div class="bottom">
					<div class="paging on">
						<a href="#none" class="btnFirst"><img src="/niim/images/board/btn_first.gif" alt="처음" /></a>
						<a href="#none" class="btnPrev"><img src="/niim/images/board/btn_prev.gif" alt="이전" /></a>
						paging &nbsp;&nbsp;<input type="text" value="1" /> <span>/ 1  페이지</span>
						<a href="#none" class="btnNext"><img src="/niim/images/board/btn_next.gif" alt="다음" /></a>
						<a href="#none" class="btnLast"><img src="/niim/images/board/btn_last.gif" alt="마지막" /></a>
						<select>
							<option>10</option>
						</select>
					</div>
					<ul class="btnArea on">
						<li><a href="javascript:fnUsrInfoDel();"><img src="/niim/images/board/btn_dele.gif" alt="삭제" /></a></li>
						<li class="right"><a href="#userRegist" class="btnLayerOpen" onclick="javascript:fnUsrInfoAdd();return false;"><img src="/niim/images/board/btn_user_regist.gif" alt="사용자 등록" /></a></li>
					</ul>
				</div>
				<!-- /.bottom -->
			</div>
			<!-- /.bottomFix -->
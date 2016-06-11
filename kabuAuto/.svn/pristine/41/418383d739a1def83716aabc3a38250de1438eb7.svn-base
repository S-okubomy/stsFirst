<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.net.URLEncoder" %>


<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="../js/previewMulti.js" charset="UTF-8"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>

<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />


<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">


<style type="text/css">
/*
 * 罫線を表示しないテーブル
 */
.noLineTable {
		/*		border-collapse: collapse;  */
				border-style:none;
				}

/*
 * 隣接する線を重ねて表示

td, th  {
 				border-collapse: collapse;
				border: 2px #808080 solid;
		}
*/

 /* 上下左右すべての線を非表示  */
.lineDel  { border-style:none; }


/* すべての線を表示  */

.NoLine    {
/* 			border: 1px solid black; */
			text-align: left;
			vertical-align: middle;
			}

.textRight {
/* 				border: 1px solid black; */
			   text-align: right;
			   vertical-align: middle;
			 }

.textRightSmall {
/* 				border: 1px solid black; */
			   text-align: right;
			   vertical-align: middle;
			   font-size:12px
			 }

.NoLineCenter {
/* 				border: 1px solid black; */
			   text-align: center;
			   vertical-align: middle;
			 }

#left1{

			float: left;
}

#left2{
			vertical-align: middle;
			float: left;
/* 			background-color:red; */
}

#left3{
			float: left;
}

#preview{
			vertical-align: middle;
			float: left;
}


/*
 * 右側の線のみ非表示。
 */
.R_NoLine  {
    border: 2px #808080 solid;
    border-right-style:none;
}

/*
 * 左側の線のみ非表示。
 */
.L_NoLine  {
    border: 2px #808080 solid;
    border-left-style:none;
}

/*
 * 下側の線のみ非表示。
 */
.B_NoLine  {
    border: 2px #808080 solid;
    border-bottom-style:none;
}

/*
 * 上、下側の線のみ非表示。
 */
.TB_NoLine  {
    border: 2px #808080 solid;
    border-top-style:none;
    border-bottom-style:none;
}


/*
 * 左、右側の線のみ非表示。
 */
.RL_NoLine  {
    border: 2px #808080 solid;
    border-right-style:none;
    border-left-style:none;
}

/*
 * 下、左、右側の線のみ非表示。
 */
.BRL_NoLine  {
    border: 2px #808080 solid;
    border-right-style:none;
    border-left-style:none;
    border-bottom-style:none;
    text-align: center;
	vertical-align: middle;
}

th {
	background-color: #bde9ba; }

th.redCell {
	text-align: right;
	vertical-align: middle;
	background-color: #ffcc99; }


.file {
  display: inline-block;
  overflow: hidden;
  position: relative;
  padding: .5em;
  border: 1px solid #999;
  background-color: #F0F;
}

.file input[type="file"] {
  opacity: 0;
  filter: progid:DXImageTransform.Microsoft.Alpha(opacity=0);
  position: absolute;
  right: 0;
  top: 0;
  margin: 0;
  font-size: 100px;
  cursor: pointer;
}


</style>

<title>銘柄 検索画面</title>

</head>
<body>

<jsp:include page="../common/kabuHeader.jsp" />

<script type="text/javascript">
<!--

function disp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('削除して良いですか？')){

		return true;

	}
	// 「OK」時の処理終了

	// 「キャンセル」時の処理開始
	else{

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false;

	}
	// 「キャンセル」時の処理終了

}

// -->
</script>


<h1>銘柄 検索画面</h1>

<h2>検索条件</h2>
<s:form method="POST"  styleId="formid">
		<table border="1">
			<tr>
				<th class="textRight">ボリンジャー・バンド 標準偏差σに掛ける係数</th>
				<td>
					<html:select property="sigmaSerch" value="${f:h(sigmaSerch)}" >
						<html:option value="未選択">未選択</html:option>
						<html:option value="0.0">0.0</html:option>
						<html:option value="0.5">0.5</html:option>
						<html:option value="1.0">1.0</html:option>
						<html:option value="1.5">1.5</html:option>
						<html:option value="2.0">2.0</html:option>
						<html:option value="2.5">2.5</html:option>
						<html:option value="3.0">3.0</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th class="textRight">移動平均乖離率 閾値(%)</th>
				<td>
					<html:select property="kairiSerch" value="${f:h(kairiSerch)}" >
						<html:option value="未選択">未選択</html:option>
						<html:option value="0.0">0.0</html:option>
						<html:option value="3.0">3.0</html:option>
						<html:option value="5.0">5.0</html:option>
						<html:option value="8.0">8.0</html:option>
						<html:option value="10.0">10.0</html:option>
						<html:option value="13.0">13.0</html:option>
						<html:option value="16.0">16.0</html:option>
					</html:select>
				</td>
			</tr>
		</table>
		<html:image src="../layoutImage/serch.gif" property="kabuSerch" value="検索" />
	</s:form>

<br>



<c:if test="${serchFlag == true}">
	<c:if test="${hasSerch == true}" var="flagHas">
	  <font size="15" color="red">該当情報は0件です。</font>
	</c:if>
	<c:if test="${! flagHas}">
		<h4>買いかも リスト</h4>
			<table border="1" >
				<tr>
					<th class="textRight">取引日</th>
					<th>株コード</th>
					<th>銘柄</th>
					<th>終値</th>
					<th>移動平均<br>（20日）</th>
					<th>標準偏差<br>（20日）</th>
					<th>ボリンジャ値<br>（上部バンド）</th>
					<th>ボリンジャ値<br>（下部バンド）</th>
					<th>移動平均乖離率(%)<br>（20日）</th>
				</tr>

				<c:forEach var="obj" items="${calLowList}" varStatus="status">
					<tr>
						<td>
							<fmt:formatDate value="${obj.kabuDate}" pattern="yyyy/MM/dd"/>
						</td>
						<td>${f:h(obj.kabuCode)}</td>
						<td>
							<a href="http://www.google.co.jp/search?hl=ja&source=hp&q=株 情報 ${f:h(obj.kabuName)}">${f:h(obj.kabuName)}</a>
						</td>
						<td><fmt:formatNumber value="${obj.kabuClose}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.transAve20}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.sigma20}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.bolCalUp}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.bolCalLow}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.kairi}" pattern="0.0" /></td>
					</tr>
				</c:forEach>
			</table>

			<br>

			<h4>売りかも リスト</h4>
			<table border="1" >
				<tr>
					<th class="textRight">取引日</th>
					<th>株コード</th>
					<th>銘柄</th>
					<th>終値</th>
					<th>移動平均<br>（20日）</th>
					<th>標準偏差<br>（20日）</th>
					<th>ボリンジャ値<br>（上部バンド）</th>
					<th>ボリンジャ値<br>（下部バンド）</th>
					<th>移動平均乖離率(%)<br>（20日）</th>
				</tr>

				<c:forEach var="obj" items="${calUpList}" varStatus="status">
					<tr>
						<td>
							<fmt:formatDate value="${obj.kabuDate}" pattern="yyyy/MM/dd"/>
						</td>
						<td>${f:h(obj.kabuCode)}</td>
						<td>
							<a href="http://www.google.co.jp/search?hl=ja&source=hp&q=株 情報 ${f:h(obj.kabuName)}">${f:h(obj.kabuName)}</a>
						</td>
						<td><fmt:formatNumber value="${obj.kabuClose}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.transAve20}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.sigma20}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.bolCalUp}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.bolCalLow}" pattern="0.0" /></td>
						<td><fmt:formatNumber value="${obj.kairi}" pattern="0.0" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</c:if>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="keywords" content="株, 関連, テクニカル分析,観光,食事,グルメ,便利グッズ,家電,収納雑貨,家具,インテリア,収納,生活雑貨,通販 アマゾン,楽天,Apple TV, 電子工作" />
<meta name="description" content="株, 関連, テクニカル分析,観光、食事、グルメ,収納雑貨。生活雑貨は家具・インテリア・収納の通販サイト。アマゾン、楽天" />

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="../css/style.css">

<title>株関連 テクニカル分析</title>

</head>
<body>



<h1>株関連 テクニカル分析</h1>
<html:errors/>


<font size="1" color=#808080>　　株関連のテクニカル分析（ボリンジャ、移動平均）の一覧です。勉強の意味合いでWeb作ってますので、<br>
				　　不備があるかもしれませんが、ご了承ください。株価情報をバッチ処理でWebサイト（Yahooファイナンス）<br>
				　　より引っ張ってきて、サーバサイドで集計処理を行っています。テクニカル分析は、ボリンジャー・バンド、<br>
				　　移動平均乖離率を使っています。利用中に生じた損害（当サイトから得た情報によって生じた損害等）<br>
				　　に対する一切の責任を負いかねます。<br>
</font>

<br><br>

<s:form method="POST" >
	<table>
		<tr>
			<th>ユーザー名：</th>
			<td><html:text property="rootId"/> </td>
		</tr>
		<tr>
			<th>パスワード：</th>
			<td><html:password property="password" /> </td>
		</tr>
		<tr>
			<th><s:submit property="loginRequest" value="ログイン" /></th>
			<td></td>
		</tr>
	</table>
</s:form>

<br>
<br>

<html:errors/>
<s:form method="POST" >
<html:hidden property="rootId" value="guest"/>
<html:hidden property="password" value="open"/>
ゲストログイン用 → <s:submit property="loginRequest" value="ログイン" />
</s:form>


<br>
<img src="../layoutImage/img1.jpg"  alt="" height="200" />
<img src="../layoutImage/img2.jpg"  alt="" height="200" />
<br><br>

<a href="http://okbhomepage.web.fc2.com/top.html">ホームに戻る</a>
<br>

<font size="1">※ 本サイトでは、Yahooファイナンスの株価情報を取得させて頂いています。</font><br>
<font size="1">※ ゲストログインできます。利用中に生じた損害（当サイトから得た情報によって生じた損害等）<br>
　　に対する一切の責任を負いかねます。</font><br>

 <br>
</body>
</html>


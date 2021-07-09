<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='dstcBean' class='ycms.component.bean.DataSourceTextCollectionBean' scope='session'></jsp:useBean>
<c:out value='${dstcBean.register(pageContext.request.session, "dstcBean")}'></c:out>

<c:if test='${param["text_accept"] ne null}'><c:out value="${dstcBean.setText(param['text_field'])}"></c:out></c:if>
<c:if test='${param["text_delete"] ne null}'><c:out value="${dstcBean.resetText()}"></c:out></c:if>
<c:if test='${param["text_plus"] ne null}'><c:out value='${dstcBean.putText()}'></c:out></c:if>
<c:if test='${param["text_minus"] ne null}'><c:out value='${dstcBean.removeText()}'></c:out></c:if>
<c:if test='${param["text_clean"] ne null}'><c:out value='${dstcBean.clearTexts()}'></c:out></c:if>
<c:if test='${param["text_reset"] ne null}'><c:out value="${dstcBean.resetSelected()}"></c:out></c:if>
<c:if test='${param["text_select"] ne null}'><c:out value="${dstcBean.select()}"></c:out></c:if>
<c:if test='${param["text_refresh"] ne null}'></c:if>

<c:if test='${param["datasource_text_submit"] ne null}'>
	<c:if test="${param['datasource_text_value'] eq 'java_buffer_text_ds'}">
		<c:out value="${dstcBean.dataSourceJavaBuffer()}"></c:out>
	</c:if>
	<c:if test="${param['datasource_text_value'] eq 'mongo_database_text_ds'}">
		<c:out value="${dstcBean.dataSourceMongoDatabase()}"></c:out>
	</c:if>
</c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Text</title>
		<script type='text/javascript' src='../../JAVASCRIPT/ajax_functionals.js'></script>
		<script type='text/javascript' src='../../JAVASCRIPT/ajax_utils.js'></script>
		<script type='text/javascript' src='../../OBJECT/AjaxMessage.js'></script>
		<script type='text/javascript' src='../../OBJECT/AjaxRequest.js'></script>
		<script type='text/javascript' src='../../OBJECT/AjaxResponse.js'></script>
		<script type='text/javascript' src='../../OBJECT/AjaxType.js'></script>
		<script type='text/javascript' src='../../js/select.js'></script>
	</head>
	<body>
		<h1>Текст - извори података</h1>
		<form method='POST'>
			<textarea name='text_field'style='width:100%; height: 300px'><c:out value="${dstcBean.text}"></c:out></textarea><br><br>
			<input type='submit' name='text_accept'  value='Постављање'/>
			<input type='submit' name='text_refresh' value='освјежавање'/>
			<input type='submit' name='text_delete'  value='брисање'/>
			<input type='submit' name='text_plus' value='убацивање'/>
			<input type='submit' name='text_minus'  value='избацивање'/>
			<input type='submit' name='text_clean'  value='чишћење'/>
			<input type='submit' name='text_reset'  value='ресетовање'/>
			<input type='submit' name='text_select'  value='селектовање'/>
		</form>
		<br>
		<fieldset id='datasource_fieldset'>
			<legend>Подаци и извори података</legend>
			<form method='POST' name='datasource_text_choose' id='datasource_text_choose' action='#datasource_text_choose'>
				<select name='datasource_text_value'>
					<option value='java_buffer_text_ds' ${dstcBean.isDataSourceJavaBuffer()?"selected":""} >Јава меморијски бафер</option>
					<option value='mongo_database_text_ds' ${dstcBean.isDataSourceMongoDatabase()?"selected":""} >Монго база података</option>
				</select>
				<input type='submit' name='datasource_text_submit' value='промјена'>
			</form>
		</fieldset>
		<br>
		<div style='background-color: violet' align='justify'>
			<div><b><code id='selected_id_place'><c:if test="${dstcBean.isSelected()}"><c:out value='${dstcBean.selectedId}'></c:out></c:if></code></b></div><hr>
			<div style='white-space:pre; width:100%'><code id='selected_text_place'><c:if test="${dstcBean.isSelected()}"><c:out value='${dstcBean.selectedText.text()}'></c:out></c:if></code></div>
		</div>
		<div id='items_place'>
			<c:forEach var='mtext' items='${dstcBean.getList()}'><br>
				<div style='background-color: steelblue' align='justify' onclick='select_text("${pageContext.request.contextPath}", "dstcBean", "${mtext}")'>
					<div><b><code><c:out value='${mtext}'></c:out></code></b></div><hr>
					<div style='white-space:pre; width:100%'><code><c:out value='${dstcBean.myText(mtext).text()}'></c:out>&nbsp;</code></div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>
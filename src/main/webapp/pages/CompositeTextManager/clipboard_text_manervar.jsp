<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='ctcBean' class='ycms.component.bean.ClipboardTextCollectionBean' scope='session'></jsp:useBean>
<c:out value='${ctcBean.register(pageContext.request.session, "ctcBean")}'></c:out>

<c:if test='${param["text_accept"]  ne null}'><c:out value="${ctcBean.setText(param['text_field'])}"></c:out></c:if>
<c:if test='${param["text_delete"]  ne null}'><c:out value="${ctcBean.resetText()}"></c:out></c:if>
<c:if test='${param["text_plus"]    ne null}'><c:out value='${ctcBean.putText()}'></c:out></c:if>
<c:if test='${param["text_minus"]   ne null}'><c:out value='${ctcBean.removeText()}'></c:out></c:if>
<c:if test='${param["text_clean"]   ne null}'><c:out value='${ctcBean.clearTexts()}'></c:out></c:if>
<c:if test='${param["text_reset"]   ne null}'><c:out value="${ctcBean.resetSelected()}"></c:out></c:if>
<c:if test='${param["text_select"]  ne null}'><c:out value="${ctcBean.select()}"></c:out></c:if>
<c:if test='${param["text_front"]   ne null}'><c:out value="${ctcBean.front()}"></c:out></c:if>
<c:if test='${param["text_change"]  ne null}'><c:out value="${ctcBean.accept()}"></c:out></c:if>
<c:if test='${param["text_refresh"] ne null}'></c:if>


<c:if test='${param["datasource_text_submit"] ne null}'>
	<c:if test="${param['datasource_text_value'] eq 'java_buffer_text_ds'}">
		<c:out value="${ctcBean.dataSourceJavaBuffer()}"></c:out>
	</c:if>
	<c:if test="${param['datasource_text_value'] eq 'mongo_database_text_ds'}">
		<c:out value="${ctcBean.dataSourceMongoDatabase()}"></c:out>
	</c:if>
</c:if>

<c:if test='${param["datasource_text_submit_dest"] ne null}'>
	<c:if test="${param['datasource_text_value_dest'] eq 'java_buffer_text_ds'}">
		<c:out value="${ctcBean.dataSourceJavaBufferDestination()}"></c:out>
	</c:if>
	<c:if test="${param['datasource_text_value_dest'] eq 'mongo_database_text_ds'}">
		<c:out value="${ctcBean.dataSourceMongoDatabaseDestination()}"></c:out>
	</c:if>
</c:if>

<c:if test='${param["text_dst_diff_src"] ne null}'  ><c:out value="${ctcBean.transferProccessController.differentSource()}"></c:out></c:if>
<c:if test='${param["text_dst_diff_dest"] ne null}' ><c:out value="${ctcBean.transferProccessController.differentDestination()}"></c:out></c:if>
<c:if test='${param["text_dst_diff_sym"] ne null}'  ><c:out value="${ctcBean.transferProccessController.differentaSymetric()}"></c:out></c:if>
<c:if test='${param["text_dst_union"] ne null}'     ><c:out value="${ctcBean.transferProccessController.unionData()}"></c:out></c:if>
<c:if test='${param["text_dst_intersect"] ne null}' ><c:out value="${ctcBean.transferProccessController.intersectData()}"></c:out></c:if>
<c:if test='${param["text_dst_transfer"] ne null}'  ><c:out value="${ctcBean.transferProccessController.transferData()}"></c:out></c:if>
<c:if test='${param["text_dst_synch"] ne null}'     ><c:out value="${ctcBean.transferProccessController.synchronisationData()}"></c:out></c:if>
<c:if test='${param["text_dst_synch_src"] ne null}' ><c:out value="${ctcBean.transferProccessController.synchronisationDataSourcePivoted()}"></c:out></c:if>
<c:if test='${param["text_dst_synch_dest"] ne null}'><c:out value="${ctcBean.transferProccessController.synchronisationDataDestinationPivoted()}"></c:out></c:if>

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
		<h1>Текст - трансфери података</h1>
		<form method='POST'>
			<textarea name='text_field'style='width:100%; height: 300px'><c:out value="${ctcBean.text}"></c:out></textarea><br><br>
			<input type='submit' name='text_accept'  value='Постављање'/>
			<input type='submit' name='text_refresh' value='освјежавање'/>
			<input type='submit' name='text_delete'  value='брисање'/>
			<input type='submit' name='text_plus'    value='убацивање'/>
			<input type='submit' name='text_minus'   value='избацивање'/>
			<input type='submit' name='text_clean'   value='чишћење'/>
			<input type='submit' name='text_reset'   value='ресетовање'/>
			<input type='submit' name='text_select'  value='селектовање'/>
			<input type='submit' name='text_front'   value='фронтовање'/>
			<input type='submit' name='text_change'   value='промјена'/>
		</form>
		<br><br><br>
		<fieldset id='datasource_fieldset'>
			<legend>Подаци и извори података</legend>
			<div align='center'>
				<form method='POST' name='datasource_text_choose' id='datasource_text_choose' action='#datasource_text_choose'>
					<table>
						<tr>
							<td>Основно извориште података : </td>
							<td>
								<select name='datasource_text_value'>
									<option value='java_buffer_text_ds' ${ctcBean.isDataSourceJavaBuffer()?"selected":""} >Јава меморијски бафер</option>
									<option value='mongo_database_text_ds' ${ctcBean.isDataSourceMongoDatabase()?"selected":""} >Монго база података</option>
								</select>
							</td>
							<td><input type='submit' name='datasource_text_submit' value='промјена'/></td>
						</tr>
						<tr>
							<td>Одредишно извориште података : </td>
							<td>
								<select name='datasource_text_value_dest'>
									<option value='java_buffer_text_ds' ${ctcBean.isDataSourceJavaBufferDestination()?"selected":""} >Јава меморијски бафер</option>
									<option value='mongo_database_text_ds' ${ctcBean.isDataSourceMongoDatabaseDestination()?"selected":""} >Монго база података</option>
								</select>
							</td>
							<td><input type='submit' name='datasource_text_submit_dest' value='промјена'/></td>
						</tr>
					</table>
				</form>
			</div>
		</fieldset>
		<br>
		<form method="POST" id='text_ds_transfer_form' name='text_ds_transfer_form' action='#text_ds_transfer_form'>
			<input type='submit' name='text_dst_diff_src'   value='Разлика извора'/>
			<input type='submit' name='text_dst_diff_dest'  value='разлика одредишта'/>
			<input type='submit' name='text_dst_diff_sym'   value='симетрична разлика'/>
			<input type='submit' name='text_dst_union'      value='унија'/>
			<input type='submit' name='text_dst_intersect'  value='пресјек'/>
			<input type='submit' name='text_dst_transfer'   value='пренос извора'/>
			<input type='submit' name='text_dst_synch'      value='синхронизација'/>
			<input type='submit' name='text_dst_synch_src'  value='синхронизација извориштем'/>
			<input type='submit' name='text_dst_synch_dest' value='синхронизација одредиштем'/>
		</form>
		<br><br><br>
		<div style='background-color: violet' align='justify'>
			<div><b><code id='selected_id_place'><c:if test="${ctcBean.isSelected()}"><c:out value='${ctcBean.selectedId}'></c:out></c:if></code></b></div><hr>
			<div style='white-space:pre; width:100%'><code id='selected_text_place'><c:if test="${ctcBean.isSelected()}"><c:out value='${ctcBean.selectedText.text()}'></c:out></c:if></code></div>
		</div>
		<div id='items_place'>
			<c:forEach var='mtext' items='${ctcBean.getList()}'><br>
				<div style='background-color: steelblue' align='justify' onclick='select_text("${pageContext.request.contextPath}", "ctcBean", "${mtext}")'>
					<div><b><code><c:out value='${mtext}'></c:out></code></b></div><hr>
					<div style='white-space:pre; width:100%'><code><c:out value='${ctcBean.myText(mtext).text()}'></c:out>&nbsp;</code></div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>
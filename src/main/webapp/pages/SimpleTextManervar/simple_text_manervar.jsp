<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='textBean' class='ycms.component.bean.TextComponentBean' scope='session'></jsp:useBean>

<c:if test='${param["text_accept"] ne null}'><c:out value="${textBean.setText(param['text_field'])}"></c:out></c:if>
<c:if test='${param["text_delete"] ne null}'><c:out value="${textBean.resetText()}"></c:out></c:if>
<c:if test='${param["text_refesh"] ne null}'></c:if>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Text</title>
	</head>
	<body>
		<h1>Текст</h1>
		<form method='POST'>
			<textarea name='text_field'style='width:100%; height: 300px'><c:out value="${textBean.text}"></c:out></textarea><br><br>
			<input type='submit' name='text_accept'  value='Постављање'/>
			<input type='submit' name='text_refresh' value='освјежавање'/>
			<input type='submit' name='text_delete'  value='брисање'/>
		</form>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Index</title>
	</head>
	<body>
		<h1>Индекс</h1>
		<dl>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/pages/SimpleTextManervar/simple_text_manervar.jsp'>Основно баратање и приказ текста</a></dd>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/pages/EngineTextManervar/jcte_text_manervar.jsp'>Управљиво баратање и приказ текста</a></dd>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/pages/EngineTextManervar/mongo_text_manervar.jsp'>Управљиво перзистентно баратање и приказ текста</a></dd>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/pages/CompositeTextManager/data_source_text_manervar.jsp'>Управљиво баратање и приказ текстова са измјељивошћу мјеста текстова</a></dd>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/pages/CompositeTextManager/clipboard_text_manervar.jsp'>ОКРУЖЕЊЕ 1 ЕДИТОР - Управљиво баратање и приказ текстова са измјељивошћу и размјењивошћу мјеста текстова</a></dd>
		</dl>
		<dl>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/notes.jsp'>Напомене о прозводима</a></dd>
			<dd><a target='_blank' href='${pageContext.request.contextPath}/proccess.jsp'>Процеси са биљешкама</a></dd>
		</dl>
	</body>
</html>
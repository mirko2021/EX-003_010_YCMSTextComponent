<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Notes</title>
	</head>
	<body>
		<h1>Напомене</h1>
		<dl>
			<dt><a target='_blank' href='${pageContext.request.contextPath}/pages/SimpleTextManervar/simple_text_manervar.jsp'>Основно баратање и приказ текста</a></dt>
			<dd><br></dd>
			<dd><div align='justify'>Општа примопредаја текста у зрно.</div></dd>
			<dd><br></dd>
			<dt><a target='_blank' href='${pageContext.request.contextPath}/pages/EngineTextManervar/jcte_text_manervar.jsp'>Управљиво баратање и приказ текста</a></dt>
			<dd><br></dd>
			<dd><div align='justify'>Примопредаја текста у зрно са листом у меморији.</div></dd>
			<dd><br></dd>
			<dt><a target='_blank' href='${pageContext.request.contextPath}/pages/EngineTextManervar/mongo_text_manervar.jsp'>Управљиво перзистентно баратање и приказ текста</a></dt>
			<dd><br></dd>
			<dd><div align='justify'>Аутоматско складиштење текстова при додавању у листу и брисање при избацивиању.</div></dd>
			<dd><br></dd>
			<dt><a target='_blank' href='${pageContext.request.contextPath}/pages/CompositeTextManager/data_source_text_manervar.jsp'>Управљиво баратање и приказ текстова са измјељивошћу мјеста текстова</a></dt>
			<dd><br></dd>
			<dd><div align='justify'>Увођење промјењивости мјеста података, од програмске меморије сервера, па до базе података реда моного, али и кроз принцип баратања са више одредишта.</div></dd>
			<dd><br></dd>
			<dt><a target='_blank' href='${pageContext.request.contextPath}/pages/CompositeTextManager/clipboard_text_manervar.jsp'>ОКРУЖЕЊЕ 1 ЕДИТОР - Управљиво баратање и приказ текстова са измјељивошћу и размјењивошћу мјеста текстова</a></dt>
			<dd><br></dd>
			<dd><div align='justify'>Увођење промјењивости мјеста података, од програмске меморије сервера, па до базе података реда моного, али и кроз принцип баратања са више одредишта са преносима података међу одредиштима.</div></dd>
			<dd><br></dd>
		</dl>
	</body>
</html>
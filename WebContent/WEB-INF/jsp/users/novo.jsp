<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>http://wbotelhos.com.br</title>
	</head>
	<body>
		<form action="<c:url value='/usuario'/>" method="post">
			Nome: <input type="text" name="usuario.nome" value="${usuario.nome}"/><br/>
			Senha: <input type="text" name="usuario.senha" value="${usuario.senha}"/><br/>
			E-mail: <input type="text" name="usuario.email" value="${usuario.email}"/><br/>
			<input type="submit" value="Salvar"/>
		</form>
	</body>
</html>
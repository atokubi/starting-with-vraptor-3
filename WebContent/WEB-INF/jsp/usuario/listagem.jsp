<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>http://wbotelhos.com</title>
	</head>
	<body>
		<a href="<c:url value='/usuario/novo'/>">Adicionar</a><br/>

		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>Senha</th>
					<th>E-mail</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarioList}" var="item">
					<tr>
						<td>${item.nome}</td>
						<td>${item.senha}</td>
						<td>${item.email}</td>
						<td>
							<form action="<c:url value='/usuario'/>" method="post">
								<input type='hidden' name='_method' value='PUT'/>
								<input type='hidden' name='usuario.id' value='${item.id}'/>
								<input type="submit" value="Editar"/>
							</form>
						</td>
						<td>
							<form action="<c:url value='/usuario'/>" method="post">
								<input type='hidden' name='_method' value='DELETE'/>
								<input type='hidden' name='usuario.id' value='${item.id}'/>
								<input type="submit" value="Excluir"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
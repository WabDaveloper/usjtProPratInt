<%@page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="charset" content="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	<c:import url="Menu.jsp"/>	
	<div id="main"	class="container">
	<h3 class="page-header" align="center">Login</h3>
	<hr>
	<form action="login_pais.do"method="Post"></formaction>
	<div class="row">
		<div class="form-group col-md-6">
			<label for="nome"><b>Usuário</b></label>
			<input type="text"class="form-control" name="usuario" requiredmaxlength="100"placeholder="Nome de Usuário">
		</div>
	</div>
		<div class="row">

			<div class="form-group col-md-6">
				<label for="populacao"><b>Senha</b></label>
				<input type="text"class="form-control" name="senha" requiredmaxlength="100"placeholder="Senha">
			</div>
		</div>


		<div id="action" class="row">

			<div class="col-md-6">
				<button type="submit" class="btn btn-primary" name="acao" value="entrar">Entrar</button>
				<a href="login.jsp" class="btn btn-danger">Limpar</a>

			</div>
		</div>
	
</div>
</form>
	

</body>
</html>
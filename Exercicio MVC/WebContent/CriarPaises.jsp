<%@page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPEhtml>
<html>
<head>
<meta name="charset" content="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style>

hr{
	width: 100%;
height: 2px;

}

</style>

<title>Cadastro de Paises</title>
</head>
<body>

	<c:import url="Menu.jsp"/>
	
	<div id="main"	class="container">
	<h3 class="page-header" align="center">Cadastro de Paises</h3>
	<hr>
	<form action="ManterPaises.do"method="Post"></formaction>
	<div class="row">
		<div class="form-group col-md-6">
			<label for="nome"><b>Nome</b></label>
			<input type="text"class="form-control" name="nome" requiredmaxlength="100"placeholder="Nome do Pais">
		</div>
	</div>
		<div class="row">

			<div class="form-group col-md-6">
				<label for="populacao"><b>Populacao</b></label>
				<input type="text"class="form-control" name="populacao" requiredmaxlength="100"placeholder="Populacao do Pais">
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-6">
				<label for="area"><b>Area</b></label>
				<input type="text" class="form-control"	name="area" requiremaxlength="100" placeholder="Area do Pais">
			</div>

		</div>

		<div id="action" class="row">

			<div class="col-md-6">
				<button type="submit" class="btn btn-primary" name="acao" value="Criar">Criar</button>
				<a href="index.jsp" class="btn btn-danger">Limpar</a>

			</div>
		</div>
	
</div>
</form>
</body>
</html>
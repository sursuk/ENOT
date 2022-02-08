
<%@ page isELIgnored ="false" %>

<html>
<head>
	<meta charset ="utf-8">
	<title>ENOT</title>
	<link rel="shortcut icon" href="ENOTLogo.png" type="image/png">

	<!--здесь нужно вставить картинку для красоты поля сверху...-->
	<!--это стартовая страничка
	-->
	<style type="text/css">

		header{
			position: absolute;
			top: 5%;
			left: 10%;
			border-color: green;
			border-style: solid;
		}

		.inputStr{
			position: absolute;

			border-color: blue;
			border-style: solid;
		}

		.loginForm{
			position: absolute;
			top: 5%;
			right: 10%;
			border-color: red;
			border-style: solid;
		}

		.outputTable{
        	position: absolute;
        	top: 35%;
        	right: 40%;
        }

		footer{
			position: absolute;
			bottom: 5%;
			left: 45%;
			border-color: grey;
			border-style: solid;
		}

	</style>
</head>


	<body>
		<header>
			<h2>ENOT</h2>
			<div class = "inputStr">
    <form action="ENOT" method="GET">
        <input type="text" name="search" placeholder=<%= request.getParameter("search") %>>
        <input type="submit" value="search">
    </form>
</div>
		</header>


    <div class = "loginForm">
	    <form action="Login" method="GET">
            <input type="submit" value="вход"><br>

	    </form>
	    <form action="Registration" method = "GET">
	        <input type="submit" value="регистрация">
	    </form>
    </div>
</c:if>
        <p>${out}</p>
        <p>EX: ${outEX}</p>
<div class = "outputTable">
<%= session.getValue("id") %>
    <c:forEach var="out" items="${out}" default="Ничего не найдено">
        <p>${out}</p>
        <p>${outEX}</p>
    </c:forEach>
</div>

<footer><h7>ENOTeam 2022</h7></footer>

</body>
</html>



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
			top: 35%;
			left: 42%;
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

		</header>

    <div class = "loginForm">
	    <form action="Login" method="POST">
            <input type="text" name="username" placeholder="username"><br><br>
		    <input type="password" name="password" placeholder="password"><br><br>
		    <input type="submit" value="LOGIN">
	    </form>
    </div>

        <p>${out}</p>
        <p>EX: ${outEX}</p>

</div>

<footer><h7>ENOTeam 2022</h7></footer>

</body>
</html>




<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
			left: 5%;
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
        <input type="text" name="search" size="50" placeholder=<%= request.getParameter("search") %>>
		<input type = "radio" id="name" name="from" value = "name"> <label for="name">name</label>
		<input type = "radio" id="author" name="from" value = "author"> <label for="author">author</label>
		<input type = "radio" id="tags" name="from" value = "tags"> <label for="tags">tags</label>
		<input type = "radio" id="year" name="from" value = "year"> <label for="year">year</label>
   <c:if test = "${outUser!= null}">		<input type = "radio" id="private" name="private" value = "private"> <label for="private">private</label> </c:if>
		<br>
        <input type="submit" value="search">
    </form>
</div>
		</header>


    <div class = "loginForm">
    <c:if test = "${outUser!= null}">
	<p>${outUser}</p>
     <form action="addFile" method="GET">
                <input type="submit" value="ADD FILE"><br>
   	    </form>
	</c:if>

	    <form action="Login" method="GET">
            <input type="submit" value="LOGIN"><br>
	    </form>
	    <form action="Registration" method="GET">
	        <input type="submit" value="REGISTRATION">
	    </form>
    </div>

<div class = "outputTable">

	<c:forEach var="out" items="${out}" >
        <p>${out}
        <c:if test = "${outUser!= null}">
            <form action="Download" method="GET">
                <input type="hidden" name="name" value="${out.name}">
                <input type="submit" value="DOWNLOAD"><br> </p>
            </form>
        </c:if>
    </c:forEach>
</div>

<footer><h7>ENOTeam 2022</h7></footer>

</body>
</html>


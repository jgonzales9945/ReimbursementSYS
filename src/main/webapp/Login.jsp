<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link type="text/css" rel="stylesheet" href="./CSS/lookandfeel.css"/>
<link type="image/png" rel="icon" href="./Images/servlet.ImageServer.png">
</head>
<body>
	<div id="Titlebar">
		<h1>Revature Employee Login</h1>
		<div id="Linkbar">
			<a href="./index.jsp">Home</a> <a href="./CreateAccount.jsp">Create account instead</a>
		</div>
	</div>
	<div style="color: #FF0000;">${errorMessage}</div>
	<div id="loginForm">
		<form method="post" action="Login" accept-charset="utf-8">
			<fieldset>
				<legend>Enter your Email:</legend>
				<input name="email" required>
			</fieldset>
			<fieldset>
				<legend>Password:</legend>
				<input name="password" type="password" required>
			</fieldset>
			<br>
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>
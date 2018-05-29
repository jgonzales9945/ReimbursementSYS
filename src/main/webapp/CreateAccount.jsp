<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Create</title>
<link type="text/css" rel="stylesheet" href="./CSS/lookandfeel.css"/>
<link type="image/png" rel="icon" href="./Images/servlet.ImageServer.png">
</head>
<body>
	<div id="Titlebar">
		<h1>New Employee Account Creation</h1>
		<div id="Linkbar">
			<a href="./index.jsp">Home</a> <a href="./Login.jsp">login instead</a>
		</div>
	</div>
	<div id="bodyform">
		<div>${errorMessage}</div>
		<form action="./CreateAccount" method="POST" accept-charset="utf-8">
			<fieldset>
				<legend>User Name:</legend>
				<input name="username" type="text" maxlength="18" autofocus required/>
				<legend>Email:</legend>
				<input id="email" name="email" type="email" onblur="emailCheck()" maxlength="30" required/><br>
				<div class="emailerror"></div>
			</fieldset>
			<fieldset>
				<legend>First Name:</legend>
				<input type="text" name="firstname" maxlength="18" required/>
				<legend>Last Name:</legend>
				<input type="text" name="lastname" maxlength="26" required/><br>
			</fieldset>
			<fieldset>
				<legend>Authentication Key:</legend>
				<input name="authkey" type="password"/><br>
			</fieldset>
			<fieldset>
				<legend>Password:</legend>
				<input class="psw" name="password" type="password" maxlength="16" required/>
				<legend>Re-input Password:</legend>
				<input class="psw" type="password" onblur="passCheck()" required/><br>
				<div class="passworderr"></div>
				<input id="submit" type="submit" disabled/><br>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
		function emailCheck() {
			function isEmail(string){
			    if(string === null) return false;
			    //check for only one instance of @
			    var valid  = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			    return valid.test(string);
			}
			let e = document.getElementById("email").value;
			if(!isEmail(e)) document.getElementsByClassName("emailerror")[0].innerHTML = "Invalid email format!";
			else document.getElementsByClassName("emailerror")[0].innerHTML = "";
		}
		function passCheck() {
			let b = document.getElementById("submit");
			let p = document.getElementsByClassName("psw");
			if(p[0].value != "" && p[1].value != "" && p[0].value == p[1].value) {
				b.disabled=false;
				document.getElementsByClassName("passworderr")[0].innerHTML = "";
			}
			else {
				b.disabled=true;
				document.getElementsByClassName("passworderr")[0].innerHTML = "Passwords do not match!";
			}
		}
	</script>
</body>
</html>
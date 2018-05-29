<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link type="text/css" rel="stylesheet" href="./CSS/lookandfeel.css"/>
<link type="image/png" rel="icon" href="./Images/servlet.ImageServer.png">
</head>
<body>
	<div id="Titlebar">
		<h1>Revature Reimbursements</h1>
		<div id="Linkbar">
			<a href="./Login">login</a> <a href="./CreateAccount">Create Account</a>
		</div>
	</div>
		<div style="color: #FF0000;">${errorMessage}</div>
		<div style="color: #0000FF;">${successMessage}</div>
		<p>
			Welcome to your personal reimbursement system. To begin,
			click on the login or create account links.
		</p>
</body>
</html>
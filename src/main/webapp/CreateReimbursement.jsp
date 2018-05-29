<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a ticket</title>
<link type="text/css" rel="stylesheet" href="./CSS/lookandfeel.css"/>
<link type="image/png" rel="icon" href="./Images/servlet.ImageServer.png">
</head>
<body>
	<div id="Titlebar">
		<h1>New Reimbursement Ticket</h1>
		<div id="Linkbar">
			<a href="./UserPage">Home Page</a> <a href="./ViewReimbursements">View Tickets</a> <a href="./Logout">Log out</a>
		</div>
	</div>
	<div>
		<div>${errorMessage}</div>
		<form action="./CreateReimbursement" method="POST" accept-charset="utf-8">
			<fieldset>
				<legend>Type of reimbursement:</legend> 
				<select name="types">
					<option value="1">Lodging</option>
					<option value="2">Travel</option>
					<option value="3">Food</option>
					<option value="4">Other</option>
				</select>
				<legend>Amount request:</legend>
				<input name="amount" type="number" min="1" step="any" required/><br>
				<legend>Description:</legend>
				<textarea name="description" rows="10" cols="60" placeholder="Description is not required, but will help make processing your ticket faster"></textarea><br>
			</fieldset>
			<fieldset>
				<legend>File upload:</legend>
				<input name="file" type="file"/><br>
			</fieldset>
			<fieldset>
				<input id="submit" type="submit"/><br>
			</fieldset>
		</form>
	</div>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View tickets</title>
<link type="text/css" rel="stylesheet" href="./CSS/lookandfeel.css"/>
<link type="image/png" rel="icon" href="./Images/servlet.ImageServer.png">
</head>
<body>
	<div id="Titlebar">
		<h1>Current Reimbursement Tickets</h1>
		<div id="Linkbar">
			<a href="./UserPage">Home Page</a> <a href="./CreateReimbursement">Create Ticket</a> <a href="./Logout">Log out</a>
		</div>
	</div>
	<div id="selectform">
		${getform}
	</div>
	<div id="viewinfo">
		${reimview}
	</div>
	<div id="viewtable">
		${infotable }
	</div>
	<script type="text/javascript">
		function parseData(data) {
			var tbl  = document.createElement("table");
			for(var i = 0; i < data.length; i++) {
				tbl.innerHTML += '<tr><td>' + data[i].id + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].amount + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].submitted + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].resolved + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].user + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].resolve + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].type + '</td>';
				tbl.innerHTML+= '<td>' + dat[i].status + '</td>';
				tbl.innerHTML+= '<td><input name="view" type="submit" value="'+ data[i].id +'"/></td></tr>';
			}
		}
		function getdata() {
			var xhr = new XMLHttpRequest();
		    xhr.onreadystatechange = function() {
		        if (xhr.readyState == 4) {
		            var data = xhr.responseText;
		            parseData(data);
		        }
		    }
		    xhr.open('GET', './ViewReimbursement', true);
		    xhr.send(null);
		}
	</script>
</body>
</html>
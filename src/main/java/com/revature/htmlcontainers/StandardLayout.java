/**
 * 
 */
package com.revature.htmlcontainers;

import java.util.ArrayList;

/*import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;*/

import com.revature.daos.UserDAOImpl;
import com.revature.pojos.ReimStatus;
import com.revature.pojos.ReimType;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

/**
 * @author Joseph Gonzales
 *
 */
public class StandardLayout {
	private String bodyHtml = "<link type=\"text/css\" rel=\"stylesheet\" href=\"./CSS/lookandfeel.css\"/>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"	<div id=\"Titlebar\">\n";
	private String links = "		<div id=\"Linkbar\">\n";
	
	public String toString(String title) {
		//spit out html structure
		return "<title>"+ title +"</title>\n" + this.bodyHtml;
				
	}
	
	public String setTitle(String name) {
		return "		<h1>"+ name +"</h1>\n";
	}
	
	public String setLinkBar(String links) {
		return this.links + links +"		</div>";
	}
	
	public String viewForm(boolean b) {
		//return manager form
		String form = "<form action=\"./ViewReimbursements\" method=\"POST\">"+
				"			<fieldset>\n" +
				"				<legend>Type of reimbursement:</legend>\n" + 
				"				<select name=\"tickets\">\n";
		if(b) {
			return form + "					<option value=\"1\">Pending Tickets</option>\n" + //get all pending reim
					"					<option value=\"2\">Handled Tickets</option>\n" + //get all reims by resolver
					"					<option value=\"3\">Tickets by Lodging</option>\n" + //get all tickets by lodging
					"					<option value=\"4\">Tickets by Travel</option>\n" + //get all tickets by travel
					"					<option value=\"5\">Tickets by Food</option>\n" + //get all tickets by food
					"					<option value=\"6\">Tickets by Others</option>\n" + //get all other tickets
					"				</select>"+
					"			</fieldset><br>" +
					"<input id=\"submit\" type=\"submit\"/><br></form>";
		}
		//return employee form
		return form + "					<option value=\"1\">Submitted Tickets</option>\n" + //get all pending reimbursements by user
				"					<option value=\"2\">Previous Tickets</option>\n" + //get all reimbursements by user
				"				</select>"+
				"			</fieldset><br>" +
				"<input id=\"submit\" type=\"submit\"/><br></form>";
	}
	
	public String reimTable(ArrayList<Reimbursement> re, Users use) {
		StringBuilder table = new StringBuilder("<table>\n" + 
				"			<tr>\n" + 
				"				<th class=\"id\">Ticket #</th>\n" + 
				"				<th class=\"amount\">Amount</th>\n" + 
				"				<th class=\"submitted\">Date Submitted</th> \n" + 
				"				<th class=\"resolved\">Date Resolved</th> \n" + 
				"				<th class=\"userid\">User Id</th> \n" + 
				"				<th class=\"resid\">Resolver Id</th> \n" + 
				"				<th class=\"type\">Type</th> \n" + 
				"				<th class=\"status\">Status</th>  \n" + 
				"				<th class=\"status\">View</th>  \n" + 
				"			</tr>");
		for(Reimbursement tick : re) {
			table.append("<tr>\n" + 
					"				<td class=\"id\">"+ tick.getID() +"</td>\n" + 
					"				<td class=\"amount\">"+ tick.getAmount() +"</td>\n" + 
					"				<td class=\"submitted\">"+ tick.getSubmitted().toString() +"</td>\n" +
					"				<td class=\"resolved\">"+ ((tick.getResolved() != null) ? tick.getResolved().toString() : " ") +"</td>\n" +
					"				<td class=\"userid\">"+ tick.getUserID() +"</td>\n" +
					"				<td class=\"resid\">"+ ((tick.getResolverID() != 0) ? tick.getResolverID() : " ") +"</td>\n" +
					"				<td class=\"type\">"+ new ReimType().getType(tick.getTypeid()) +"</td>\n" +
					"				<td class=\"status\">"+ new ReimStatus().getStatus(tick.getStatusid()) +"</td>\n" +
					"				<th class=\"viewid\">"+//html hacking
					"					<form class=\"minibutton\" action=\"./ViewInfo\" method=\"POST\">\n" + 
					"  						<input name=\"view\" type=\"submit\" value=\""+tick.getID()+"\"/>View\n" + 
					"					</form></th>\n" +  
					"			</tr>\n");
		}
		return table.toString();
	}
	
	public String reimView(Reimbursement r, Users use) {
		StringBuilder view = new StringBuilder("<div class=\"view\"><h2>");
		view.append("Reimbursement #"+ r.getID() +"</h2>");
		view.append("Amount request: $"+ r.getAmount() +"</h2>");
		//check for nulls or defaults
		if(!r.getDescription().equals("") && r.getDescription() != null) view.append("<p>"+ r.getDescription() +"<p><br>");
		/*if(r.getPhoto() != null) {
			try {
				BufferedImage img = ImageIO.read(r.getPhoto());
				view.append("<img src=\""+ img.toString());
			} catch (IOException e) {//who cares it doesn't exist
			}
		}*/
		//check to see if the user id is equal to the resolver id on the reimbursement ticket
		if(use.getId() == 1) {
			view.append("<p>User: "+ use.getfName() + " " + use.getlName() +"</p>");
			if(r.getResolverID() != 0) {
				Users reso = new UserDAOImpl().getUserById(r.getResolverID());
				view.append("<p>Submitter: "+ reso.getfName() + " " + reso.getlName() +"</p><br>");
			}
		}
		else if(use.getId() == 2) {
			Users user = new UserDAOImpl().getUserById(r.getUserID());
			view.append("<p>Submit name: "+ user.getfName() + " " + user.getlName() +"</p><br>");
			if(r.getResolverID() > 0) {
				Users res = new UserDAOImpl().getUserById(r.getResolverID());
				view.append("<p>Resover: "+ res.getfName() + " " + res.getlName() +"</p><br>");
			}
			else {
				view.append("<form action=\"./ReimbursementAuthorize\" method=\"POST\">" +
					"			<fieldset>\n" +
					"				<legend>Reimbursement Authorization:</legend>\n" + 
					"				<select name=\"approval\">\n" +
					"					<option value=\"1\">Approve</option>\n" + 
					"					<option value=\"2\">Reject</option>\n" +
					"					<option value=\"3\">Close</option>\n" +
					"				</select>" +
					"			<input id=\"submit\" type=\"submit\"/><br>" +
					"			</fieldset>");
			}
		}
		return view.toString();
	}
	
}

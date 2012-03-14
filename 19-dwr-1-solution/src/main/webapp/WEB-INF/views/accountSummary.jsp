<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	
 	<script type='text/javascript' src="<c:url value='/dwr/engine.js'/>"></script>
 	<script type='text/javascript' src="<c:url value='/dwr/util.js'/>"></script>	
	<script type='text/javascript' src="<c:url value="/dwr/interface/AccountManager.js"/>"></script>
	<script type="text/javascript">
	    function searchAccounts()
	    {
	    	dwr.util.useLoadingMessage();
	        var name = dwr.util.getValue("name");
	        AccountManager.searchAccounts(name, function(accounts) {
	        	
	        	  dwr.util.removeAllRows("accountbody");
	        	  if (accounts.length == 0) {
	        	    addSingleRow("accountbody", "No se encontraron coincidencias");
	        	  }
	        	  else {
	        	    dwr.util.addRows("accountbody", accounts, [
	        	      function(account) { 
	        	    	  var url = "<c:url value='/accounts/accountDetails.htm?entityId=" + account.entityId + "'/>";
	        	    	  url = "<a href='" + url + "'>" + account.name +	"</a>";	
	        	      	  return url; },
	        	      function(account) { return account.number; }
	        	    ], { escapeHtml:false });
	        	  }	        	   
	        	  
			});
	  	           		 	
	    }       
	    
	    function addSingleRow(id, message) {
	    	  dwr.util.addRows(id, [1], [
	    	    function(data) { return message; }
	    	  ], {
	    	    cellCreator:function() {
	    	      var td = document.createElement("td");
	    	      td.setAttribute("colspan", 3);
	    	      return td;
	    	    }
	    	  });
	    	}
	</script>		
	<title>dwr-1-solution: Account Summary</title>
</head>

<body>
	
<div id="main_wrapper">

<h1>Account Summary</h1>
	
	<table>
		<tr>
			<td>Name:</td>
			<td><input id="name" name="name" /></td>
		</tr>
		<tr>
			<td colspan="3"><input type="button" value="Search" onclick="searchAccounts()"/></td>
		</tr>
	</table>
	
	<br/>
	<div>	
		<input type="button" value="Add" 
				onclick="location.href='<c:url value="/accounts/editAccount.htm"/>'"/>
		<input type="button" value="Cancel" 
				onclick="location.href='<c:url value="/"/>'"/>
	</div>
	<br/>
		
	<table id="accounts" cellpadding="0" cellspacing="0" style="width: 400px" > 
		<thead> 
			<tr> 
	            <th scope="col">Owner</th>
	            <th scope="col">Number</th>
			</tr>
		</thead> 
		<tbody id="accountbody">
			<c:forEach var="account" items="${accounts}" >
				<tr>	
					<td>
						<a href="accountDetails.htm?entityId=${account.entityId}">${account.name}</a>           	
					</td>
		            <td>
		            	${account.number}
		            </td>
	           </tr>
	        </c:forEach>				
		</tbody>
	</table> 
	<br/>
	<br/>
	<br/>
</div>
</body>
</html>
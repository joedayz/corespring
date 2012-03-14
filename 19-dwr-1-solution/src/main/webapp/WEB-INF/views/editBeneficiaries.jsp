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
 	<script type='text/javascript' src="<c:url value="/dwr/interface/BeneficiaryManager.js"/>"></script>
 	
	<script type="text/javascript">
	
	window.onload=init; 
	
	function init() {
	  fillTable();
	}
	
	var viewed = -1;
	var beneficiariesCache = { };
	
	function fillTable() {
		
		var accountId = dwr.util.getValue("accountId");
		BeneficiaryManager.findBeneficiariesByAccountId(accountId, function(beneficiaries) {	
		    // Delete all the rows except for the "pattern" row
		    dwr.util.removeAllRows("beneficiarybody", { filter:function(tr) {
		      return (tr.id != "pattern");
		    }});
		    // Create a new set cloned from the pattern row
		    var beneficiary, id, urlEdit, urlDelete;
		    for (var i = 0; i < beneficiaries.length; i++) {
				beneficiary = beneficiaries[i];
			    id = beneficiary.entityId;
			    dwr.util.cloneNode("pattern", { idSuffix:id });
			    dwr.util.setValue("tableName" + id, beneficiary.name);
			    
			    var percentage = parseFloat(beneficiary.allocationPercentageView);
			    dwr.util.setValue("tablePercentage" + id, percentage * 100);
			    dwr.util.setValue("tableSavings" + id, beneficiary.savingsView);
			    
			    urlEdit = "<a href='javascript:editClicked(" + id + "," + i + ")'>Edit</a>&nbsp;&nbsp;";
			    urlDelete = "<a href='javascript:deleteClicked(" + id + "," + i + ")'>Delete</a>";
			    dwr.util.setValue("tableOptions" + id, urlEdit + " " + urlDelete, { escapeHtml:false });
			    $("pattern" + id).style.display = "table-row";
			    beneficiariesCache[id] = beneficiary;
		    }
	  	});
	}
	
	function editClicked(eleid) {
		var beneficiary = beneficiariesCache[eleid];
		dwr.util.setValue("entityId", beneficiary.entityId);
		dwr.util.setValue("name", beneficiary.name);
		
		var percentage = parseFloat(beneficiary.allocationPercentageView);
		dwr.util.setValue("allocationPercentage", percentage * 100);
	}
	
	function deleteClicked(eleid) {
	  var beneficiary = beneficiariesCache[eleid];
	  if (confirm("Are you sure you want to delete " + beneficiary.name + "?")) {
	    dwr.engine.beginBatch();
	    BeneficiaryManager.deleteBeneficiary(beneficiary.entityId, function(beneficiaries) {});
	    fillTable();
	    dwr.engine.endBatch();
	  }
	}
	
	function writeBeneficiary() {
	
		var accountId = dwr.util.getValue("accountId");		
		var entityId = dwr.util.getValue("entityId");
		var name = dwr.util.getValue("name");
		var allocationPercentage = dwr.util.getValue("allocationPercentage");
		var ok = true;
		
		if(!name || 0 === name.length){
			alert("Name is a required field.");
			ok = false;
		}

		if(ok && !allocationPercentage || 0 === allocationPercentage.length){
			alert("Allocation Percentage is a required field.");
			ok = false;
		}
		
		var percentage = parseFloat(allocationPercentage);
		if(ok && isNaN(percentage)){
			alert("Allocation Percentage must be a number.");
			ok = false;
		}	
		
		if(ok && allocationPercentage > 100){
			alert("Allocation Percentage can not be greater than 100%.");
			ok = false;
		}
		
		if(ok){
			BeneficiaryManager.isValidAllocationPercentage(accountId, entityId, percentage, function(isValidAllocationPercentage) {
				if(!isValidAllocationPercentage){
					alert("The sum of percentages exceeds 100%.");	
				}else {
					dwr.engine.beginBatch();
					BeneficiaryManager.saveBeneficiary(accountId, entityId, name, allocationPercentage, function() {});
					fillTable();
					clearBeneficiary();
					dwr.engine.endBatch();		
				}
			});			
		}
	}
	
	function clearBeneficiary() {
	  viewed = -1;	  
	  dwr.util.setValue("entityId", -1);
	  dwr.util.setValue("name", "");	
	  dwr.util.setValue("allocationPercentage", "");	
	}
	</script> 	
 		
	<title>dwr-1-solution: Edit Beneficiaries Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Beneficiaries Details</h1>
<input type="hidden" id="accountId" name="accountId" value="${account.entityId}"/>
<table>
	<tr><td>
		<table>
			<tr>
				<td>Account:</td>
				<td>${account.number}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${account.name}</td>
			</tr>
		</table>
	</td></tr>
	<tr><td>
		<table>
			<thead>
				<tr>
					<td>Beneficiaries:</td>
				</tr>
				<tr>
					<td>Name</td>
					<td>Allocation Percentage</td>
					<td>Savings</td>
					<td>Options</td>
				</tr>
			</thead>
			<tbody id="beneficiarybody">
				<tr id="pattern" style="display:none;">
			    	<td><span id="tableName">Name</span><br/></td>
			      	<td><span id="tablePercentage">Percentage</span>%<br/></td>
			      	<td>$<span id="tableSavings">Savings</span></td>
			      	<td><span id="tableOptions"></span></td>
				</tr>
			</tbody>			
		</table>
	</td></tr>
</table>

<h3>Edit Beneficiary</h3>
<table class="plain">
	<tr>
	    <td>Name:</td>
	    <td><input id="name" type="text" maxlength="50" size="30"/></td>
  	</tr>
  	<tr>
	    <td>Allocation Percentage:</td>
	    <td><input id="allocationPercentage" type="text" maxlength="4" size="4"/>%</td>
  	</tr>
  	<tr>
	    <td colspan="2" align="right">
	      <small>(ID=<span id="entityId">-1</span>)</small>
	      <input type="button" value="Save" onclick="writeBeneficiary()"/>
	      <input type="button" value="Clear" onclick="clearBeneficiary()"/>
		  <input type="button" value="Cancel" 
				onclick="location.href='<c:url value="/accounts/accountSummary.htm"/>'"/>	      
	   </td>
  	</tr>
</table>

</div>
</body>
</html>
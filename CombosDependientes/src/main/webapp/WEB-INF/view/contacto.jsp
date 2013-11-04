<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ page isELIgnored="false" %>  
<!DOCTYPE html> 
<html > 

<head> 
<meta charset="utf-8"/> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 

<title></title> 

<meta name="description" content="HTML 5 CSS 3 Spring Form"/> 
<meta name="viewport" content="width=device-width,initial-scale=1"/> 
<script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
</head> 

<body> 
    <div id="container"> 
         <form  method="post" action="tabs.htm"> 

            <h1>Formulario test...</h1> 

            <fieldset id="user-details"> 
               <div id="cont1"> 
                <label for="name" >Name: </label>  
                <input type="text" name="name" 
                    value="" id="name" placeholder="Your Name" required="required" 
                    autofocus="autofocus"  />  
                <label for="email" >Email: </label> 
                <input type="email" name="email" value="" id="email" placeholder="mail@email.com" required="required"  />  
                </div> 
                <div id="cont2"> 
                <label for="phone" >Phone:</label>  
                <input type="text" name="phone" value=""  /> 
                <label for="website" >Website:</label>  
                <input type="url" name="website" value=""  /> 
                </div> 
            </fieldset> 
            <!--end user-details--> 
             
            <fieldset id="user-message"> 

                <label for="message" >Your Message: </label> 
                <textarea name="message" rows="0" cols="0" ></textarea> 

             
        <div id="combo1"> 
        <select name="paises" id="paises" > 
            <option value="-1">--Select your country--</option> 
            <c:forEach items="${listCountries}" var="country"> 
                <option value="<c:out value="${country.id}" />"><c:out value="${country.country}" /></option> 
            </c:forEach> 
        </select> 
        </div>     
        <div id="combo2"> 
        <select name="states" id="states" disabled="disabled" > 
        <option>--Select state--</option> 
        </select>     
        </div> 
            </fieldset> 
            <br> 
            <input type="submit" value="Submit Message" name="submit" 
                    class="submit" /> 
         </form>   
         
                 
    </div> 
    <!--! end of #container --> 

<script type="text/javascript" language="javascript"> 

	jQuery(document).ready(function() { 

		var contexPath = "<%=request.getContextPath() %>"; 

    	$('#paises').change( function(e) { 
     
    	if(jQuery(this).val()!="-1"){ 
     		$('#states').find('option').remove().end() 
     		.append('<option value="-1">--Select state--</option>'); 
    
        	e.preventDefault(); 
        	var val = $(this).val(); 
        	jQuery("#states").removeAttr("disabled"); 
         
        	
            
            $.ajax({ 
               type: "POST", 
               url:  contexPath + '/loadcities', 
               dataType: 'json', 
               data: { idpais : val }, 
               success: function(data) { 
                
               showUsers(data.lstStates); 
                  // $('#states').html( data.lstStates ); 
               }, 
               error: 
               function(e){ 
	             alert('Error: ' + e); 
             }  
            }); 
            
            
            
        } 
        else { 
           $("#states").attr("disabled","disabled"); 
           $('#states').find('option').remove().end() 
           .append('<option value="-1">--Select state--</option>'); 
        } 
    }); 

    function showUsers(data) { 
  
        for ( var i = 0, len = data.length; i < len; ++i) { 
            var msajax = data[i]; 
            $('#states').append("<option value=\"" +msajax.id + "\">" + msajax.city+ "</option>"); 
        } 
    } 

}); 
</script> 

</body> 
</html>  
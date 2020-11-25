<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $( "#bookApp" ).click(function() {
    	  $( "#bookAppoinment" ).submit();
    	  alert("Appointment is booked successfully !!!");
    	 // window.location.href="${contextPath}/powertools/en/USD/";
    	});
    
    /* function validateForm() {
        var isValid = true;
        $('div#main').each(function() {
          if ( $(this).val() === '' )
              isValid = false;
        });
        return isValid;
      }
 */
});
</script>
<style>
table#booked td, th { border: 1px solid #CCC; } 
</style>
<template:page pageTitle="${pageTitle}">


	<div id="main" align="center">
		<form:form id="bookAppoinment" action="bookAppointment" method="post" modelAttribute="bookAnAppointmentForm">
		<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2 class="heading2">Book An Appointment</h2></td>
				</tr>
				<tr>
					<td>Model Number :</td>
					<td>
					  <c:choose>
					    <c:when test="${param.oid == null || param.oid == ''}">
					     <form:input path="modelNumber" class="form-control"/>
					    </c:when>
					    <c:otherwise>
					     <input name="modelNumber" value="${productCodes}" class="form-control"/>
					    </c:otherwise>
					  </c:choose>
					</td>
				</tr>
				<tr>
					<td>Type of Service Required :</td>
					<td>
					 <select name="typeOfService" class="form-control">
                       <option selected="selected">Select A Service</option>
                       <option value="Installation">Installation</option>
                       <option value="Repair">Repair</option>
                       <option value="Maintenance">Maintenance</option>
                      </select>
					</td>
				</tr>
				<tr>
					<td>Time :</td>
					<td>
					   <div class='input-group' id='datetimepicker1'>
					      <form:input path="time" class="form-control"/>
					      <span class="input-group-addon">
					         <span class="glyphicon glyphicon-calendar"></span>
					      </span>
					    </div>
					</td>
				</tr>
				<tr>
					<td>Customer Details : </td>
					<td>
					  <c:choose>
				        <c:when test="${param.custemail == null || param.custemail == ''}">
				          <form:input path="customerDetails" class="form-control"/>
				        </c:when>
				        <c:otherwise>
				          <input name="customerDetails" value="${param.custemail}" class="form-control">
				        </c:otherwise>
					  </c:choose>
					</td>
				</tr>
				
				<tr>
					<td>Order Number : </td>
					<td>
					<c:choose>
				       <c:when test="${param.oid == null || param.oid == ''}">
				          <form:input path="orderNo" class="form-control"/>
				       </c:when>
				       <c:otherwise>
				          <input name="orderNo" value="${param.oid}" readonly="readonly" class="form-control">
				       </c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
				   <td>Comments : </td>
				   <td><textarea name="comments" maxlength="150" class="form-control"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<div class="row">
					 <div class="col-md-6 pull-right">
					    <input class="btn btn-primary btn-block" type="button" id="bookApp"
						value="Book an Appointment"   />
					  </div>
					  </div>
					</td>
				</tr>
			</table></div>
			</div>
			
		</form:form>
	</div>
	
	<div class="row">
      <div class="col-md-12">
      <div class="col-md-12">
	<table id="booked"class="table">
    <!-- here should go some titles... -->
    <tr>
     	<th>Index </th>
        <th>Model Number</th>
        <th>Type of Service Required</th>
        <th>Time </th>
        <th>Customer Details</th>
        <th>Order Number</th>
		<th>Comments</th>
    </tr>
     <c:forEach items="${bookAnAppointmentModelList}" var="appointment" varStatus="loop">
    <tr>
    <td>
            <c:out value="${loop.count}" />
        </td>
        <td>
            <c:out value="${appointment.modelNumber}" />
        </td>
        <td>
            <c:out value="${appointment.typeOfService}" />
        </td>
        <td>
            <c:out value="${appointment.time}" />
        </td>
        <td>
            <c:out value="${appointment.customerDetails}" />
        </td>
        <td>
            <c:out value="${appointment.orderNo}" />
        </td>
		<td>
            <c:out value="${appointment.customerComments}" />
        </td>
    </tr>
    </c:forEach>
</table>
</div>
</div>
</div>
</template:page>

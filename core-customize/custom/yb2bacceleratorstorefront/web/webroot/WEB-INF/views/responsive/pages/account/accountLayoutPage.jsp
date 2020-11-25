<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<spring:url value="/my-account/update-profile" var="updateProfileUrl"/>
<spring:url value="/my-account/update-password" var="updatePasswordUrl"/>
<spring:url value="/my-account/update-email" var="updateEmailUrl"/>
<spring:url value="/my-account/address-book" var="addressBookUrl"/>
<spring:url value="/my-account/payment-details" var="paymentDetailsUrl"/>
<spring:url value="/my-account/orders" var="ordersUrl"/>


<template:page pageTitle="${pageTitle}">
		
		<div id="globalMessages">
		<common:globalMessages/>
		<c:if test="${not empty orderCancelResult }">
			<c:choose>
				<c:when test="${ orderCancelResult.success}">
					<div class="span-24">
						<div class="information_message positive">
						<span class="single"></span>
						<p><spring:theme code="order.cancel.success" arguments="${orderCancelResult.orderId }"/></p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="span-24">
						<div class="information_message positive">
						<p><spring:theme code="order.cancel.failed" arguments="${orderCancelResult.orderId },${orderCancelResult.failReason }"/></p>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		</div>

		<cms:pageSlot position="SideContent" var="feature" class="accountPageSideContent">
			<cms:component component="${feature}" />
		</cms:pageSlot>
        <cms:pageSlot position="TopContent" var="feature" element="div" class="accountPageTopContent">
            <cms:component component="${feature}" />
        </cms:pageSlot>

        <div class="account-section">
            <cms:pageSlot position="BodyContent" var="feature" element="div" class="account-section-content">
                <cms:component component="${feature}" />
            </cms:pageSlot>
        </div>

        <cms:pageSlot position="BottomContent" var="feature" element="div" class="accountPageBottomContent">
            <cms:component component="${feature}" />
        </cms:pageSlot>
</template:page>
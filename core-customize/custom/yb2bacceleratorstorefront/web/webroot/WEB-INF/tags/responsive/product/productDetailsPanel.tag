<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>


<div class="row">
	<div class="col-sm-6 col-lg-4 col-md-7">
		<product:productImagePanel galleryImages="${galleryImages}" />
		<cms:pageSlot position="SpareParts" var="component" element="div" class="productDetailsPageSectionUpSelling">
		<cms:component component="${component}" element="div" class="productDetailsPageSectionUpSelling-component"/>
	</cms:pageSlot>
	</div>
	<div class="clearfix hidden-sm hidden-md hidden-lg"></div>
	<div class="col-sm-6 col-lg-8 col-md-5">
		<div class="product-main-info">
			<div class="row">
				<div class="col-lg-6">
				<div class="product-details page-title">
					<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
					<product:productShare/>
					<div class="name">${fn:escapeXml(product.name)}<p class="sku">ID: <span class="code">${fn:escapeXml(product.code)}</span></p></div>
					</ycommerce:testId>
					<product:productReviewSummary product="${product}" showLinks="true"/>
				</div>
					<div class="product-details">
						<product:productPromotionSection product="${product}"/>
						<ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
							<product:productPricePanel product="${product}" />
						</ycommerce:testId>
						
						<div class="description">
						<p class="p-heading">Product Description</p>
						<p class="p-description">${ycommerce:sanitizeHTML(product.summary)}</p></div>
						<c:if test="${not empty  product.specifications}">
							<c:set value="${product.specifications}" var="downloadUrl"/>
							<spring:url value="${downloadUrl}" var="url"/>
							<a href="${downloadUrl}" target="_blank"  class="button positive right">
								<spring:theme code="checkout.link.download.manual" text="Download  Manual"/>
							</a>
							<%--     <embed src="${downloadUrl}" width="500" height="375" type='application/pdf'> --%>
						</c:if>
					</div>
				</div>
				
				<c:if  test="${not product.purchasable}">					
					<c:set var="productStockLevel" value="Product cannot be purchased." scope="request"/>
				</c:if>
				
				
				
				<div class="col-md-12">
					<cms:pageSlot position="VariantSelector" var="component" element="div" class="page-details-variants-select">
						<cms:component component="${component}" element="div" class="yComponentWrapper page-details-variants-select-component"/>
					</cms:pageSlot>
					<cms:pageSlot position="AddToCart" var="component" element="div" class="page-details-variants-select">
						<cms:component component="${component}" element="div" class="yComponentWrapper page-details-add-to-cart-component"/>
					</cms:pageSlot>
					
				</div>
			</div>
		</div>

	</div>
</div>
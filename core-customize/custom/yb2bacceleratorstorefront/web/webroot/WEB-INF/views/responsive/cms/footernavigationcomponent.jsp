<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/responsive/common/footer"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${component.visible}">
	<div class="container-fluid">
	    <div class="footer__top row">
	        <div class="col-md-10 col-md-push-1">
	            <div class="footer__left col-xs-12 col-sm-12 col-md-9">
	                <div class="row">
	                	<c:forEach items="${component.navigationNode.children}" var="childLevel1">
		                	<c:forEach items="${childLevel1.children}" step="${component.wrapAfter}" varStatus="i">
							   <div class="footer__nav--container col-xs-12 col-sm-3">
		                	       <c:if test="${component.wrapAfter > i.index}">
	                                   <div class="title">${fn:escapeXml(childLevel1.title)}</div>
	                               </c:if>
	                               <ul class="footer__nav--links">
	                                   <c:forEach items="${childLevel1.children}" var="childLevel2" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
	                                        <c:forEach items="${childLevel2.entries}" var="childlink" >
		                                        <cms:component component="${childlink.item}" evaluateRestriction="true" element="li" class="footer__link"/>
	                                        </c:forEach>
	                                   </c:forEach>
	                               </ul>
	                		   </div>
						    </c:forEach>
	                	</c:forEach>
	                	<div class="footer__nav--container col-xs-12 col-sm-3">
	                		<div class="title">Follow Us</div>
	                			<ul class="footer__nav--links">
	                				<li class="yCmsComponent footer__link">
	                							<a href="https://www.linkedin.com/company/hcl-technologies" target="_blank"><img title="Linked In" alt="Linked In" src="${commonResourcePath}/images/in_icon.png" width="20" height="20"></a>
	                		                	<a href="https://www.facebook.com/HCLTechnologies/" target="_blank"><img title="Facebook" alt="Facebook" src="${commonResourcePath}/images/fb_icon.png" width="20" height="20"></a>
	                		                	<a href="https://twitter.com/hcltech?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor" target="_blank"><img title="Twitter" alt="Twitter" src="${commonResourcePath}/images/insta_icon.png" width="20" height="20"></a>
	                		                	<a href="https://www.instagram.com/hcltech/" target="_blank"><img title="Instagram" alt="Instagram" src="${commonResourcePath}/images/twr_icon.png" width="20" height="20"></a>
	                				</li>
	                			</ul>
	                	</div>
	               </div>
	           </div>
	           <div class="footer__right col-xs-12 col-md-3">
	               <c:if test="${showLanguageCurrency}">
	                   <div class="row">
	                       <div class="col-xs-6 col-md-10 footer__dropdown">
	                           <footer:languageSelector languages="${languages}" currentLanguage="${currentLanguage}" />
	                       </div>
	                       <div class="col-xs-6 col-md-6 footer__dropdown">
	                           <footer:currencySelector currencies="${currencies}" currentCurrency="${currentCurrency}" />
	                       </div>
	                   </div>
	               </c:if>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="footer__bottom">
	    <div class="footer__copyright">
	        <div class="container">${fn:escapeXml(notice)}</div>
	    </div>
	</div>
</c:if>
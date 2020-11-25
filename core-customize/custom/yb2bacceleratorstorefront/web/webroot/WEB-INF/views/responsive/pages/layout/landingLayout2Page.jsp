<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<template:page pageTitle="${pageTitle}">
    
 <section class="container-fluid">
        <div class="row hero-section">
            <div class="col-sm-12 text-center">
                <div class="display-4 hero-text">
                    <h2>PleurX drainage system</h2>
                </div>
            </div>
            
        </div>
    </section>
   	
	 <section id="shop-by-equipment">
        <div class="container">
            <div class="row">
                <h3 class="text-center title">Equip your patients to manage effusions and ascites at home</h3>
            </div>
            <div class="row">
                <ul class="nav nav-pills mb-3 shop-eq-nav" id="pills-tab" role="tablist">
                    <li class="nav-item active">
                        <a class="nav-link show active" id="pills-GBL75-tab" data-toggle="pill" href="#pills-GBL75" role="tab" aria-controls="pills-GBL75" aria-selected="true">INDOOR LUMINAIRES</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-YTOOL-tab" data-toggle="pill" href="#pills-YTOOL" role="tab" aria-controls="pills-YTOOL" aria-selected="false">OUTDOOR LUMINAIRES</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-GSTE98-tab" data-toggle="pill" href="#pills-GSTE98" role="tab" aria-controls="pills-GSTE98" aria-selected="false">LED LAMPS AND TUBES</a>
                    </li>
                </ul>
                <div class="tab-content" id="pills-tabContent">
                    <div class="tab-pane fade active in" id="pills-GBL75" role="tabpanel" aria-labelledby="pills-GBL75-tab">
                        <div class="col-sm-7 col-sm-offset-1">
                            <div class="row category-images shop-equipments">
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Indoor-Luminaires/Recessed/c/4005">
																					<img src="${commonResourcePath}/images/Indoor-LuminairesRecessed.png">
																					<div class="caption">
																					<p>Recessed </p>
																					</div>
																					</a>
                                    </div>
                                </div>
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Indoor-Luminaires/Suspended/c/4006">
																					<img src="${commonResourcePath}/images/Indoor-LuminairesSuspended.jpg">
																					<div class="caption">
																					<p>Suspended </p>
																					</div>
																					</a>
                                    </div>
                                </div>
                               
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Indoor-Luminaires/Projector/c/4007">
																					<img src="${commonResourcePath}/images/Indoor-LuminairesProjectors.png">
																					<div class="caption">
																					<p>Projector</p>
																					</div>
																					</a>
                                    </div>
                                </div>                              
                            </div>
                        </div>
                        <div class="col-sm-3 col-sm-offset-1 shop-eqp-text">
                        	<h2>HCL Lighting </h2>
                        	<p> Our lighting innovations will change how you experience light.</p>
                        	<a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/c/4000">
							<button class="btn btn-primary btn-lg" id="view-more">View More Products</button>
							</a>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="pills-YTOOL" role="tabpanel" aria-labelledby="pills-YTOOL-tab"><div class="col-sm-7 col-sm-offset-1">
                            <div class="row category-images shop-equipments">
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Outdoor-Luminaires/FloodLight/c/4009">
																					<img src="${commonResourcePath}/images/Outdoor-LuminairesFloodLight.png">
																					<div class="caption">
																					<p>Floodlight </p>
																					</div>
																					</a>
                                    </div>
                                </div>
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Outdoor-Luminaires/Tunnel-Underpass-Lighting/c/4015">
																					<img src="${commonResourcePath}/images/Outdoor-LuminairesTunnel-Underpass-Lighting.png">
																					<div class="caption">
																					<p>Tunnel Underpass Lighting </p>
																					</div>
																					</a>
                                    </div>
                                </div>
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/Outdoor-Luminaires/Urban-Road-Luminaires/c/4014">
																					<img src="${commonResourcePath}/images/Outdoor-LuminairesUrban-Road-Luminaires.png">
																					<div class="caption">
																					<p>Urban Road Luminaires </p>
																					</div>
																					</a>
                                    </div>
                                </div>                                
                            </div>
                        </div>
                         <div class="col-sm-3 col-sm-offset-1 shop-eqp-text">
                        	<h2>HCL Lighting </h2>
                        	<p> Our lighting innovations will change how you experience light.</p>
                        	<a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/c/4000">
							<button class="btn btn-primary btn-lg" id="view-more">View More Products</button>
							</a>
                        </div></div>
                    <div class="tab-pane fade" id="pills-GSTE98" role="tabpanel" aria-labelledby="pills-GSTE98-tab"><div class="col-sm-7 col-sm-offset-1">
                            <div class="row category-images shop-equipments">
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/LED-Lamps-Tubes/Tubes/c/4010">
																					<img src="${commonResourcePath}/images/LED-Lamps-and-TubesLED-Tubes.png">
																					<div class="caption">
																					<p>LED Tubes </p>
																					</div>
																					</a>
                                    </div>
                                </div>
                                <div class="col-sm-4 category-img">
                                    <div class="thumbnail">
                                        <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/LED-Lamps-Tubes/Spots/c/4011">
																					<img src="${commonResourcePath}/images/LED-Lamps-and-TubesLED-Spots.png">
																					<div class="caption">
																					<p>LED Spots </p>
																					</div>
																					</a>
                                    </div>
                                </div>                              
                            </div>
                        </div>
                         <div class="col-sm-3 col-sm-offset-1 shop-eqp-text">
                        	<h2>HCL Lighting </h2>
                        	<p> Our lighting innovations will change how you experience light.</p>
                        	<a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/Shop-by-Philips-Lighting/c/4000">
							<button class="btn btn-primary btn-lg" id="view-more">View More Products</button>
							</a>
                        </div></div>
                </div>
            </div>
        </div>
    </section>
   
    <section id="top-stories">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6 book-appointment">
                    <img class="calendar" src="${commonResourcePath}/images/calender_icon.png">
                    <h2>Book an appointment</h2>
                    <p>Click below to Book an appointment. We will get back to you soon.</p>
                    <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/showBookAnAppointmentPage" ><button class="btn btn-lg" id="book-now">Book Now</button> </a>
                </div>
                <div class="col-sm-6 story-text">
                    <h3> TOP STORIES </h3>
                    <p> Is your supplier selling you rejected products on the grey market? </p>
                    <p> Sourcing from China 101, part 6: Kepp some leverage with suppliers</p>
                    <p> Which product destruction method is right for your defetcive products?</p>
                    <button class="btn btn-primary btn-lg" id="view-more">View More Stories</button>
                </div>
            </div>
        </div>
    </section>
    <section>
        <div class="container deals">
            <div class="row text-center">
                <h3>BEST DEALS</h3>
            </div>
            <div class="row offer-container">
                <div class="col-sm-6 auto-gears">
                    <h2>Up <span class="bold">30% OFF</span> on</h2>
                    <h2 class="heading-2">Projector's spareparts</h2>
                </div>
                <div class="col-sm-6 offers">
                    <h2 class="heading-1">Great Deals on</h2>
                    <h2 class="heading-2">Projectors</h2>
                    <p>Get upto 30% OFF on projector's sparepart.</p>
                    <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/showBestDeals"><button class="btn btn-primary btn-lg" id="view-more">View All Deals</button></a>
                </div>
            </div>
            <div class="row offer-container">
                <div class="col-sm-6 offers-second">
                    <h2 class="heading-1">Tunnel Underpass Lighting</h2>
                    <h4 class="heading-2">Limited Time only</h4>
                    <p>Get upto 10% OFF on Tunnel Underpass Lighting spare parts.</p>
                    <a href="https://hclsdhyb01.hcldigilabs.com:9002/yb2bacceleratorstorefront/powertools/en/USD/showBestDeals"><button class="btn btn-primary btn-lg" id="view-more">View All Deals</button></a>
                </div>
                <div class="col-sm-6 cable-wire">
                    <h2>Up <span class="bold">10% OFF</span> on</h2>
                    <h2 class="heading-2">Tunnel Underpass Lighting spareparts</h2>
                </div>
            </div>
        </div>
    </section>
   
   <!--
	<cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>
	
    <div class="row no-margin">
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2A" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2B" var="feature" element="div" class="row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12">
            <cms:pageSlot position="Section2C" var="feature" element="div" class="landingLayout2PageSection2C">
                <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
            </cms:pageSlot>
        </div>
    </div>

    <cms:pageSlot position="Section3" var="feature" element="div">
        <cms:component component="${feature}" element="div" class="no-space yComponentWrapper"/>
    </cms:pageSlot>

    <cms:pageSlot position="Section4" var="feature" element="div" class="row no-margin">
        <cms:component component="${feature}" element="div" class="col-xs-6 col-md-3 no-space yComponentWrapper"/>
    </cms:pageSlot>

    <cms:pageSlot position="Section5" var="feature" element="div">
        <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
    </cms:pageSlot>
-->
</template:page>

/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 *
 */

package com.sap.hybris.medical.webservices.controllers;

import com.sap.hybris.medical.commons.facades.MedicalFacade;
import com.sap.hybris.medical.webservices.dto.MedicalProductsDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/products")
public class MedicalProductsController extends BaseMedicalController {
    @Resource(name = "medicalFacade")
    private MedicalFacade medicalFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalProductsDTO getMedicalProducts() {
        final MedicalProductsDTO productsDTO = new MedicalProductsDTO();
        productsDTO.setProducts(medicalFacade.getMedicalProducts());
        return productsDTO;
    }
}

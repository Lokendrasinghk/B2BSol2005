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

import com.sap.hybris.medical.commons.dto.MedicalKitTemplatesDTO;
import com.sap.hybris.medical.commons.dto.MedicalProceduresDTO;
import com.sap.hybris.medical.commons.facades.MedicalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/{baseSiteId}/medical/procedures")
public class ProceduresController extends BaseMedicalController {

    @Autowired
    MedicalFacade medicalFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalProceduresDTO getAllProcedures() {
        return medicalFacade.getAllProcedures();
    }

    @RequestMapping(value = "/{procedureCode}/kittemplates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MedicalKitTemplatesDTO getKitTemplatesForProcedure(@PathVariable final String procedureCode) {
        return medicalFacade.getKitTemplatesForProcedure(procedureCode);
    }
}
